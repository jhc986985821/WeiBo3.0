package com.hcq.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.Message;
import com.hcq.biz.MessageBiz;
import com.hcq.dao.Basedao;
import com.hcq.web.model.PageModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope(value = "prototy")
@Namespace("/")
public class PageAction extends BaseAction implements ModelDriven<PageModel>{

	private static final long serialVersionUID = 1L;

	private PageModel pageModel;
	private MessageBiz messageBiz;
	
	@Action(value="/message_search")
	public void searchHouse() throws IOException{
		Map<String, Object>session=ActionContext.getContext().getSession();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("currPage",pageModel.getCurrPage() );
		map.put("sizePage", pageModel.getSizePage());
		map.put("Uid",pageModel.getMessage().getUser().getUid());
		pageModel=messageBiz.searchPage(map);
		pageModel.setMessage(null);
		session.put("pageModel", pageModel);
		jsonModel.setCode(1);
		jsonModel.setObj(pageModel);
		
		super.outJson(jsonModel,ServletActionContext.getResponse());
	}
	
	public PageModel getModel() {
		pageModel =new PageModel();
		return pageModel;
	}
	
	@Resource(name = "messageBizImpl")
	public void setMessageBiz(MessageBiz messageBiz) {
		this.messageBiz = messageBiz;
	}

}
