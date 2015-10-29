package com.sprhib.dao;

import com.sprhib.model.Member;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sprhib.model.Team;

@Repository
public class TeamDAOImpl implements TeamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public int addTeam(Team team) {
		getCurrentSession().save(team);
		return team.getId();
	}

	public void updateTeam(Team team) {
		Team teamToUpdate = getTeam(team.getId());
		teamToUpdate.setName(team.getName());
		teamToUpdate.setRating(team.getRating());
		teamToUpdate.setOrganization(team.getOrganization());
		getCurrentSession().update(teamToUpdate);
		
	}

	public Team getTeam(int id) {
		Team team = (Team) getCurrentSession().get(Team.class, id);
		return team;
	}

	public void deleteTeam(int id) {
		Team team = getTeam(id);
		if (team != null) {
			Session currentSession = getCurrentSession();
			List<Member> members = team.getMembers();
			if (members != null) {
				for (Member m : members) {
					m.getTeams().remove(team);
					currentSession.update(m);
				}
			}
			currentSession.delete(team);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Team> getTeams() {
		return getCurrentSession().createQuery("from Team").list();
	}

}
