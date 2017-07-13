package com.fantasybaby.bindspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQMessageSender implements  IMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(message);
    }

}