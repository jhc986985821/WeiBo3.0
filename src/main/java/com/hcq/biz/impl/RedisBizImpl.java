package com.hcq.biz.impl;

import org.springframework.stereotype.Service;

import com.hcq.biz.RedisBiz;
import com.hcq.biz.RedisTestBiz;

@Service
public class RedisBizImpl extends RedisBiz implements RedisTestBiz{

	public void putObject(String key, Object value) {
		redisDao.putObject(key, value);
	}

}
