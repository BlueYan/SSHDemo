package com.mark.dao;

import com.mark.domain.User;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/9.
 * UserDao层
 */
public interface IUserDAO {

	void save(User user);

	/**
	 * 使用Hibernate 我们需要传递实体类进来
	 * @param user
	 */
	void delete(User user);


	void update(User user);

	User get(Long id);

	List<User> list();

}
