package com.mark.project.pss.dao.impl;

import com.mark.project.pss.dao.IEmployeeDAO;
import com.mark.project.pss.domain.Employee;
import lombok.Setter;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/11.
 */
public class EmployeeDAOImpl implements IEmployeeDAO {

	@Setter
	private SessionFactory sessionFactory;

	public void save(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
	}

	public void update(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
	}

	public void delete(Employee employee) {
		sessionFactory.getCurrentSession().delete(employee);
	}

	public Employee get(Long id) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}

	public List<Employee> list() {
		return sessionFactory.getCurrentSession().createQuery("SELECT obj FROM Employee obj").list();
	}
}
