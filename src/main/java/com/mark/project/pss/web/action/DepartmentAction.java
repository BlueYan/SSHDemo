package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.DepartmentQueryObject;
import com.mark.project.pss.service.IDepartmentService;
import com.mark.project.pss.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public class DepartmentAction extends BaseAction {

	@Setter
	private IDepartmentService departmentService;

	/**
	 * 给Department对象提供get和set方法
	 * 主要是拿到前台传递过来的department.sn和department.name参数然后创建Department对象再通过set方法将参数设置进去
	 * 使用OGNL技术
	 */
	@Setter
	@Getter
	private Department department = new Department();

	@Getter
	@Setter
	private DepartmentQueryObject dqo = new DepartmentQueryObject();

	@RequiredPermission("部门列表")
	public String list() throws Exception {
		//List<Department> departments = departmentService.list();
		PageResult<Department> pageResult = departmentService.pageQuery(dqo);
		ActionContext.getContext().put("pageResult", pageResult);
		//ActionContext.getContext().put("dept", departments); //将数据放到valueStack

		return "list";
	}

	/**
	 * 用于跳转到编辑页面上 一般不做任何操作
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("部门编辑")
	public String input() throws Exception {
		//查询当前的department对象
		if ( department.getId() != null ) {
			department = departmentService.get(department.getId());
		}
		return INPUT;
	}

	/**
	 * 保存或者更新页面
	 * 问题: 从编辑页面保存编辑无法提示无法找到/pss/department_saveOrUpdate.action
	 * 已解决：struts2.5版本与2.3版本的问题 通配符
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("部门新增/更新")
	public String saveOrUpdate() throws Exception {
		System.out.println(department);
		if ( department.getId() == null ) {
			//表示我们要进行保存
			departmentService.save(department);
		} else {
			//表示进行更新
			departmentService.update(department);
		}
		return SUCCESS;
	}

	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	@RequiredPermission("部门删除")
	public String delete()throws Exception {
		departmentService.delete(department);
		return SUCCESS;
	}

}
