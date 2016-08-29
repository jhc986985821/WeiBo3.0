package com.hcq.biz;

import javax.annotation.Resource;

import com.hcq.dao.Basedao;
import com.mysql.jdbc.Blob;

public abstract class BaseBiz {
	protected Basedao basedao;
	
	@Resource(name="baseDaoImpl")
	public void setBaseDao(Basedao basedao){
		this.basedao=basedao;
	}
}
