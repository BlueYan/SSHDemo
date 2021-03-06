package com.mark.project.pss.service;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.domain.Permission;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.EmployeeQueryObject;

import java.util.List;
import java.util.Set;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public interface IEmployeeService {

	void save(Employee employee);

	void update(Employee employee);

	void delete(Employee employee);

	Employee get(Long id);

	List<Employee> list();

	List<Employee> query(EmployeeQueryObject qo);

	PageResult<Employee> pageQuery(EmployeeQueryObject qo);

	Employee queryForLogin(String username, String password);

	Set<Permission> queryForPermission(Employee user);

}
