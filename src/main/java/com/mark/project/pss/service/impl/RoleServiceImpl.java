package com.mark.project.pss.service.impl;

import com.mark.project.pss.dao.IRoleDAO;
import com.mark.project.pss.domain.Role;
import com.mark.project.pss.service.IRoleService;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/15.
 */
public class RoleServiceImpl implements IRoleService {

	@Setter
	private IRoleDAO roleDAO;

	public void save(Role role) {
		roleDAO.save(role);
	}

	public void update(Role role) {
		roleDAO.update(role);
	}

	public void delete(Role role) {
		roleDAO.delete(role);
	}

	public Role get(Long id) {
		return roleDAO.get(id);
	}

	public List<Role> list() {
		return roleDAO.list();
	}
}
