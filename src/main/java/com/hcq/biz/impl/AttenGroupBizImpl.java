package com.hcq.biz.impl;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hcq.bean.AttenGroup;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.BaseBiz;

@Service
public class AttenGroupBizImpl extends BaseBiz implements AttenGroupBiz{
	private RedisTemplate<String, Object> redisTemplate;
	
	public List findUidQueue(Integer uid) {
		return basedao.findListById(AttenGroup.class, uid, "findUidQueue");
	}

	public List findUid(Integer uider) {
		return basedao.findListById(AttenGroup.class, uider, "findUid");
	}
	
}
