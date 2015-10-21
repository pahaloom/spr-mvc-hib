package com.sprhib.dao;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Organization;
import com.sprhib.model.Team;
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
public class TeamDAOTest {
	@Autowired
	TeamDAO teamDAO;

	@Autowired
	OrganizationDAO organizationDAO;

	@Test
	public void testAddTeam() {
		Team team = new Team();
		team.setName("Test");
		team.setRating(8);
		Organization org = new Organization();
		org.setName("Test Organization");
		team.setOrganization(org);

		int id = teamDAO.addTeam(team);
		assertEquals(Integer.valueOf(id), team.getId());

		team = teamDAO.getTeam(id);
		assertEquals("Test", team.getName());
		assertEquals(Integer.valueOf(8), team.getRating());
		org = team.getOrganization();
		assertNotNull(org);
		assertEquals("Test Organization", org.getName());
	}

	@Test
	public void testUpdateTeam() {
		Organization newOrganization = new Organization();
		newOrganization.setName("Team 8 Organization");
		organizationDAO.addOrganization(newOrganization);
		assertNotNull(newOrganization.getId());

		Team newTeam = new Team();
		newTeam.setName("Test");
		newTeam.setRating(8);
		newTeam.setOrganization(newOrganization);

		int teamId = teamDAO.addTeam(newTeam);

		Team team = teamDAO.getTeam(teamId);
		team.setName("Updated");
		team.setRating(99);
		teamDAO.updateTeam(team);

		Team team2 = teamDAO.getTeam(teamId);
		assertEquals("Updated", team2.getName());
		assertEquals(Integer.valueOf(99), team2.getRating());
		Organization org = team2.getOrganization();
		assertNotNull(org);
		System.out.println("org.name");
		assertEquals("Team 8 Organization", org.getName());
		System.out.println("== testUpdateTeam end ==");
	}

	@Test
	public void testGetTeam() {
		Team newTeam = new Team();
		newTeam.setName("Test");
		newTeam.setRating(8);

		int teamId = teamDAO.addTeam(newTeam);

		Team team = teamDAO.getTeam(teamId);
		assertEquals("Test", team.getName());
		assertEquals(Integer.valueOf(8), team.getRating());
	}

	@Test
	public void testDeleteTeam() {
		Team newTeam = new Team();
		newTeam.setName("Test");
		newTeam.setRating(8);

		int teamId = teamDAO.addTeam(newTeam);

		teamDAO.deleteTeam(teamId);

		Team team = teamDAO.getTeam(teamId);
		assertNull(team);
	}

	@Test
	public void testGetTeams() {
		teamDAO.getTeams();
	}
}
