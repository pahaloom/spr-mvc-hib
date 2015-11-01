package com.sprhib.service;

import com.sprhib.dao.MemberDAO;
import com.sprhib.dao.OrganizationDAO;
import com.sprhib.dao.TeamDAO;
import com.sprhib.model.Member;
import com.sprhib.model.Organization;
import com.sprhib.model.Team;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationDAO organizationDAO;

	@Autowired
	MemberDAO memberDAO;

	/* setter for testing */
	protected void setOrganizationDAO(OrganizationDAO organizationDAO) {
		this.organizationDAO = organizationDAO;
	}

	/* setter for testing */
	protected void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void addOrganization(Organization organization) {
		organizationDAO.addOrganization(organization);
	}

	@Override
	public void updateOrganization(Organization organization) {
		organizationDAO.updateOrganization(organization);
	}

	@Override
	public Organization getOrganization(int id) {
		return organizationDAO.getOrganization(id);
	}

	@Override
	public void deleteOrganization(int id) {
		Organization organization = organizationDAO.getOrganization(id);
		if (organization != null) {
			List<Team> teams = organization.getTeams();
			if (teams != null && !teams.isEmpty()){
				List<Member> organizationMembers = memberDAO.getByOrganization(organization.getId());
				if (organizationMembers != null && !organizationMembers.isEmpty()) {
					for (Member member : organizationMembers) {
						List memberTeams = member.getTeams();
						if (memberTeams != null && !memberTeams.isEmpty()) {
							if (memberTeams.removeAll(teams)) {
								memberDAO.updateMember(member);
							}
						}
					}
				}
			}
			organizationDAO.deleteOrganization(id);
		}
	}

	@Override
	public List<Organization> getOrganizations() {
		return organizationDAO.getOrganizations();
	}
}
