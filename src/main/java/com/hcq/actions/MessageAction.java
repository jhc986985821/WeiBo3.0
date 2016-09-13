package com.hcq.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.hcq.biz.DianzanBiz;
import com.hcq.biz.MessageBiz;
import com.hcq.biz.MessageReplyBiz;
import com.hcq.biz.UsersBiz;
import com.hcq.utils.LuceneUtil;
import com.hcq.web.model.HotWord;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value = "prototy")
@Namespace("/")
public class MessageAction extends BaseAction implements ModelDriven<Message> {
	private static final long serialVersionUID = 5834501724483588458L;
	private Message message;
	private MessageBiz messageBiz;
	private AttenGroupBiz attenGroupBiz;
	private UsersBiz usersBiz;
	private MessageReplyBiz messageReplyBiz;
	private DianzanBiz dianzanBiz;

	@Action(value = "message_add")
	public void AddMessage() throws IOException {
		try {
			messageBiz.addMessage(message);
			Users users = usersBiz.getUser(message.getUser().getUid());
			message.setUser(users);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			dianzanBiz.putHashOne("message_topic."+ message.getUser().getUid(), "Num",1l);
			//redis 添加今日发布条数
			dianzanBiz.putHashOne("message_jinri_" + df.format(new Date()) , "Uid_" + message.getUser().getUid() ,1l);
			dianzanBiz.getHashOne("message_jinri_" + df.format(new Date()) , "Uid_" + message.getUser().getUid());
			
			jsonModel.setCode(1);
			jsonModel.setObj(message);
		} catch (Exception e) {
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
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
				if(dianzanBiz.hashExist("topic."+message.getMid(), "PraiseCnt")){
					String praiseCnt = dianzanBiz.getHashOne(
							"topic." + message.getMid(), "PraiseCnt");
					//System.out.println("点赞数"+praiseCnt);
					message.setPraiseCnt(Integer.parseInt(praiseCnt));
				}else{
					message.setPraiseCnt(0);
				}
			}
			
			//查网络热词
			LuceneUtil luceneUtil = new LuceneUtil();
			List<HotWord>hotWords=luceneUtil.Hotword();
			
			
			jsonModel.setHotWords(hotWords);
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


	@Resource(name = "usersBizImpl")
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}

	@Resource(name = "messageReplyBizImpl")
	public void setMessageReplyBiz(MessageReplyBiz messageReplyBiz) {
		this.messageReplyBiz = messageReplyBiz;
	}
	
	@Resource(name = "dianzanBizImpl")
	public void setDianzanBiz(DianzanBiz dianzanBiz) {
		this.dianzanBiz = dianzanBiz;
	}
	
}
