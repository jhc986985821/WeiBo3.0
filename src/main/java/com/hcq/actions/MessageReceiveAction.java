package com.hcq.actions;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hcq.bean.Message;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.UsersBiz;
import com.hcq.mq.queue.TopicReceiver2;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/")
@Scope(value = "prototy")
public class MessageReceiveAction extends BaseAction implements ModelDriven<Message>{
	private static final long serialVersionUID = -8206243665022541195L;
	private Message message;
	private AttenGroupBiz attenGroupBiz;
	private TopicReceiver2 topicReceiver2;
	
	@Action(value="messageReceive")
	public void MessageReceive(){
		List<String> uidlist = attenGroupBiz.findUidQueue(message.getUser().getUid());
		String messagestring =topicReceiver2.getMessageString();
		Message message = (Message) JSONObject.stringToValue(messagestring);
		System.out.println(message.getUser().getUid());
	}
	
	public Message getModel() {
		message =new Message();
		return message;
	}

	@Resource(name="attenGroupBizImpl")
	public void setAttenGroupBiz(AttenGroupBiz attenGroupBiz) {
		this.attenGroupBiz = attenGroupBiz;
	}
	
	 public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		  Object pojo;
		  pojo = JSONObject.stringToValue(jsonString);
		  return pojo;
	 }

	@Resource(name="topicReceiver2")
	public void setTopicReceiver2(TopicReceiver2 topicReceiver2) {
		this.topicReceiver2 = topicReceiver2;
	}
}
