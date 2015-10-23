package com.sprhib.dao;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Member;
import com.sprhib.model.Team;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=BaseTestConfig.class)
@Transactional
public class MemberDAOTest {
	@Autowired
	MemberDAO memberDAO;

	@Autowired
	TeamDAO teamDAO;

	@Test
	public void testAddMember() {
		Team team1 = new Team();
		team1.setName("Test team 1");
		teamDAO.addTeam(team1);

		Team team2 = new Team();
		team2.setName("Test team 2");
		teamDAO.addTeam(team2);

		List<Team> teams = new ArrayList<Team>(2);
		teams.add(team1);
		teams.add(team2);

		Member member = new Member();
		member.setName("Test");
		member.setTeams(teams);
		memberDAO.addMember(member);
	}

	@Test
	public void testUpdateMember() {
		Member newMember = new Member();
		newMember.setName("Test");

		int memberId = memberDAO.addMember(newMember);
		Member member = memberDAO.getMember(memberId);
		member.setName("Updated");
		memberDAO.updateMember(member);
	}

	@Test
	public void testGetMember() {
		Member newMember = new Member();
		newMember.setName("Test");
		int orgId = memberDAO.addMember(newMember);

		Member member = memberDAO.getMember(orgId);
		assertEquals("Test", member.getName());
	}

	@Test
	public void testDeleteMember() {
		Member newMember = new Member();
		newMember.setName("Test");
		int memberId = memberDAO.addMember(newMember);

		memberDAO.deleteMember(memberId);

		Member org = memberDAO.getMember(memberId);
		assertNull(org);
	}

	@Test
	public void testGetMembers() {
		memberDAO.getMembers();
	}
}
