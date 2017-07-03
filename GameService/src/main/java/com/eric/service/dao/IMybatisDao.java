package com.eric.service.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public interface IMybatisDao<T> extends IDao<T>{

	void insert(String sqlName,T entity);
	
	void insertBatch(String sqlName,List<T> e_list);
	
	void update(String sqlName,T entity);
	
	void update(String sqlName);
	
	void delete(String sqlName,String id);
	
	void delete(String sqlName,Map<String, Object> param);
	
	T get(String sqlName,String id);
	
	T get(String sqlName,Map<String, Object> param);
	
	List<T> find(String sqlName,Map<String, Object> param);
	
	Page<T> findByPage(String sqlName,Map<String, Object> param,int page,int size);
	
	Integer count(String sqlName,Map<String, Object> param);
	
	SqlSessionTemplate getSqlSessionTemplate();
}
