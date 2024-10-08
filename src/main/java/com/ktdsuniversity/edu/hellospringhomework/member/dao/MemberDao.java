package com.ktdsuniversity.edu.hellospringhomework.member.dao;

import com.ktdsuniversity.edu.hellospringhomework.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.MemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.SignUpMemberVO;

public interface MemberDao {
	
	public String NAMESPACE = "com.ktdsuniversity.edu.hellospringhomework.member.dao.MemberDao";

	public int selectEmailCount(String email);
	
	public int insertNewMember(SignUpMemberVO signUpMemberVO);
	
	public String selectSalt(String email);
	
	public MemberVO selectOneMember(LoginMemberVO loginMemberVO);
	
	public int updateLoginFailState(LoginMemberVO loginMemberVO);
	
	public int selectLoginImpossibleCount(String email);
	
	public int updateLoginSuccessState(LoginMemberVO loginMemberVO);

	/**
	 * 회원 DELETE 쿼리를 실행한다.
	 * @param email 삭제할 회원의 이메일
	 * @return DB 에 Delete 한 회원의 개수
	 */
	public int deleteMe(String email);
}
