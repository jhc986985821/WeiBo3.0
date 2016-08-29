package com.hcq.dao.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.hcq.dao.redis.RedisPool;
import com.hcq.utils.SerializableUtil;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache{
	
	private static Logger logger = LogManager.getLogger(RedisCache.class);
	private String id;
	private Jedis redisClient=createRedis();  //����һ��jedis����
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	
	
	public void setReadWriteLock(ReadWriteLock readWriteLock) {
		this.readWriteLock = readWriteLock;
	}

	public RedisCache(String id) {
		if(id==null){
			throw new IllegalArgumentException("Cache instance requires an ID");
		}
		logger.debug("create an cache instance with id"+id);
		this.id=id;
	}

	public String getId() {
		return this.id;
	}

	/**�����ӳ���ȡ
	 * @return
	 */
	private  static Jedis createRedis() {
		Jedis jedis =RedisPool.getPool().getResource();
		return jedis;
	}

	public void putObject(Object key, Object value) {
		byte[] keybyte=SerializableUtil.serialize(key);
		byte[]valuebyte=SerializableUtil.serialize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	public Object getObject(Object key) {
		//���洩͸
		
		byte[] values=this.redisClient.get(SerializableUtil.serialize(key));
		//�㷨������һ��ʱ����û�����еļ���������   key->value
		if(values==null){
			return null;
		}
		Object obj =SerializableUtil.unserizlize(values);
		return obj;
	}

	public Object removeObject(Object key) {
		byte[]keybyte=SerializableUtil.serialize(key);
		return this.redisClient.expire(keybyte, 0);
	}

	public void clear() {
		this.redisClient.flushDB();
	}

	public int getSize() {
		Long size = this.redisClient.dbSize();
		int s =Integer.valueOf(size+"");
		return s;
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
