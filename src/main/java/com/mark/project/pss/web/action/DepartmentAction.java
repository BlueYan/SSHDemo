package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.service.IDepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public class DepartmentAction extends ActionSupport {

	@Setter
	private IDepartmentService departmentService;

	/**
	 * 给Department对象提供get和set方法
	 * 主要是拿到前台传递过来的department.sn和department.name参数然后创建Department对象再通过set方法将参数设置进去
	 * 使用OGNL技术
	 */
	@Setter
	@Getter
	private Department department;

	public String list() throws Exception {
		List<Department> departments = departmentService.list();
		ActionContext.getContext().put("dept", departments); //将数据放到valueStack
		return "LIST";
	}

	/**
	 * 用于跳转到编辑页面上 一般不做任何操作
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		return INPUT;
	}

	/**
	 * 保存或者更新页面
	 * TODO: 从编辑页面保存编辑无法提示无法找到/pss/department_saveOrUpdate.action
	 * @return
	 * @throws Exception
	 */
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

}
