package com.mark.service.impl;

import com.mark.dao.IUserDAO;
import com.mark.domain.User;
import com.mark.service.IUserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/9.
 */
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	public void save(User user) {
		userDAO.save(user);
	}

	public void delete(User user) {
		userDAO.delete(user);
	}

	public void update(User user) {
		userDAO.update(user);
	}

	public User get(Long id) {
		return userDAO.get(id);
	}

	public List<User> list() {
		return userDAO.list();
	}



}
