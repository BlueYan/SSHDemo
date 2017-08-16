package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.RoleQueryObject;
import com.mark.project.pss.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/15.
 */
public class RoleAction extends BaseAction implements Preparable {

	@Setter
	@Getter
	private Role role = new Role();

	@Getter
	private RoleQueryObject qo = new RoleQueryObject();

	@RequiredPermission("角色列表")
	public String list() throws Exception {
		List<Role> roles = roleService.list();
		ActionContext.getContext().put("roles", roles);
		return "list";
	}

	/**
	 * 用于跳转到编辑页面上 一般不做任何操作
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("角色编辑")
	public String input() throws Exception {
		//查询出所有的权限列表
		List<Permission> permissions = permissionService.list();
		ActionContext.getContext().put("permissions", permissions);
		if ( role.getId() != null ) {
			role = roleService.get(role.getId());
		}
		return INPUT;
	}

	/**
	 * 保存或者更新页面
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("角色新增/更新")
	public String saveOrUpdate() throws Exception {
		if ( role.getId() == null ) {
			//表示我们要进行保存
			roleService.save(role);
		} else {
			//表示进行更新
			roleService.update(role);
		}
		return SUCCESS;
	}

	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("角色删除")
	public String delete()throws Exception {
		roleService.delete(role);
		return SUCCESS;
	}

	/**
	 * This method is called to allow the action to prepare itself.
	 * 主要是为了解决参数丢失问题.Action类要实现prepare接口
	 * 但是为了不让所有的方法都经过该prepare处理,我们要针对某个方法进行处理.
	 *
	 * @throws Exception thrown if a system level exception occurs.
	 */
	public void prepare() throws Exception {}

	/**
	 * 需要处理的函数定义一个prepare+函数名的方法
	 * @throws Exception
	 */
	public void prepareSaveOrUpdate() throws Exception {
		//再我们保存或者更新数据之前,我们先根据id去查询出对应的实体对象,然后将其复制给当前的全局实体对象
		if ( role.getId() != null ) {
			role = roleService.get(role.getId());
		}
		role.getPermissions().clear(); //解决给角色添加权限时候权限数据重复问题
	}

}
