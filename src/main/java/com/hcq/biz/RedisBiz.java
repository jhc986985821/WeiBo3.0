package com.hcq.biz;

import javax.annotation.Resource;

import com.hcq.dao.Basedao;
import com.hcq.dao.RedisDao;

public abstract class RedisBiz {
	protected RedisDao redisDao;

	@Resource(name="redisDaoImpl")
	public void setRedisDao(RedisDao redisDao) {
		this.redisDao = redisDao;
	}
	
}
