package com.mark.project.pss.service;

import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.PermissionQueryObject;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/14.
 */
public interface IPermissionService {

	void save(Permission permission);

	void delete(Permission permission);

	PageResult<Permission> pageQuery(PermissionQueryObject pqo);

	List<Permission> list();

	void reload();

}
