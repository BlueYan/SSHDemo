package com.mark.project.pss.dao;

import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.EmployeeQueryObject;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/11.
 */
public interface IEmployeeDAO extends IBaseDAO<Employee> {

	List<Employee> query(EmployeeQueryObject qo);

	//分页高级查询
	PageResult<Employee> pageQuery(EmployeeQueryObject qo);

}
