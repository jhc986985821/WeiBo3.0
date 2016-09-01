package com.hcq.biz;


import java.util.List;

import com.hcq.bean.Message;
import com.hcq.bean.Users;

public interface MessageBiz {
	
	public void addMessage(Message message);
	
	public void delMessage(Integer mid);
	
	public void updateMessage(Message message);
	
	public List<Message> selectAllMessageByUid(Integer uid);
	
	public Message selectMessageByMid(Integer mid);
	
	public Integer selectNewMessageCount(Integer uid);

	public void resendMessage(Message message);

	public List<Message> selectMyMessageByUid(Integer uid);
	
}
