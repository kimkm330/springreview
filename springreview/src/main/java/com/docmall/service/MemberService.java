package com.docmall.service;

import com.docmall.domain.MemberVO;

public interface MemberService {
	
	public void join(MemberVO vo) throws Exception;
	
	// 회원정보 수정
	public void modifyUserInfo(MemberVO vo) throws Exception;
		
}
