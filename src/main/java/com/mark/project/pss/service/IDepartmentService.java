package com.mark.project.pss.service;

import com.mark.project.pss.domain.Department;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public interface IDepartmentService {

	void save(Department department);

	void update(Department department);

	void delete(Department department);

	Department get(Long id);

	List<Department> list();

}
