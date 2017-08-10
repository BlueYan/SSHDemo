package com.mark.project.pss.service.impl;

import com.mark.project.pss.dao.IDepartmentDAO;
import com.mark.project.pss.domain.Department;
import com.mark.project.pss.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public class DepartmentServiceImpl implements IDepartmentService {

	@Setter
	private IDepartmentDAO departmentDAO;

	public void save(Department department) {
		departmentDAO.save(department);
	}

	public void update(Department department) {
		departmentDAO.update(department);
	}

	public void delete(Department department) {
		departmentDAO.delete(department);
	}

	public Department get(Long id) {
		return departmentDAO.get(id);
	}

	public List<Department> list() {
		return departmentDAO.list();
	}
}
