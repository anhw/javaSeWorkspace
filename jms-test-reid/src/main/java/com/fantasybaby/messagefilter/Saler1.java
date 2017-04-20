package com.fantasybaby.messagefilter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasybaby.util.UserNameUtil;

public class Saler1 implements MessageListener{
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private Queue requestQueue;
	private static Logger _logger = LoggerFactory.getLogger(Saler1.class);
	public Saler1(String factoryName,String queueName){
		try {
			InitialContext context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup(factoryName);
			queueConnection =  (QueueConnection) factory.createConnection(UserNameUtil.USERNAME,UserNameUtil.USERPWD);
			queueSession = (QueueSession) queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			requestQueue = (Queue) context.lookup(queueName);
			QueueReceiver createReceiver = queueSession.createReceiver(requestQueue,"type='xi'");
			
			createReceiver.setMessageListener(this);
			queueConnection.start();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		_logger.info("slaer1 start");
		Saler1 retailer = new Saler1("TopicConnectionFactory","requestQueue");
		
	}
	@Override
	public void onMessage(Message receive) {
		TextMessage message = (TextMessage) receive;
		try {
			_logger.info("saler1 receive:"+message.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
