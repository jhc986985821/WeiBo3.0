package com.hcq.biz;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DianzanBiz {

	public void putObject(String key, Object value);
	
	public void putHashOne(String key, String field, long value);
	
	public void putHashString(String key, String field, String value);
	
	public String getHashOne(String key, String field);
	
	public Map<String, String> getHash(String key);
	
	public void incr(String key,String field) ;
	
	public void reduce(String key,String field) ;
	
	public boolean hashExist(String key,String field);
	
	public Long getHashlen(String key);

	public List<String> getHashField(String key);
	
	public List<String> getHashValue(String key);
	
	public void putList(String key,String value);
	
	public void putHashZset(String key,double score,String value);
	
	public List<String> HashzRevRange(String key);
	
	public List<String> HashzRangeByScore(String key);
	
	public void inrHashZset(String key,double score, String value);
}
