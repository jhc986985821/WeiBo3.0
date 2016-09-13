package com.hcq.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.hcq.dao.RedisDao;
import com.hcq.utils.SerializableUtil;

@Repository(value="redisDaoImpl")
public class RedisDaoImpl implements RedisDao{
	private static RedisTemplate<String, Object> redisTemplate;
	
	public void putObject(final String key, Object value) {
		final byte[] vbytes = SerializableUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				return null;
			}
		});
	}

	
	


	public void putHashOne(final String key,final String field, final long value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.hIncrBy(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(field), value);
				return null;
			}
		});
	}
	
	public void putHashString(final String key,final String field, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.hSet(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(field), redisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}
	
	public void incr(final String key,final String field) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.hIncrBy(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(field), 1l);
				System.out.println("成功");
				return null;
			}
		});
	}

	public void reduce(final String key,final String field) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.hIncrBy(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(field), -1l);

				return null;
			}
		});
	}

	public String getHashOne(final String key, final String field) {
		
		Object obj =redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException { 
			//	System.out.println(connection.hMGet(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(field)));
			//	return connection.hMGet(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(field));
			//	System.out.println(mobile);
			//	System.out.println(connection.hGetAll(redisTemplate.getStringSerializer().serialize(key)));
			//	System.out.println(redisTemplate.getHashValueSerializer());
				
				List<byte[]> value = connection.hMGet(redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(field));
				String m =  redisTemplate.getStringSerializer().deserialize(value.get(0));
				System.out.println(m);
				System.out.println(key);
				return m;
			}
			
		});
		return (String) obj;
	}


	public Map<String, String> getHash(final String key) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				Map<byte[], byte[]> value = connection.hGetAll(redisTemplate.getStringSerializer().serialize(key));
				String m =  redisTemplate.getStringSerializer().deserialize(value.get(0));
				return m;
			}
		});
		return null;
	}

	
	
	
	@Resource(name="redisTemplate")
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}


	public boolean hashExist(final String key, final String field) {
		 return redisTemplate.execute(new RedisCallback() {
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	            	return connection.hExists(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(field));
	            }
	        });
	}

	public Long getHashlen(final String key) {
		 return redisTemplate.execute(new RedisCallback() {
	            public Long doInRedis(RedisConnection connection) throws DataAccessException {
	            	return connection.hLen(redisTemplate.getStringSerializer().serialize(key)); 
	            }
	        });
	}

	public List<String> getHashField(final String key) {
		 return redisTemplate.execute(new RedisCallback() {
	            public List<String>  doInRedis(RedisConnection connection) throws DataAccessException {
	            	Set<byte[]> fileds =   connection.hKeys(redisTemplate.getStringSerializer().serialize(key));
	            	List<String> filedstr = new ArrayList<String>();
	            	for (byte[] bs : fileds) {
	            		String str =  redisTemplate.getStringSerializer().deserialize(bs);
	            		filedstr.add(str);
	            	}
	            	return filedstr;
	            }
	        });
	}

	public List<String> getHashValue(final String key) {
		 return redisTemplate.execute(new RedisCallback() {
	            public List<String>  doInRedis(RedisConnection connection) throws DataAccessException {
	            	List<byte[]> fileds =   connection.hVals(redisTemplate.getStringSerializer().serialize(key));
	            	List<String> filedstr = new ArrayList<String>();
	            	for (byte[] bs : fileds) {
	            		String str =  redisTemplate.getStringSerializer().deserialize(bs);
	            		filedstr.add(str);
	            	}
	            	return filedstr;
	            }
	        });
	}
	
	public void putList(final String key,final String value){
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.lPush(redisTemplate.getStringSerializer().serialize(key),redisTemplate.getStringSerializer().serialize(value) );
				return null;
			}
		});
	}
	
	public void putHashZset(final String key,final double score, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.zAdd(redisTemplate.getStringSerializer().serialize(key), score, redisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}
	
	public void inrHashZset(final String key,final double score, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.zIncrBy(redisTemplate.getStringSerializer().serialize(key), 1l, redisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}
	
	//取前10位
	public List<String> HashzRevRange(final String key) {
		return redisTemplate.execute(new RedisCallback() {
            public List<String>  doInRedis(RedisConnection connection) 
            		throws DataAccessException {
				Set<byte[]> fileds = connection.zRevRange(redisTemplate.getStringSerializer().serialize(key), 0, 10);
				List<String> filedstr = new ArrayList<String>();
				
            	for (byte[] bs : fileds) {
            		String str =  redisTemplate.getStringSerializer().deserialize(bs);
            		filedstr.add(str);
            	}	
				return filedstr;
			}
		});
	}
	
	public List<String> HashzRangeByScore(final String key) {
		return redisTemplate.execute(new RedisCallback() {
            public List<String>  doInRedis(RedisConnection connection) 
            		throws DataAccessException {
				Set<byte[]> fileds = connection.zRangeByScore(redisTemplate.getStringSerializer().serialize(key), 0, 10);
				List<String> filedstr = new ArrayList<String>();
				System.out.println(fileds);
            	for (byte[] bs : fileds) {
            		String str =  redisTemplate.getStringSerializer().deserialize(bs);
            		System.out.println("a" + bs);
            		System.out.println("a" + str);
            		filedstr.add(str);
            	}	
				return filedstr;
			}
		});
	}
	
	
}
