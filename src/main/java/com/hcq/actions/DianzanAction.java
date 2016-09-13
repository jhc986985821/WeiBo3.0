package com.hcq.actions;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.Dianzan;
import com.hcq.biz.DianzanBiz;
import com.hcq.dao.mybatis.cache.RedisCache;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope(value="prototy")
@Namespace("/")
public class DianzanAction extends BaseAction implements ModelDriven<Dianzan>{

	private static final long serialVersionUID = -4937128686971254216L;
	
	private DianzanBiz dianzanBiz;
	private Dianzan dianzan;

	@Action(value = "dian_zan")
	public void dianzan() throws IOException {
		try {
			if(dianzanBiz.hashExist("user_topic."+dianzan.getMid(), ""+dianzan.getUid())){
				jsonModel.setCode(0);
				jsonModel.setMsg("用户已经点过赞");
			}else{
				dianzanBiz.putHashOne("user_topic."+dianzan.getMid(), ""+dianzan.getUid(), dianzan.getUid());
				dianzanBiz.putHashOne("topic." + dianzan.getMid(), "PraiseCnt", 1l);
				jsonModel.setCode(1);
			}
			
			
		} catch (Exception e) {
			jsonModel.setCode(0);
			jsonModel.setMsg("error"+e);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	public Dianzan getModel() {
		dianzan = new Dianzan();
		return dianzan;
	}

	@Resource(name="dianzanBizImpl")
	public void setDianzanBiz(DianzanBiz dianzanBiz) {
		this.dianzanBiz = dianzanBiz;
	}
	
	
}
