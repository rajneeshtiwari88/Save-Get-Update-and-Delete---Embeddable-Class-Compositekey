package com.hibernate.config;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RepositoryService {
	public static void main(String[] args) {
		RepositoryService repositoryService = new RepositoryService();
		Session session = repositoryService.getSession();
		ModelClassEmployee mce = repositoryService.setValue();
		repositoryService.save(session, mce);
		List<ModelClassEmployee> meList = repositoryService.getData(session);
		System.out.println(meList.get(0).getAge());
		mce.setAge(28);;
		repositoryService.updateData(session, mce);
		meList = repositoryService.getData(session);
		System.out.println(meList.get(0).getAge());
		repositoryService.deleteData(session, mce.getId());
	}

	public Session getSession() {
		SessionFactory sf = new Configuration().configure("hibernate.xml").buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}

	public ModelClassEmployee setValue() {
		ModelClassEmployee employee = new ModelClassEmployee();

		EmployeeId eid = new EmployeeId();
		eid.setId("1");
		eid.setEmpName("Hibernate");

		employee.setId(eid);
		employee.setAge(23);
		employee.setSalary(new BigDecimal(20000));
		return employee;
	}

	public void save(Session session, ModelClassEmployee mce) {
		Transaction tx = session.beginTransaction();
		session.save(mce);
		tx.commit();

	}

	private List<ModelClassEmployee> getData(Session session) {
		Criteria criteria = session.createCriteria(ModelClassEmployee.class);
		return criteria.list();
	}

	private void updateData(Session session, ModelClassEmployee modelEmployee) {
		Transaction tx = session.beginTransaction();
		try {
			session.update(modelEmployee);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Unable to update due to : " + e);
		}
	}

	private void deleteData(Session session, EmployeeId id) {
		try {
			Transaction tx = session.beginTransaction();
			ModelClassEmployee me = session.find(ModelClassEmployee.class, id);
			session.delete(me);
			tx.commit();
			System.out.println("data deleted with id :" + id);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (session != null)
				session.close();
		}
	}
}
