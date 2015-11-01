package com.sprhib.service;

import com.sprhib.dao.MemberDAO;
import com.sprhib.dao.OrganizationDAO;
import com.sprhib.model.Member;
import com.sprhib.model.Organization;
import com.sprhib.model.Team;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class OrganizationServiceImplTest {
	OrganizationDAO organizationDAOMock;
	MemberDAO memberDAOMock;
	OrganizationServiceImpl organizationService;

	@Before
	public void initService() {
		organizationDAOMock = Mockito.mock(OrganizationDAO.class);
		memberDAOMock = Mockito.mock(MemberDAO.class);
		organizationService = new OrganizationServiceImpl();
		organizationService.setOrganizationDAO(organizationDAOMock);
		organizationService.setMemberDAO(memberDAOMock);
	}

	@Test
	public void testAddOrganization() {
		Organization organization = new Organization();
		organizationService.addOrganization(organization);
		verify(organizationDAOMock, times(1)).addOrganization(Matchers.same(organization));
	}

	@Test
	public void testUpdateOrganization() {
		Organization organization = new Organization();
		organizationService.updateOrganization(organization);
		verify(organizationDAOMock, times(1)).updateOrganization(Matchers.same(organization));
	}

	@Test
	public void testGetOrganization() {
		organizationService.getOrganization(2);
		verify(organizationDAOMock, times(1)).getOrganization(Matchers.eq(2));
	}

	@Test
	public void testDeleteOrganization() {
		Organization org = new Organization();
		org.setId(7);
		org.setName("testDeleteOrganization");
		Team team = new Team();
		team.setId(8);
		List<Team> teams = new ArrayList<>();
		teams.add(team);
		org.setTeams(teams);

		when(organizationDAOMock.getOrganization(Matchers.eq(7))).thenReturn(org);

		Member member = new Member();
		member.setId(100);
		member.setName("Test Member");
		Team memberTeam = new Team();
		memberTeam.setId(8);
		memberTeam.setName("Test Team");
		List<Team> memberTeams = new ArrayList<>();
		memberTeams.add(memberTeam);
		member.setTeams(memberTeams);

		List<Member> teamMembers = new ArrayList<>();
		teamMembers.add(member);

		when(memberDAOMock.getByOrganization(Matchers.eq(7))).thenReturn(teamMembers);

		organizationService.deleteOrganization(7);
		verify(memberDAOMock, times(1)).getByOrganization(Matchers.eq(7));
		verify(memberDAOMock, times(1)).updateMember(Matchers.same(member));
		verify(organizationDAOMock, times(1)).deleteOrganization(Matchers.eq(7));
	}

	@Test
	public void testGetOrganizations() {
		organizationService.getOrganizations();
		verify(organizationDAOMock, times(1)).getOrganizations();
	}

}
