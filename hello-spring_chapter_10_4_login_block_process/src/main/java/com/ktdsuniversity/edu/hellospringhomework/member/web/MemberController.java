package com.ktdsuniversity.edu.hellospringhomework.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.hellospringhomework.member.service.MemberService;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.MemberVO;
import com.ktdsuniversity.edu.hellospringhomework.member.vo.SignUpMemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MemberController {

	@Autowired
	private MemberService memeberService;
	
	@GetMapping("/member/regist")
	public String viewRegistMemberPage() {
		return "member/memberregist";
	}
	
	@PostMapping("/member/regist")
	public String doRegistMember(@Valid SignUpMemberVO signUpMemberVO
								, BindingResult bindingResult
								, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("signUpMemberVO", signUpMemberVO);
			return "member/memberregist";
		}
		
		if(!signUpMemberVO.getConfirmPassword().equals(signUpMemberVO.getPassword())) {
			model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("signUpMemberVO", signUpMemberVO);
			return "member/memberregist";
		}
		
		boolean isRegist = this.memeberService.createNewMember(signUpMemberVO);
		
		return "redirect:/member/login";
		
	}
	
	@ResponseBody
	@GetMapping("/member/regist/available")
	public Map<String, Object> doCheckAvailableEmail(@RequestParam String email){
		
		boolean isAvailableEmail = this.memeberService.checkAvailableEmail(email);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("email", email);
		response.put("available", isAvailableEmail);
		
		return response;
	}
	
	@GetMapping("/member/login")
	public String viewLoginPage() {
		return "member/memberlogin";
	}
	
	@PostMapping("/member/login")
	public String doLogin(@Valid LoginMemberVO loginMemberVO
						, BindingResult bindingResult
						, HttpSession session
						, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginMemberVO", loginMemberVO); // jsp 의 attribute 와 연동.
			return "member/memberlogin";
		}
		
		// MemberServiceImpl 의 에러들을 핸들링하기 위해 try-catch 사용.
		try {
			MemberVO memberVO = this.memeberService.readMember(loginMemberVO);
			
			// 로그인 상태를 서버에 저장시킨다.
			session.setAttribute("_LOGIN_USER_", memberVO);
		} catch(IllegalArgumentException iae) {
			model.addAttribute("loginMemberVO", loginMemberVO);
			model.addAttribute("message", iae.getMessage());
			return "member/memberlogin";
		}
		
		return "redirect:/board/list";
	}
}
