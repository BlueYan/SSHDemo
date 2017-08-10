package com.mark.project.pss.dao.impl;

import com.mark.project.pss.dao.IDepartmentDAO;
import com.mark.project.pss.domain.Department;
import lombok.Setter;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 * 采用Hibernate持久层技术
 */
public class DepartmentDAOImpl implements IDepartmentDAO {

	@Setter
	private SessionFactory sessionFactory;

	public void save(Department department) {
		sessionFactory.getCurrentSession().save(department);
	}

	public void update(Department department) {
		sessionFactory.getCurrentSession().update(department);
	}

	public void delete(Department department) {
		sessionFactory.getCurrentSession().delete(department);
	}

	public Department get(Long id) {
		return (Department) sessionFactory.getCurrentSession().get(Department.class, id);
	}

	public List<Department> list() {
		return sessionFactory.getCurrentSession().createQuery("SELECT dept FROM Department dept").list();
	}
}
