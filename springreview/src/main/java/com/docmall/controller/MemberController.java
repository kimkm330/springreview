package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.dto.MemberDTO;
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
		
		return "/";
	}
	
	//회원수정
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPOST(MemberVO vo, RedirectAttributes rttr, HttpSession session) throws Exception {




		MemberDTO dto = new MemberDTO();
		dto.setMem_id(vo.getMem_id());
		dto.setMem_pw(vo.getMem_pw());
		
		// 비밀번호 암호화작업
		vo.setMem_pw(passwdEncrypt.encode(vo.getMem_pw()));
		service.modifyUserInfo(vo); // 회원수정
		

		// 처음에 로그인시 세션에 저장했던 정보를 다른곳에서 사용이 되어질 경우(회원수정, 비번변경, 회원탈퇴)
		// 그 정보가 수정이 발생이 되면,세션정보를 갱신해주어야 한다.
		// 예> 비밀번호가 변경
		// 세션작업. 수정중에서 변경된 정보를 세션에 새로 반영하는 의미.
		session.setAttribute("user", service.login(dto));
		
		rttr.addFlashAttribute("msg", "MODIFY_USER_SUCCESS");
		
		return "redirect:/";
	}
	
	//로그인
	
	
	//로그아웃
	
	//비밀번호 변경하기
	
}
