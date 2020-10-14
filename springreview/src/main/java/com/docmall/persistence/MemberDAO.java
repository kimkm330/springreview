package com.docmall.persistence;

import com.docmall.domain.MemberVO;

public interface MemberDAO {

	public void join(MemberVO vo) throws Exception;

	// 회원정보 수정
	public void modifyUserInfo(MemberVO vo) throws Exception;
}
