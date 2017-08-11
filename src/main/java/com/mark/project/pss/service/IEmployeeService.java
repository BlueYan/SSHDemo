package com.mark.project.pss.service;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.domain.Employee;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public interface IEmployeeService {

	void save(Employee employee);

	void update(Employee employee);

	void delete(Employee employee);

	Employee get(Long id);

	List<Employee> list();

}
