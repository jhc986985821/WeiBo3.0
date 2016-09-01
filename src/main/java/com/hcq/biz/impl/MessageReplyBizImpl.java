package com.hcq.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.hcq.bean.MessageReply;
import com.hcq.biz.BaseBiz;
import com.hcq.biz.MessageReplyBiz;

@Service
public class MessageReplyBizImpl extends BaseBiz implements MessageReplyBiz{
	private static String MessageReply_COLLECTION = "MessageReplys";

	@Autowired  
    MongoOperations mongoTemplate; 
	
	public void addMessageReply(MessageReply messageReply) {
		mongoTemplate.save(messageReply,MessageReply_COLLECTION ); 
	}

	public List<MessageReply> findMessageReply(Integer mid) {
		return  mongoTemplate.find(new Query(Criteria.where("mid").is(mid)), MessageReply.class, MessageReply_COLLECTION);
	}
	
}
