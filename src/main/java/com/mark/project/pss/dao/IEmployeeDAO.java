package com.mark.project.pss.dao;

import com.mark.project.pss.domain.Employee;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/11.
 */
public interface IEmployeeDAO {

	void save(Employee employee);

	void update(Employee employee);

	void delete(Employee employee);

	Employee get(Long id);

	List<Employee> list();


}
