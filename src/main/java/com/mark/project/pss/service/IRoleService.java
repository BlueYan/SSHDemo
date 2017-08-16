package com.mark.project.pss.service;

import com.mark.project.pss.domain.Employee;
import com.mark.project.pss.domain.Role;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/15.
 */
public interface IRoleService {

	void save(Role role);

	void update(Role role);

	void delete(Role role);

	Role get(Long id);

	List<Role> list();

}
