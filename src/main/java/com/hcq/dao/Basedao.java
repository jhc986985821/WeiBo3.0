package com.hcq.dao;

import java.util.List;
import java.util.Map;

public interface Basedao<T>{
	
	/**
	 * @param t     Ҫ��������ݶ���
	 * @param sqlId      mapper�еķ�����
	 * "com.hcq.dao.mapper." +t.getClass.getSimpleName + "Mapper."+ sqlId
	 *com.hcq.dao.mapper.AccountMapper.update
	 */
	public void save(T t,String sqlId);
	
	public void update(T t,String sqlId);
	
	public void del(Class<T>clazz,int id,String sqlId);
	
	public void del(Class<T>t,List<Integer>ids,String sqlId);
	/**����������ҳ��ѯ
	 * @param clazz
	 * @param map   ����   ��Ϊ�ֶ���   ֵ ����ֵ
	 * @param sqlId    mapper����ķ����� 
	 * @param offset    �ڼ�ҳ
	 * @param sizePage    ÿҳ����
	 * @return
	 */
	public List<T> findList(Class<T>clazz,Map<String, Object>map,String sqlId,int offset,int sizePage);
	
	public T find(T t,String sqlId);
	
	public List<T> findAll(Class<T>clazz,String sqlId);
	
	/**�ۺϲ�ѯ
	 * @param clazz
	 * @param sqlId
	 * @return
	 */
	public int getCount(Class<T>clazz,String sqlId);
	
	public T findById(Class<T>t,int id, String sqlid);
	
	public List<T> findListById(Class<T>t,int id, String sqlid);
	/**���������ۺϲ�ѯ
	 * @param clazz
	 * @param map
	 * @param sqlId
	 * @return
	 */
	public int getCount(Class<T>clazz,Map<String, Object>map,String sqlId);
}