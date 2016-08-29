package com.hcq.biz;

import java.util.List;

import com.hcq.bean.MessageReply;

public interface MessageReplyBiz {
	public void addMessageReply(MessageReply messageReply);
	
	public List<MessageReply>findMessageReply(Integer mid);
	
}
