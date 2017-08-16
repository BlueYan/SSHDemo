package com.mark.project.pss.dao.impl;

import com.mark.project.pss.dao.IEmployeeDAO;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.EmployeeQueryObject;
import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/11.
 */
public class EmployeeDAOImpl extends BaseDAOImpl<Employee> implements IEmployeeDAO {

	public List<Employee> query(EmployeeQueryObject qo) {
		String hql = "SELECT obj FROM Employee obj" + qo.getQuery();
		System.out.println("hql = " + hql + " size = " + qo.getParams().size());
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for ( int i = 0, len = qo.getParams().size(); i < len; i++ ) {
			query.setParameter(i, qo.getParams().get(i));
		}
		return query.list();
	}

	/**
	 * 分页加高级查询
	 * @param qo
	 * @return
	 */
	public PageResult<Employee> pageQuery(EmployeeQueryObject qo) {
		Session session = sessionFactory.getCurrentSession();
		String queryStr = qo.getQuery(); //解决重复调用
		//先查询出中条数
		Long count = (Long) session.createQuery("SELECT COUNT(obj) FROM Employee obj" + queryStr).list().get(0);
		if (count == 0) {
			return new PageResult<Employee>(new ArrayList<Employee>(), 0, 0, 0);
		}
		//查询出数据
		Query query  = session.createQuery("SELECT obj FROM Employee obj" + queryStr);
		for ( int i = 0; i < qo.getParams().size(); i++ ) {
			query.setParameter(i, qo.getParams().get(i));
		}
		query.setFirstResult((qo.getCurrentPage() - 1) * qo.getPageSize()).setMaxResults(qo.getPageSize());
		List<Employee> data = query.list();
		PageResult<Employee> employeePageResult = new PageResult<Employee>(data, count.intValue(), qo.getCurrentPage(), qo.getPageSize());
		return employeePageResult;
	}

	public Employee queryForLogin(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Employee user = (Employee) session.createQuery("SELECT obj FROM Employee obj WHERE obj.name = ? AND obj.password = ?")
				.setParameter(0, username).setParameter(1, password).uniqueResult();
		return user;
	}
}
