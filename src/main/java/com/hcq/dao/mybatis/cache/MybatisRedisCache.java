package com.hcq.dao.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;  
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;

import com.hcq.utils.SerializableUtil;

import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPool;  
import redis.clients.jedis.JedisPoolConfig;  

public class MybatisRedisCache implements Cache {
	
	 private static Logger logger = Logger.getLogger(MybatisRedisCache.class);  
	    private Jedis redisClient=createReids();  
	     /** The ReadWriteLock. */    
	    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();   
	      
	    private String id;  
	      
	    public MybatisRedisCache(final String id) {    
	        if (id == null) {  
	            throw new IllegalArgumentException("Cache instances require an ID");  
	        }  
	        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id="+id);  
	        this.id = id;  
	    }    
	    public String getId() {  
	        return this.id;  
	    }  
	  
	    public int getSize() {  
	     
	        return Integer.valueOf(redisClient.dbSize().toString());  
	    }  
	  
	    public void putObject(Object key, Object value) {  
	        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>putObject:"+key+"="+value);  
	        redisClient.set(SerializableUtil.serialize(key.toString()), SerializableUtil.serialize(value));  
	    }  
	  
	    public Object getObject(Object key) {  
	        Object value = SerializableUtil.unserizlize(redisClient.get(SerializableUtil.serialize(key.toString())));  
	        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>getObject:"+key+"="+value);  
	        return value;  
	    }  
	  
	    public Object removeObject(Object key) {  
	        return redisClient.expire(SerializableUtil.serialize(key.toString()),0);  
	    }  
	  
	    public void clear() {  
	          redisClient.flushDB();  
	    }  
	    public ReadWriteLock getReadWriteLock() {  
	        return readWriteLock;  
	    }  
	    protected  static Jedis createReids(){  
	        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");  
	        return pool.getResource();  
	    }  

}
