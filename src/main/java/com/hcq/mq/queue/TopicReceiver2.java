package com.hcq.mq.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @description  Topic消息监听器
 * 
 */
@Component
public class TopicReceiver2 implements MessageListener{
	private String messageString;
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver2接收到消息:"+((TextMessage)message).getText());
			setMessageString(((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public String getMessageString() {
		return messageString;
	}
	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}
	
}
