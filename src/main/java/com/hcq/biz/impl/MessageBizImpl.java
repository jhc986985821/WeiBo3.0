package com.hcq.biz.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcq.bean.Message;
import com.hcq.bean.Users;
import com.hcq.biz.BaseBiz;
import com.hcq.biz.MessageBiz;

@Service
public class MessageBizImpl extends BaseBiz implements MessageBiz {
	
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.REQUIRED)
	public void addMessage(Message message) {
		basedao.save(message,"addMessage");
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.REQUIRED)
	public void delMessage(Integer mid) {
		basedao.del(Message.class, mid, "delMessage");
	}
	
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.REQUIRED)
	public void updateMessage(Message message) {
		basedao.update(message, "updateMessage");
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.NOT_SUPPORTED)
	public List<Message> selectMessageByUid(Integer uid) {
		return (List<Message>)basedao.findListById(Message.class, uid, "selectMessageByUid");
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.NOT_SUPPORTED)
	public Message selectMessageByMid(Integer mid) {
		return (Message)basedao.findById(Message.class, mid, "selectMessageByMid");
	}

	public Integer selectNewMessageCount(Integer uid) {
		return (Integer) basedao.findById(Message.class, uid, "selectNewMessageCount");
	}

}
