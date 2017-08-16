package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.PermissionQueryObject;
import com.mark.project.pss.service.IPermissionService;
import com.mark.project.pss.service.impl.PermissionServiceImpl;
import com.mark.project.pss.util.CommonUtil;
import com.mark.project.pss.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.rngom.parse.host.Base;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Mark_Yan on 2017/8/14.
 */
public class PermissionAction extends BaseAction {

	@Setter
	@Getter
	private PermissionQueryObject qo = new PermissionQueryObject();

	@Setter
	@Getter
	private Permission permission = new Permission();

	@RequiredPermission("权限列表")
	public String list() throws Exception {
		PageResult<Permission> pageResult = permissionService.pageQuery(qo);
		ActionContext.getContext().put("pageResult", pageResult);
		return "list";
	}

	/**
	 * 加载所有的权限
	 * 获取所有的Action,然后筛选出贴有RequiredPermission注解的方法,保存到数据库中
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("权限加载")
	public String reload() throws Exception {
		permissionService.reload();
		return NONE;
	}

	@RequiredPermission("权限删除")
	public String delete() throws Exception {
		permissionService.delete(permission);
		return SUCCESS;
	}
}
