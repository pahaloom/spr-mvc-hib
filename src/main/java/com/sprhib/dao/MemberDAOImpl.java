package com.sprhib.dao;

import com.sprhib.model.Member;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int addMember(Member member) {
		getCurrentSession().save(member);
		return member.getId();
	}

	@Override
	public void updateMember(Member member) {
		Member memberToUpdate = getMember(member.getId());
		memberToUpdate.setName(member.getName());
		memberToUpdate.setTeams(member.getTeams());
		getCurrentSession().update(memberToUpdate);
	}

	@Override
	public Member getMember(int id) {
		Member member = (Member) getCurrentSession().get(Member.class, id);
		return member;
	}

	@Override
	public void deleteMember(int id) {
		Member member = getMember(id);
		if (member != null)
			getCurrentSession().delete(member);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Member> getMembers() {
		return getCurrentSession().createQuery("from Member").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Member> getByOrganization(int organizationId) {
		return getCurrentSession().createQuery("SELECT m FROM Member m JOIN m.teams t WHERE t.organization.id=:organizationId")
				.setInteger("organizationId", organizationId).list();
	}
}
