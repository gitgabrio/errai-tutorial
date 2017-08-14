package com.gwidgets.errai.tutorial.server.services;

import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.bus.server.annotations.Service;
import org.slf4j.Logger;

import javax.inject.Inject;


@Service
public class HelloServerService extends AbstractMessageCallback {


    public static final String SERVER_SUBJECT = "HelloServerService";

    @Inject
    public HelloServerService(RequestDispatcher dispatcher, Logger logger) {
        super(dispatcher, logger);
    }


    @Override
    public void callback(Message message) {
        String messageText = message.get(String.class, "text");
        logger.debug("HelloServerService received " + messageText);
        MessageBuilder.createMessage()
                .toSubject(HelloLocalService.LOCAL_SUBJECT)
                .signalling()                 
                .with("text", SERVER_SUBJECT + ": " + messageText)
                .noErrorHandling()            
                .sendNowWith(dispatcher);
    }
}
