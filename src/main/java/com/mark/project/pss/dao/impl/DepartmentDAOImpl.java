package com.mark.project.pss.dao.impl;

import com.mark.project.pss.dao.IDepartmentDAO;
import com.mark.project.pss.domain.Department;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.DepartmentQueryObject;
import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 * 采用Hibernate持久层技术
 */
public class DepartmentDAOImpl extends BaseDAOImpl<Department> implements IDepartmentDAO {

	public PageResult<Department> pageQuery(DepartmentQueryObject dqo) {
		//查询出总条数
		Session session = sessionFactory.getCurrentSession();
		Long count = (Long) session.createQuery("SELECT COUNT(obj) FROM Department obj").list().get(0);
		if ( count == 0 ) {
			return new PageResult<Department>(new ArrayList<Department>(), 0, dqo.getCurrentPage(), dqo.getPageSize());
		}
		//查询所有的数据
		Query query = session.createQuery("SELECT obj FROM Department obj");
		query.setFirstResult((dqo.getCurrentPage() - 1) * dqo.getPageSize()).setMaxResults(dqo.getPageSize());
		List<Department> departments = query.list();
		return new PageResult<Department>(departments, count.intValue(), dqo.getCurrentPage(), dqo.getPageSize());
	}
}
