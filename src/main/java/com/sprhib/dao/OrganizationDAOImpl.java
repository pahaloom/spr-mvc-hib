package com.sprhib.dao;

import com.sprhib.model.Organization;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addOrganization(Organization organization) {
		getCurrentSession().save(organization);
		return organization.getId();
	}

	@Override
	public void updateOrganization(Organization organization) {
		Organization organizationToUpdate = getOrganization(organization.getId());
		organizationToUpdate.setName(organization.getName());
		getCurrentSession().update(organizationToUpdate);
	}

	@Override
	public Organization getOrganization(int id) {
		Organization organization = (Organization) getCurrentSession().get(Organization.class, id);
		return organization;
	}

	@Override
	public void deleteOrganization(int id) {
		Organization organization = getOrganization(id);
		if (organization != null)
			getCurrentSession().delete(organization);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizations() {
		return getCurrentSession().createQuery("from Organization").list();
	}
}
