package com.sprhib.service;

import com.sprhib.dao.OrganizationDAO;
import com.sprhib.model.Organization;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class OrganizationServiceImplTest {
	OrganizationDAO organizationDAOMock;
	OrganizationServiceImpl organizationService;

	@Before
	public void initService() {
		organizationDAOMock = Mockito.mock(OrganizationDAO.class);
		organizationService = new OrganizationServiceImpl();
		organizationService.setOrganizationDAO(organizationDAOMock);
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
		organizationService.deleteOrganization(7);
		verify(organizationDAOMock, times(1)).deleteOrganization(Matchers.eq(7));
	}

	@Test
	public void testGetOrganizations() {
		organizationService.getOrganizations();
		verify(organizationDAOMock, times(1)).getOrganizations();
	}

}
