package com.yhw.service;

import java.util.List;

public interface IBaseService<T> {
	
	public T insert(T entity);
	
	public T update(T entity);
	
	public List<T> findUndeletedRecordsByProperty(String property, Object value);
	
	public T findUndeletedRecordByProperty(String property, Object value);
	
	public T findRecordByProperty(String property, Object value);
	
	public List<T> findRecordsByProperty(String property, Object value);
	
	public List<T> findAllRecords();
}