package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.EmployeeQueryObject;
import com.mark.project.pss.service.IDepartmentService;
import com.mark.project.pss.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/11.
 *
 * 员工Action
 *
 */
public class EmployeeAction extends ActionSupport implements Preparable {

	@Setter
	private IEmployeeService employeeService;

	@Setter
	private IDepartmentService departmentService;

	/**
	 * 给Department对象提供get和set方法
	 * 主要是拿到前台传递过来的department.sn和department.name参数然后创建Department对象再通过set方法将参数设置进去
	 * 使用OGNL技术
	 */
	@Setter
	@Getter
	private Employee employee = new Employee();

	@Getter
	private EmployeeQueryObject qo = new EmployeeQueryObject();

	public String list() throws Exception {
		//List<Employee> employees = employeeService.query(qo);
		PageResult<Employee> pageResult = employeeService.pageQuery(qo);
		List<Department> departments = departmentService.list();
//		ActionContext.getContext().put("employees", employees); //将数据放到valueStack
		ActionContext.getContext().put("pageResult", pageResult);
		ActionContext.getContext().put("depts", departments);
		return "list";
	}

	/**
	 * 用于跳转到编辑页面上 一般不做任何操作
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception {
		//这里要查询出对应的所有部门信息
		List<Department> departments = departmentService.list();
		ActionContext.getContext().put("depts", departments);
		//查询当前的Employee对象
		if ( employee.getId() != null ) {
			employee = employeeService.get(employee.getId());
		}
		return INPUT;
	}

	/**
	 * 保存或者更新页面
	 * @return
	 * @throws Exception
	 */
	public String saveOrUpdate() throws Exception {
		System.out.println(employee);
		if ( employee.getId() == null ) {
			//表示我们要进行保存
			employeeService.save(employee);
		} else {
			//表示进行更新
			employeeService.update(employee);
		}
		return SUCCESS;
	}

	/**
	 * 删除操作
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception {
		employeeService.delete(employee);
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
		if ( employee.getId() != null ) {
			employee = employeeService.get(employee.getId());
		}
	}
}
