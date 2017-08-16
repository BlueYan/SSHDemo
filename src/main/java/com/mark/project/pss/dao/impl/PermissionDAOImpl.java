package com.mark.project.pss.dao.impl;

import com.mark.project.pss.dao.IPermissionDAO;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.PermissionQueryObject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/14.
 */
public class PermissionDAOImpl extends BaseDAOImpl<Permission> implements IPermissionDAO {

	public PageResult<Permission> pageQuery(PermissionQueryObject pqo) {
		Session session = sessionFactory.getCurrentSession();
		//查询总条数
		Long count = (Long) session.createQuery("SELECT COUNT(obj) FROM Permission obj")
				.list().get(0);
		if ( count == 0 ) {
			return new PageResult<Permission>(new ArrayList<Permission>(), 0, pqo.getCurrentPage(), pqo.getPageSize());
		}
		//查询所有的数据
		Query query = session.createQuery("SELECT obj FROM Permission obj");
		List<Permission> permissions = query.setFirstResult((pqo.getCurrentPage() - 1) * pqo.getPageSize()).setMaxResults(pqo.getPageSize()).list();
		return new PageResult<Permission>(permissions, count.intValue(), pqo.getCurrentPage(), pqo.getPageSize());
	}

	/**
	 * 通过Permission_id来查询出对应的所有角色
	 * @param permission
	 * @return
	 */
	public List<Role> queryRoleByPId(Permission permission) {
		List<Role> roles = sessionFactory.getCurrentSession()
				.createQuery("SELECT obj FROM Role obj WHERE ? MEMBER OF obj.permissions")
				.setParameter(0, permission)
				.list();
		return roles;
	}

	/**
	 * 将持久化的状态转变成游离状态
	 * @param p
	 */
	public void evict(Permission p) {
		sessionFactory.getCurrentSession().evict(p);
	}
}
