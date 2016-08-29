package com.hcq.actions;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.MessageReply;
import com.hcq.bean.Users;
import com.hcq.biz.MessageReplyBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/")
@Scope(value="prototy")
public class MessageReplyAction extends BaseAction implements ModelDriven<MessageReply>{
	private static final long serialVersionUID = 5761476457561645942L;
	private MessageReply messageReply;
	private MessageReplyBiz messageReplyBiz;
	
	public MessageReply getModel() {
		messageReply=new MessageReply();
		return messageReply;
	}
	
	@Action(value="add_reply")
	public void addMessageReply() throws IOException{
		Users users = (Users) ActionContext.getContext().getSession().get("loginuser");
		messageReply.setRUid(users.getUid());
		messageReply.setRuimage(users.getUimage());
		messageReply.setRuname(users.getUname());
		messageReply.setRdatetime("a");
		messageReplyBiz.addMessageReply(messageReply);
		jsonModel.setCode(1);
		jsonModel.setObj(messageReply);
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	
	@Resource(name="messageReplyBizImpl")
	public void setMessageReplyBiz(MessageReplyBiz messageReplyBiz) {
		this.messageReplyBiz = messageReplyBiz;
	}
	

}
