package com.sprhib.service;

import com.sprhib.dao.TeamDAO;
import com.sprhib.model.Team;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class TeamServiceImplTest {
	TeamDAO teamDAOMock;
	TeamServiceImpl teamService;

	@Before
	public void initService() {
		teamDAOMock = Mockito.mock(TeamDAO.class);
		teamService = new TeamServiceImpl();
		teamService.setTeamDAO(teamDAOMock);
	}

	@Test
	public void testAddTeam() {
		Team team = new Team();
		teamService.addTeam(team);
		verify(teamDAOMock, times(1)).addTeam(Matchers.same(team));
	}

	@Test
	public void testUpdateTeam() {
		Team team = new Team();
		teamService.updateTeam(team);
		verify(teamDAOMock, times(1)).updateTeam(Matchers.same(team));
	}

	@Test
	public void testGetTeam() {
		teamService.getTeam(2);
		verify(teamDAOMock, times(1)).getTeam(Matchers.eq(2));
	}

	@Test
	public void testDeleteTeam() {
		teamService.deleteTeam(7);
		verify(teamDAOMock, times(1)).deleteTeam(Matchers.eq(7));
	}

	@Test
	public void testGetTeams() {
		teamService.getTeams();
		verify(teamDAOMock, times(1)).getTeams();
	}

}
