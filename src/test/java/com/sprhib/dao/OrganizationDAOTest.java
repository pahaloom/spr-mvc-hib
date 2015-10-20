package com.sprhib.dao;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Organization;
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
public class OrganizationDAOTest {
	@Autowired
	OrganizationDAO organizationDAO;
	
	@Test
	public void testAddOrganization() {
		Organization organization = new Organization();
		organization.setName("Test");
		organizationDAO.addOrganization(organization);
	}

	@Test
	public void testUpdateOrganization() {
		Organization newOrg = new Organization();
		newOrg.setName("Test");

		int orgId = organizationDAO.addOrganization(newOrg);
		Organization org = organizationDAO.getOrganization(orgId);
		org.setName("Updated");
		organizationDAO.updateOrganization(org);
	}

	@Test
	public void testGetOrganization() {
		Organization newOrg = new Organization();
		newOrg.setName("Test");
		int orgId = organizationDAO.addOrganization(newOrg);

		Organization org = organizationDAO.getOrganization(orgId);
		assertEquals("Test", org.getName());
	}

	@Test
	public void testDeleteOrganization() {
		Organization newOrg = new Organization();
		newOrg.setName("Test");
		int orgId = organizationDAO.addOrganization(newOrg);

		organizationDAO.deleteOrganization(orgId);

		Organization org = organizationDAO.getOrganization(orgId);
		assertNull(org);
	}

	@Test
	public void testGetOrganizations() {
		organizationDAO.getOrganizations();
	}
}
