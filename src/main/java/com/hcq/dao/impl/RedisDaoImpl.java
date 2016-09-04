package com.hcq.dao.impl;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.hcq.dao.RedisDao;
import com.hcq.dao.redis.SerializeUtil;

@Repository(value="redisDaoImpl")
public class RedisDaoImpl implements RedisDao{
	private static RedisTemplate<String, Object> redisTemplate;
	
	public void putObject(final String key, Object value) {
		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				return null;
			}
		});
	}

	
	//@Resource(name="redisTemplate")
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	
}
