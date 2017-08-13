package com.mark.project.pss.dao.impl;

import com.mark.project.pss.dao.IBaseDAO;
import lombok.Setter;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/13.
 *
 * 实现公共的DAO操作
 */
public class BaseDAOImpl<T> implements IBaseDAO<T> {

	@Setter
	protected SessionFactory sessionFactory;

	private Class<T> clz;

	/**
	 * 提供构造器的原因是为了
	 * 获取到调用者的父类的具体泛型
	 * 在我们调用get和list方法使用
	 */
	public BaseDAOImpl() {

		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public T get(Long id) {
		return (T) sessionFactory.getCurrentSession().get(clz, id);
	}

	public List<T> list() {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT obj FROM " + clz.getSimpleName() + " obj").list();
	}
}
