package com.mark.project.pss.dao;

import com.mark.project.pss.domain.Department;
import com.mark.project.pss.page.PageResult;
import com.mark.project.pss.query.DepartmentQueryObject;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
public interface IDepartmentDAO extends IBaseDAO<Department> {

	PageResult<Department> pageQuery(DepartmentQueryObject dqo);

}
