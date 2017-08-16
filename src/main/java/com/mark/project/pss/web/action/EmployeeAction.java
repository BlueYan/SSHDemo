package com.mark.project.pss.web.action;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.EmployeeQueryObject;
import com.mark.project.pss.service.IDepartmentService;
import com.mark.project.pss.service.IEmployeeService;
import com.mark.project.pss.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import lombok.Getter;
import lombok.Setter;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/11.
 *
 * 员工Action
 *
 */
public class EmployeeAction extends BaseAction implements Preparable {

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

	@RequiredPermission("员工列表")
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
	@RequiredPermission("员工编辑")
	public String input() throws Exception {
		//查询出所有的角色信息
		List<Role> roles = roleService.list();
		ActionContext.getContext().put("roles", roles);
		for( Role role : roles ) {
			System.out.println(role);
		}
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
	@RequiredPermission("员工新增/更新")
	public String saveOrUpdate() throws Exception {
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
	@RequiredPermission("员工删除")
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
			/**
			 * 设置部门为null是为了解决我们在编辑员工信息修改其部门信息的时候报错。
			 * 首先param拦截器会对我们的employee对象进行参数的注入
			 * 然后判断getId不等null，查询出了employee对象(持久化状态)
			 * 在操作saveOrUpdate之前我们在这里设置了dept为null后(游离状态)
			 * struts的param拦截器会重新帮我们进行注入参数(为了使得employee的数据恢复原来一样)
			 * 当我们进行saveOrUpdate操作的时候，hibernate判断出我们传递进去的employee对象
			 * 是一个游离状态，所有会进行更新。
			 *
			 *-------------------------------------------
			 * 至于为什么我们这里不用跟RoleAction的prepareSaveOrUpdate一样也去设置
			 * employee.getRoles().clear(); 然后设置了setDept(null)也可来解决添加角色数据重复的问题。
			 * 首先param拦截器会对employee进行参数注入。
			 * employee=Employee{id=1, name=Mark, dept=0x123, roles=[...], ...}
			 * Role(id=1, name=null, sn=null)
			 * Role(id=5, name=null, sn=null)
			 * 然后再前台点击了保存按钮后我们会先在这里设置dept为null。此时的employee
			 * 然后param又对employee重新注入，此时的role集合会重复添加数据.(此时的roles就有重复数据)
				 Role(id=1, name=null, sn=null)
				 Role(id=5, name=null, sn=null)
				 Role(id=1, name=null, sn=null)
				 Role(id=5, name=null, sn=null)
			   不懂。无法理解。曹尼玛 撸代码卧槽。
			 *
			 */
			employee.setDept(null);
		}
		employee.getRoles().clear();
	}
}
