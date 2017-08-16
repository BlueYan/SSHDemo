package com.mark.project.pss.service.impl;

import com.mark.project.pss.dao.IEmployeeDAO;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.EmployeeQueryObject;
import com.mark.project.pss.service.IEmployeeService;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mark_Yan on 2017/8/11.
 */
public class EmployeeServiceImpl implements IEmployeeService {

	@Setter
	private IEmployeeDAO employeeDAO;

	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	public void update(Employee employee) {
		employeeDAO.update(employee);
	}

	public void delete(Employee employee) {
		employeeDAO.delete(employee);
	}

	public Employee get(Long id) {
		return employeeDAO.get(id);
	}

	public List<Employee> list() {
		return employeeDAO.list();
	}

	public List<Employee> query(EmployeeQueryObject qo) {
		return employeeDAO.query(qo);
	}

	public PageResult<Employee> pageQuery(EmployeeQueryObject qo) {
		return employeeDAO.pageQuery(qo);
	}

	public Employee queryForLogin(String username, String password) {
		return employeeDAO.queryForLogin(username, password);
	}

	/**
	 * 查询出用户对应的权限
	 * @param user
	 * @return
	 */
	public Set<Permission> queryForPermission(Employee user) {
		List<Role> roles = user.getRoles();
		Set<Permission> permissions = new HashSet<Permission>();
		for(Role role : roles ) {
			for( Permission permission : role.getPermissions() ) {
				permissions.add(permission);
			}
		}
		return permissions;
	}
}
