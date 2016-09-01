package com.hcq.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.JMSException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hcq.bean.Message;
import com.hcq.bean.MessageReply;
import com.hcq.bean.Users;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.MessageBiz;
import com.hcq.biz.MessageReplyBiz;
import com.hcq.biz.UsersBiz;
import com.hcq.mq.queue.TopicSender;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototy")
@Namespace("/")
public class MessageAction extends BaseAction implements ModelDriven<Message> {
	private static final long serialVersionUID = 5834501724483588458L;
	private Message message;
	private MessageBiz messageBiz;
	private AttenGroupBiz attenGroupBiz;
	private TopicSender topicSender;
	private UsersBiz usersBiz;
	private MessageReplyBiz messageReplyBiz;

	@Action(value = "message_add")
	public void AddMessage() throws IOException {
		try {
			messageBiz.addMessage(message);
			topicSender.send(message.toString());
			Users users = usersBiz.getUser(message.getUser().getUid());
			message.setUser(users);
			jsonModel.setCode(1);
			jsonModel.setObj(message);
		} catch (Exception e) {
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "NewMessageCount")
	public void selectNewMessageCount() throws IOException {
		int count = messageBiz.selectNewMessageCount(1001);
		jsonModel.setCode(1);
		jsonModel.setObj(count);

		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "selectMyMessageByUid")
	public void selectMyMessageByUid() throws IOException, JMSException {
		// 查本人微博
		List<Message> list = messageBiz.selectMyMessageByUid(message.getUser().getUid());

		// 查本人
		// Users users = usersBiz.getUser(message.getUser().getUid());

		// 查好友
		List<String> uidlist = attenGroupBiz.findUidQueue(message.getUser().getUid());
		List<Users> userslist = new ArrayList<Users>();

		for (String uid : uidlist) {
			Users u = usersBiz.getUser(Integer.valueOf(uid));
			userslist.add(u);
		}

		jsonModel.setUserslist(userslist);
		jsonModel.setCode(1);
		jsonModel.setObj(list);

		super.outJson(jsonModel, ServletActionContext.getResponse());
	}

	@Action(value = "selectAllByUid")
	public void selectAllMessageByUid() throws IOException, JMSException {
		try {
			List<String> uidlist = attenGroupBiz.findUidQueue(message.getUser().getUid());
			//查本人
			Users users = usersBiz.getUser(message.getUser().getUid());
			
			List<Users> userslist = new ArrayList<Users>();
			//查好友
			for (String uid : uidlist) {
				Users u = usersBiz.getUser(Integer.valueOf(uid));
				userslist.add(u);
			}
			
			uidlist.add(String.valueOf(message.getUser().getUid()));
			
			//查所有微博及回复
			
			List<Message> mList = messageBiz.selectAllMessageByUid(message.getUser().getUid());
			for(Message message:mList){
				List<MessageReply>messageReplies =messageReplyBiz.findMessageReply(message.getMid());
				message.setMessageReply(messageReplies);
			}
			
			jsonModel.setCode(1);
			jsonModel.setObj(mList);
			jsonModel.setUsers(users);
			jsonModel.setUserslist(userslist);
		} catch (NullPointerException e) {
			jsonModel.setCode(0);
			jsonModel.setMsg("空！");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	
	@Action(value="delByMid")
	public void delByMid() throws IOException{
		messageBiz.delMessage(message.getMid());
		jsonModel.setCode(1);
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value="selectFriendByUid")
	public void selectAllFriendByUid() throws IOException, JMSException{
		try{
		List<String> uidlist=attenGroupBiz.findUidQueue(message.getUser().getUid());
		Users users = usersBiz.getUser(message.getUser().getUid());
		List<Users> userslist = new ArrayList<Users>();
		
		for(String uid:uidlist){
			Users u=usersBiz.getUser(Integer.valueOf(uid));
			userslist.add(u);
		}
		
		jsonModel.setCode(1);
		jsonModel.setUsers(users);
		jsonModel.setUserslist(userslist);
		}catch(NullPointerException e){
			jsonModel.setCode(0);
			jsonModel.setMsg("空！");
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	
	@Action(value="user_retransmission")
	public void AddreMessage() throws IOException{
		try{
			messageBiz.resendMessage(message);
			jsonModel.setCode(1);
			jsonModel.setObj(message);
		}catch(Exception e){
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	/*@Action(value = "/dianzan")
	public void dianzan() throws IOException {
		int mid = message.getMid();
	//	System.out.println(mid);
		try {
			RedisClient redisClient = new RedisClient();
			redisClient.adddianzan(mid);
			jsonModel.setCode(1);
		} catch (Exception e) {
			jsonModel.setMsg("error"+e);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	
	@Action(value = "/find_dianzan")
	public void findDianzan() throws IOException {
		int mid = message.getMid();
	//	System.out.println(mid);
		try {
			RedisClient redisClient = new RedisClient();
			String m = redisClient.finddianzan(mid);
			jsonModel.setCode(1);
			jsonModel.setObj(m);
		} catch (Exception e) {
			jsonModel.setMsg("error"+e);
		}
		super.outJson(jsonModel, ServletActionContext.getResponse());
	}
	*/

	public Message getModel() {
		message = new Message();
		return message;
	}

	@Resource(name = "attenGroupBizImpl")
	public void setAttenGroupBiz(AttenGroupBiz attenGroupBiz) {
		this.attenGroupBiz = attenGroupBiz;
	}

	@Resource(name = "messageBizImpl")
	public void setMessageBiz(MessageBiz messageBiz) {
		this.messageBiz = messageBiz;
	}

	@Resource(name = "topicSender")
	public void setTopicSender(TopicSender topicSender) {
		this.topicSender = topicSender;
	}

	@Resource(name = "usersBizImpl")
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}

	@Resource(name = "messageReplyBizImpl")
	public void setMessageReplyBiz(MessageReplyBiz messageReplyBiz) {
		this.messageReplyBiz = messageReplyBiz;
	}
	
}
