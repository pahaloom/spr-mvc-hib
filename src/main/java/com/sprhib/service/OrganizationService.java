package com.sprhib.service;

import com.sprhib.model.Organization;
import java.util.List;


public interface OrganizationService {
	
	public void addOrganization(Organization organization);
	public void updateOrganization(Organization organization);
	public Organization getOrganization(int id);
	public void deleteOrganization(int id);
	public List<Organization> getOrganizations();

}
