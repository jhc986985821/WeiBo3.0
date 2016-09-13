package com.hcq.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hcq.bean.Message;
import com.hcq.bean.Users;
import com.hcq.biz.BaseBiz;
import com.hcq.biz.MessageBiz;
import com.hcq.web.model.PageModel;

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
	public List<Message> selectAllMessageByUid(Integer uid) {
		return (List<Message>)basedao.findListById(Message.class, uid, "selectAllMessageByUid");
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.NOT_SUPPORTED)
	public Message selectMessageByMid(Integer mid) {
		return (Message)basedao.findById(Message.class, mid, "selectMessageByMid");
	}

	public Integer selectNewMessageCount(Integer uid) {
		return (Integer) basedao.findById(Message.class, uid, "selectNewMessageCount");
	}

	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.REQUIRED)
	public void resendMessage(Message message) {
		basedao.save(message,"reSendMessage");
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.NOT_SUPPORTED)
	public List<Message> selectMyMessageByUid(Integer uid) {
		return (List<Message>)basedao.findListById(Message.class, uid, "selectMyMessageByUid");
	}

	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,noRollbackForClassName={"java.lang.RuntimeException"},propagation=Propagation.NOT_SUPPORTED)
	public List<Message> selectAllMessage() {
		return (List<Message>)basedao.findAll(Message.class, "selectAllMessage");
	}

	public PageModel getPageModelBean(PageModel pm) {
		int count =basedao.getCount(Message.class, "selectCount");
		int total = count%pm.getSizePage() ==0?count/pm.getSizePage():count/pm.getSizePage()+1;
		pm.setTotal(total);
		int offset=(pm.getCurrPage()-1)*pm.getSizePage();
		List<Message> list=basedao.findList(Message.class, null,"getMessage",offset, pm.getSizePage());
		pm.setList(list);
		return pm;
	}

	
	@Transactional(readOnly=true,isolation=Isolation.DEFAULT,rollbackForClassName={"java.lang.RuntimeExceptipon"},propagation=Propagation.NOT_SUPPORTED)
	public PageModel searchPage(Map<String, Object> map) {
		PageModel pageModel =new PageModel();
		int currPage =Integer.parseInt(map.get("currPage").toString());
		int sizePage = Integer.parseInt(map.get("sizePage").toString());
		int count =basedao.getCount(Message.class, map,"findMessageConditionCount");
		pageModel.setTotalRecord(count);
		int total = count%sizePage ==0?count/sizePage:count/sizePage+1;
		pageModel.setTotal(total);
		int offset=(currPage-1)*sizePage;
		List<Message>messages=basedao.findList(Message.class, map, "findMessageCondition", offset, sizePage);
		pageModel.setList(messages);
		pageModel.setSizePage(sizePage);
		pageModel.setCurrPage(currPage);
		return pageModel;
	}
}
