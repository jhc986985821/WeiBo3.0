package com.hcq.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcq.bean.AttenGroup;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.BaseBiz;

@Service
public class AttenGroupBizImpl extends BaseBiz implements AttenGroupBiz{

	public List findUidQueue(Integer uid) {
		return basedao.findListById(AttenGroup.class, uid, "findUidQueue");
	}
	

}