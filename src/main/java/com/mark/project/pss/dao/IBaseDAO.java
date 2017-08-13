package com.mark.project.pss.dao;

import com.mark.project.pss.domain.Employee;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/13.
 * 抽取公共的DAO操作
 *
 */
public interface IBaseDAO<T> {

	void save(T entity);

	void update(T entity);

	void delete(T entity);

	T get(Long id);

	List<T> list();

}
