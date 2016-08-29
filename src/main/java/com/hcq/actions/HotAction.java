package com.hcq.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.UsersBiz;

@Controller
@Namespace("/")
@Scope(value="prototy")
public class HotAction extends BaseAction{
	private static final long serialVersionUID = 1667646829068644308L;
	private AttenGroupBiz attenGroupBiz;
	
	@Action()
	
	@Resource(name="attenGroupBizImpl")
	public void setAttenGroupBiz(AttenGroupBiz attenGroupBiz) {
		this.attenGroupBiz = attenGroupBiz;
	}
	
	
}
