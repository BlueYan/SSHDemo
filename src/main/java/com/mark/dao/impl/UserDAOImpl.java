package com.mark.dao.impl;

import com.mark.dao.IUserDAO;
import com.mark.domain.User;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/9.
 *
 * User DAO实现类
 */
public class UserDAOImpl implements IUserDAO {

	//自动注入SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	/**
	 * 使用Hibernate 我们需要传递实体类进来
	 *
	 * @param user
	 */
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	public User get(Long id) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}

	public List<User> list() {
		List<User> users = sessionFactory.getCurrentSession().createQuery("SELECT user FROM User user").list();
		return users;
	}
}
