package com.sprhib.dao;

import com.sprhib.init.BaseTestConfig;
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

	@Test
	public void testAddTeam() {
		Team team = new Team();
		team.setName("Test");
		team.setRating(8);

		teamDAO.addTeam(team);
	}

	@Test
	public void testUpdateTeam() {
		Team newTeam = new Team();
		newTeam.setName("Test");
		newTeam.setRating(8);

		int teamId = teamDAO.addTeam(newTeam);

		Team team = teamDAO.getTeam(teamId);
		team.setName("Updated");
		team.setRating(99);
		teamDAO.updateTeam(team);
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
