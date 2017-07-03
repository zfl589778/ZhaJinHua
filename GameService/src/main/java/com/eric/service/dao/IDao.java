package com.eric.service.dao;

import java.util.List;
import java.util.Map;

public interface IDao<T> {

	void insert(T entity);
	
	void insertBatch(List<T> e_list);
	
	void update(T entity);
	
	void delete(String id);
	
	void delete(Map<String, Object> param);
	
	T get(String id);
	
	T get(Map<String, Object> param);
	
	List<T> findAll();
	
	List<T> find(Map<String, Object> param);
	
	Page<T> findByPage(Map<String, Object> param,int page,int size);
	
	Integer count();
	
	Integer count(Map<String, Object> param);
	
}
