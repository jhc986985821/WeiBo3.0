package com.hcq.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hcq.biz.DianzanBiz;
import com.hcq.biz.RedisBiz;
import com.hcq.dao.RedisDao;

@Service
public class DianzanBizImpl extends RedisBiz implements DianzanBiz{
	

	public void putObject(String key, Object value) {
		redisDao.putObject(key, value);
	}

	public void putHashOne(String key, String field, long value) {
		redisDao.putHashOne(key, field, value);
	}

	public String getHashOne(String key, String field) {
		String m = redisDao.getHashOne(key, field);
		return m;
	}

	public Map<String, String> getHash(String key) {
		redisDao.getHash(key);
		return null;
	}

	public void incr(String key, String field) {
		redisDao.incr(key, field);
	}

	public void reduce(String key, String field) {
		redisDao.reduce(key, field);
	}

	public boolean hashExist(String key, String field) {	
		return redisDao.hashExist(key, field);
	}

	public void putHashString(String key, String field, String value) {
		redisDao.putHashString(key, field, value);
		
	}

	public Long getHashlen(String key) {	
		return redisDao.getHashlen(key);
	}

	public List<String> getHashField(String key) {	
		return redisDao.getHashField(key);
	}
	
	public List<String> getHashValue(String key) {	
		return redisDao.getHashValue(key);
	}

	public void putList(String key, String value) {
		redisDao.putList(key, value);
	}
	
	public void putHashZset(String key,double score,String value){
		redisDao.putHashZset(key, score, value);
	}

	public List<String> HashzRevRange(final String key){
		return redisDao.HashzRevRange(key);
	}
	
	public List<String> HashzRangeByScore(final String key){
		return redisDao.HashzRangeByScore(key);
	}
	
	public void inrHashZset(final String key,final double score, final String value){
		redisDao.inrHashZset(key, score, value);
	}
}
