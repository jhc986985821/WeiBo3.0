package com.hcq.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hcq.dao.Basedao;

@Repository(value="baseDaoImpl")
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements Basedao<T> {
	private final String MAPPERPATH = "com.hcq.dao.mapper.";
	
	public void save(T t, String sqlId) {
		super.getSqlSession().insert(MAPPERPATH+t.getClass().getSimpleName()+"Mapper."+sqlId,t);
	}

	public void update(T t, String sqlId) {
		super.getSqlSession().insert(MAPPERPATH+t.getClass().getSimpleName()+"Mapper."+sqlId,t);
	}

	public void del(Class<T> t, int id, String sqlId) {
		super.getSqlSession().delete(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlId,id);
	}

	public List<T> findList(Class<T> t, Map<String, Object> map, String sqlId, int offset, int sizePage) {
		List<T>ts=null;
		ts=super.getSqlSession().selectList(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlId,map,new RowBounds(offset,sizePage));
		return ts;
	}
	
	public T findById(Class<T>t,int id, String sqlid){
		T o=null;
		o=super.getSqlSession().selectOne(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlid,id);
		return o;
	}
	
	public List<T> findListById(Class<T>t,int id, String sqlid){
		List<T> o=null;
		o=super.getSqlSession().selectList(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlid,id);
		return o;
	}
	
	public T find(T t, String sqlId) {
		t= super.getSqlSession().selectOne(MAPPERPATH+t.getClass().getSimpleName()+"Mapper."+sqlId,t);
		return t;
	}

	public List<T> findAll(Class<T> t, String sqlId) {
		List<T>ts =null;
		ts=super.getSqlSession().selectList(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlId,t);
		return ts;
	}

	public int getCount(Class<T> t, String sqlId) {
		int count=0;
		count = super.getSqlSession().selectOne(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlId);
		return count;
	}

	public int getCount(Class<T> t, Map<String, Object> map, String sqlId) {
		int count =0;
		count = super.getSqlSession().selectOne(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlId,map);
		return count;
	}
	
	@Resource(name="sqlSession")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlsessionTemplate){
		super.setSqlSessionTemplate(sqlsessionTemplate);
	}

	public void del(Class<T> t, List<Integer> ids, String sqlId) {
		super.getSqlSession().delete(MAPPERPATH+t.getSimpleName()+"Mapper."+sqlId,ids);
	}
}
