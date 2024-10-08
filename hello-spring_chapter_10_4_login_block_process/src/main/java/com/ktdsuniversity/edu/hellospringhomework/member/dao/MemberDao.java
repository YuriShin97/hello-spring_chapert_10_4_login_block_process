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
}
