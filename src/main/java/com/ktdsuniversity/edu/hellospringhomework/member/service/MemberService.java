package com.ktdsuniversity.edu.hellospringhomework.member.service;

import com.ktdsuniversity.edu.hellospringhomework.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.MemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.SignUpMemberVO;

public interface MemberService {
	
	public boolean createNewMember(SignUpMemberVO signUpmemberVO);
	
	public boolean checkAvailableEmail(String email);
	
	public MemberVO readMember(LoginMemberVO loginMemberVO);

	/**
	 * 회원을 탈퇴시킨다.
	 * @param email 탈퇴시킬 회원의 이메일
	 * @return 탈퇴 성공 여부
	 */
	public boolean deleteMe(String email);
}
