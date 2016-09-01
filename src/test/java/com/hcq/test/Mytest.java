package com.hcq.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hcq.bean.Message;
import com.hcq.bean.MessageReply;
import com.hcq.bean.Users;
import com.hcq.biz.AttenGroupBiz;
import com.hcq.biz.MessageBiz;
import com.hcq.biz.MessageReplyBiz;
import com.hcq.biz.RedisBiz;
import com.hcq.biz.RedisTestBiz;
import com.hcq.biz.UsersBiz;
import com.hcq.dao.impl.RedisDaoImpl;
import com.hcq.dao.mybatis.cache.MyRedisCache;
import com.hcq.dao.mybatis.cache.RedisCache;

import junit.framework.TestCase;

public class Mytest extends TestCase {
	
	public void testdelMessage(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		MessageBiz messageBiz = (MessageBiz) context.getBean("messageBizImpl");
		messageBiz.delMessage(4);
	}
	
	public void updatelMessage(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		MessageBiz messageBiz = (MessageBiz) context.getBean("messageBizImpl");
		Message message=new Message(2,"b");
		messageBiz.updateMessage(message);
	}
	
	public void selectlMessage(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		MessageBiz messageBiz = (MessageBiz) context.getBean("messageBizImpl");
		Message message =messageBiz.selectMessageByMid(1001);
		System.out.println(message);
	}
	
	
	public void addMessageReply(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		MessageReplyBiz messageReplyBiz = (MessageReplyBiz) context.getBean("messageReplyBizImpl");
		MessageReply messageReply = new MessageReply(102,1,"小明","1.jpg","啦啦啦","2016-08-01");
		messageReplyBiz.addMessageReply(messageReply);
	}
	
	public void findMessageReply(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		MessageReplyBiz messageReplyBiz = (MessageReplyBiz) context.getBean("messageReplyBizImpl");
		List<MessageReply>list=messageReplyBiz.findMessageReply(1);
		System.out.println(list);
	}
	
	
	public void selectattenGroupByUid(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		AttenGroupBiz attenGroupBiz = (AttenGroupBiz) context.getBean("attenGroupBizImpl");
		Users users = new Users(1002);
		List<Integer> list=attenGroupBiz.findUidQueue(1002);
		System.out.println(list);
	}
	
	public void getUser(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		UsersBiz usersBiz = (UsersBiz) context.getBean("usersBizImpl");
		Users users=usersBiz.getUser(1002);
		System.out.println(users);
	}
	
	public void test2(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		MyRedisCache redisCache = new MyRedisCache("");
		Users users = new Users(1);
		redisCache.putObject("2", users);
	}
	
	
	public void test3(){
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"beans_mybatis.xml","beans_redis.xml"});
		RedisTestBiz redisbiz=(RedisTestBiz) context.getBean("redisBizImpl");
		Users users = new Users(1);
		redisbiz.putObject("1", users);
	}
	
	
}
