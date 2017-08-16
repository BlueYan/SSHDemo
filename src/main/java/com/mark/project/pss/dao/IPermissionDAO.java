package com.mark.project.pss.dao;

import com.mark.project.pss.dao.impl.BaseDAOImpl;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.PermissionQueryObject;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/14.
 */
public interface IPermissionDAO extends IBaseDAO<Permission> {

	PageResult<Permission> pageQuery(PermissionQueryObject pqo);

	List<Role> queryRoleByPId(Permission permission);

	void evict(Permission p);
}
