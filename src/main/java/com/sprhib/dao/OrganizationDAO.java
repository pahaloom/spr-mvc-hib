package com.sprhib.dao;

import java.util.List;

import com.sprhib.model.Organization;

public interface OrganizationDAO {
	
	public int addOrganization(Organization organization);
	public void updateOrganization(Organization organization);
	public Organization getOrganization(int id);
	public void deleteOrganization(int id);
	public List<Organization> getOrganizations();

}
