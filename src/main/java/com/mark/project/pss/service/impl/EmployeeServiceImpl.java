package com.mark.project.pss.service.impl;

import com.mark.project.pss.dao.IEmployeeDAO;
import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.service.IEmployeeService;
import lombok.Setter;

import java.util.List;

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
}
