package com.ktdsuniversity.edu.hellospringhomework.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hellospringhomework.access.dao.AccessLogDao;
import com.ktdsuniversity.edu.hellospringhomework.access.vo.AccessLogVO;
import com.ktdsuniversity.edu.hellospringhomework.common.beans.SHA;
import com.ktdsuniversity.edu.hellospringhomework.common.utils.RequestUtils;
import com.ktdsuniversity.edu.hellospringhomework.member.dao.MemberDao;
import com.ktdsuniversity.edu.hellospringhomework.member.service.MemberService;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.MemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.SignUpMemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private AccessLogDao accessLogDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SHA sha;
	
	@Override
	public boolean createNewMember(SignUpMemberVO signUpMemberVO) {
		
		  //1. salt발급
	      String salt = sha.generateSalt();
	      
	      //2. 사용자의 비밀번호 암호화
	      String password = signUpMemberVO.getPassword();
	      password = sha.getEncrypt(password, salt);

	      signUpMemberVO.setPassword(password);
	      signUpMemberVO.setSalt(salt);
		
//		int emailCount = this.memberDao.selectEmailCount(signUpMemberVO.getEmail());
//		if(emailCount > 0) {
//			throw new IllegalArgumentException("Email이 이미 사용중입니다.");
//		}
		int insertCount = this.memberDao.insertNewMember(signUpMemberVO);
		
		return insertCount > 0;
	}
	
	@Override
	public boolean checkAvailableEmail(String email) {
		return this.memberDao.selectEmailCount(email) == 0;
	}
	
	@Override
	public MemberVO readMember(LoginMemberVO loginMemberVO) {
		
		boolean isIpBlock = this.accessLogDao.selectLoginFailCount(RequestUtils.getIp()) >= 5;
		if(isIpBlock) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		
		// 1. Salt 조회.
		String salt = this.memberDao.selectSalt(loginMemberVO.getEmail());
		if(salt == null) {
			
			AccessLogVO accessLogVO = new AccessLogVO();
			accessLogVO.setAccessType("LOGIN");
			accessLogVO.setAccessUrl(RequestUtils.getRequest().getRequestURI());
			accessLogVO.setAccessMethod(RequestUtils.getRequest().getMethod().toUpperCase());
			accessLogVO.setAccessIp(RequestUtils.getIp());
			
			this.accessLogDao.insertNewAccessLog(accessLogVO);
			
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		// 2. 사용자가 입력한 비밀번호를 salt 를 이용해 암호화.
		String password = loginMemberVO.getPassword();
		password = this.sha.getEncrypt(password, salt);
		loginMemberVO.setPassword(password);
		
		// 3. 이메일과 암호화된 비밀번호로 데이터베이스에서 회원 정보 조회.
		MemberVO memberVO = this.memberDao.selectOneMember(loginMemberVO);
		if(memberVO == null) {
			
			// LOGIN_FAIL_COUNT 를 하나 증가시킨다.
			// LATEST_LOGIN_FAIL_DATE 를 현재 날짜로 갱신한다.
			// LATEST_LOGIN_IP 를 요청자의 IP 로 갱신한다.
			loginMemberVO.setIp(RequestUtils.getIp());
			this.memberDao.updateLoginFailState(loginMemberVO);
			
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		// LOGIN_FAIL_COUNT 가 5 이상이라면 && 마지막 로그인 실패 시간이 한시간이 지나지 않았다면
		// 정상적인 로그인 시도라고 하더라도 로그인을 실패시켜야 함.
		boolean isBlockState = this.memberDao.selectLoginImpossibleCount(loginMemberVO.getEmail()) > 0;
		if(isBlockState) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		// LOGIN_FAIL_COUNT 가 5 이상이라면 && 마지막 로그인 실패 시간이 한시간이 지났다면
		// 혹은 LOGIN_FAIL_COUNT 가 5 미만일 경우
		// 정상적인 로그인 시도일 경우 로그인을 성공시킨다.
		// 이 때 LOGIN_FAIL)COUNT 는 0으로 초기화시키고
		// LATEST_LOGIN_FAIL_DATE 는 NULL 로 초기화
		// LATEST_LOGIN_IP 는 요청자의 IP 로 갱신
		// LATEST_LOGIN_SUCCESS_DATE 는 현재 날짜로 갱신
		loginMemberVO.setIp(RequestUtils.getIp());
		this.memberDao.updateLoginSuccessState(loginMemberVO);
		
		AccessLogVO accessLogVO = new AccessLogVO();
		accessLogVO.setAccessType("LOGIN");
		accessLogVO.setAccessEmail(loginMemberVO.getEmail());
		accessLogVO.setAccessUrl(RequestUtils.getRequest().getRequestURI());
		accessLogVO.setAccessMethod(RequestUtils.getRequest().getMethod().toUpperCase());
		accessLogVO.setAccessIp(RequestUtils.getIp());
		accessLogVO.setLoginSuccessYn("Y");
		
		this.accessLogDao.insertNewAccessLog(accessLogVO);
		
		return memberVO;
	}

	@Override
	public boolean deleteMe(String email) {
		int deleteCount = memberDao.deleteMe(email);
		return deleteCount > 0;
	}
}
