package com.mark.service;

import com.mark.domain.User;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/9.
 */
public interface IUserService {

	void save(User user);

	void delete(User user);

	void update(User user);

	User get(Long id);

	List<User> list();

}
