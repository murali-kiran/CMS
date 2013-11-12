package com.sumadga.dao;

import java.util.List;



public interface GenericDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public T update(T entity);
	public List<T> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount);
	public List<T> findAll(final int... rowStartIdxAndCount);
}
