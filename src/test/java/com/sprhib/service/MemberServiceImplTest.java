package com.sprhib.service;

import com.sprhib.dao.MemberDAO;
import com.sprhib.model.Member;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class MemberServiceImplTest {
	MemberDAO memberDAOMock;
	MemberServiceImpl memberService;

	@Before
	public void initService() {
		memberDAOMock = Mockito.mock(MemberDAO.class);
		memberService = new MemberServiceImpl();
		memberService.setMemberDAO(memberDAOMock);
	}

	@Test
	public void testAddMember() {
		Member member = new Member();
		memberService.addMember(member);
		verify(memberDAOMock, times(1)).addMember(Matchers.same(member));
	}

	@Test
	public void testUpdateMember() {
		Member member = new Member();
		memberService.updateMember(member);
		verify(memberDAOMock, times(1)).updateMember(Matchers.same(member));
	}

	@Test
	public void testGetMember() {
		memberService.getMember(2);
		verify(memberDAOMock, times(1)).getMember(Matchers.eq(2));
	}

	@Test
	public void testDeleteMember() {
		memberService.deleteMember(7);
		verify(memberDAOMock, times(1)).deleteMember(Matchers.eq(7));
	}

	@Test
	public void testGetMembers() {
		memberService.getMembers();
		verify(memberDAOMock, times(1)).getMembers();
	}

}
