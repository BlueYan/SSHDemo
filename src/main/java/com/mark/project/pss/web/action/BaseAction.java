package com.mark.project.pss.web.action;

import com.mark.project.pss.service.IDepartmentService;
import com.mark.project.pss.service.IEmployeeService;
import com.mark.project.pss.service.IPermissionService;
import com.mark.project.pss.service.IRoleService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/8/14.
 */
public class BaseAction extends ActionSupport {

	@Setter
	protected IEmployeeService employeeService;

	@Setter
	protected IDepartmentService departmentService;

	@Setter
	protected IPermissionService permissionService;

	@Setter
	protected IRoleService roleService;

}
