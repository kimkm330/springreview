package com.docmall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwdEncrypt;
	
	//회원가입 뷰
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String memberAdd() {
		
		return "/member/join";
	}
	
	//회원가입전송
	
	@RequestMapping(value = "/joinOk", method = RequestMethod.POST)
	public String memberAddOk(MemberVO vo, RedirectAttributes redirect) throws Exception {
		
		logger.info("입력데이터" + vo.toString());
		
		//비밀번호 암호화 처리
		passwdEncrypt.encode(vo.getMem_pw());
		service.join(vo);
		
		return "redirect:/";
	}
	
	//회원탈퇴
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String memberDelete() {
		
		return "/member/";
	}
	
	//회원수정
	
	
	//로그인
	
	
	//로그아웃
	
	//비밀번호 변경하기
	
}
