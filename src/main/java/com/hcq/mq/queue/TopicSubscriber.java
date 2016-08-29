package com.hcq.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.List;

import javax.jms.*;

public class TopicSubscriber {

	public List<String> Message(List<String> uidlist) throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		final List<String> list = null;
		for (String s : uidlist) {
			Topic topic = session.createTopic(s);

			MessageConsumer consumer = session.createConsumer(topic);

			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage tm = (TextMessage) message;
					try {
						System.out.println("Received message: " + tm.getText());
						list.add(tm.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		}
		session.close();
		connection.stop();
		connection.close();
		return list;
	}

}
