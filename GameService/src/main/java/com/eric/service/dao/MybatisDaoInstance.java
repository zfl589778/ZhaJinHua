package com.eric.service.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.eric.components.BeanFactoryUtil;

@SuppressWarnings("rawtypes")
public class MybatisDaoInstance<T> implements IMybatisDao<T>{
	
	private static final String INSERT = ".insert";
	private static final String INSERT_BATCH = ".insertBatch";
	private static final String UPDATE = ".update";
	private static final String DELETE_BY_ID = ".deleteById";
	private static final String DELETE = ".delete";
	private static final String GET_BY_ID = ".getById";
	private static final String GET = ".get";
	private static final String LIST = ".list";
	private static final String COUNT = "Count";
	
	private static Class clazz;
	private static SqlSessionTemplate sqlSessionTemplate;
	private static MybatisDaoInstance instance = new MybatisDaoInstance();
	private MybatisDaoInstance(){
		if(sqlSessionTemplate==null){
			sqlSessionTemplate = BeanFactoryUtil.getBean(SqlSessionTemplate.class);
		}
	}
	
	public static MybatisDaoInstance getInstance(Class clz){
		clazz = clz;
		return instance;
	}
	
	@Override
	public void insert(T entity) {
		insert(entity.getClass().getSimpleName()+INSERT, entity);
	}
	
	@Override
	public void insert(String sqlName, T entity) {
		sqlSessionTemplate.insert(sqlName, entity);
	}
	
	@Override
	public void insertBatch(List<T> e_list) {
		insertBatch(clazz.getSimpleName()+INSERT_BATCH, e_list);
	}

	@Override
	public void insertBatch(String sqlName, List<T> e_list) {
		sqlSessionTemplate.insert(sqlName, e_list);
	}

	@Override
	public void update(T entity) {
		update(entity.getClass().getSimpleName()+UPDATE, entity);
	}

	@Override
	public void update(String sqlName, T entity) {
		sqlSessionTemplate.update(sqlName, entity);
	}
	
	@Override
	public void update(String sqlName) {
		update(sqlName,null);
	}

	@Override
	public void delete(String id) {
		delete(clazz.getSimpleName()+DELETE_BY_ID,id);
	}

	@Override
	public void delete(Map<String, Object> param) {
		delete(clazz.getSimpleName()+DELETE,param);
	}
	
	@Override
	public void delete(String sqlName, String id) {
		sqlSessionTemplate.delete(sqlName, id);
	}
	
	@Override
	public void delete(String sqlName, Map<String, Object> param) {
		sqlSessionTemplate.delete(sqlName, param);
	}
	
	@Override
	public T get(String id) {
		return get(clazz.getSimpleName()+GET_BY_ID,id);
	}

	@Override
	public T get(Map<String, Object> param) {
		return get(clazz.getSimpleName()+GET,param);
	}
	
	@Override
	public T get(String sqlName, String id) {
		return sqlSessionTemplate.selectOne(sqlName, id);
	}
	
	@Override
	public T get(String sqlName, Map<String, Object> param) {
		List<T> r_list = sqlSessionTemplate.selectList(sqlName, param);
		if(r_list.isEmpty())return null;
		return r_list.get(0);
	}
	
	@Override
	public List<T> find(String sqlName, Map<String, Object> param) {
		return sqlSessionTemplate.selectList(sqlName, param);
	}
	
	@Override
	public List<T> findAll() {
		return find(null);
	}

	@Override
	public List<T> find(Map<String, Object> param) {
		return sqlSessionTemplate.selectList(clazz.getSimpleName()+LIST,param);
	}

	@Override
	public Page<T> findByPage(Map<String, Object> param, int page, int size) {
		return findByPage(clazz.getSimpleName()+LIST,param,page,size);
	}
	
	@Override
	public Page<T> findByPage(String sqlName, Map<String, Object> param,int page, int size) {
		if(page<1)page = 1;
		if(size<0)size = 0;
		int p = (page-1)*size;
		param.put("page", p);
		param.put("size", size);
		List<T> list = sqlSessionTemplate.selectList(sqlName, param);
		Integer totalCount = count(sqlName+COUNT,param);
		Page<T> pageList = new Page<T>(list, page, size, totalCount);
		return pageList;
	}

	@Override
	public Integer count() {
		return count(clazz.getSimpleName()+LIST+COUNT,null);
	}
	
	@Override
	public Integer count(Map<String, Object> param) {
		return count(clazz.getSimpleName()+LIST+COUNT,param);
	}

	@Override
	public Integer count(String sqlName, Map<String, Object> param) {
		return sqlSessionTemplate.selectOne(sqlName,param);
	}

	@Override
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

}
