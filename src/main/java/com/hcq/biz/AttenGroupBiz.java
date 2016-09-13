package com.hcq.biz;

import java.util.List;

import com.hcq.bean.AttenGroup;

public interface AttenGroupBiz {
	public List findUidQueue(Integer uid); 
	
	public List findUid(Integer uider); 
	
	public List selectHotPeople();
	
	public boolean addAtten(AttenGroup attenGroup);
	

	public List findMyUid(Integer uider); 
}
