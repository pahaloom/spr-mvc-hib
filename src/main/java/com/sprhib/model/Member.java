package com.sprhib.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="members")
public class Member {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;

	@ManyToMany()
	@JoinTable(name = "members_teams",
			joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
	private List<Team> teams;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
