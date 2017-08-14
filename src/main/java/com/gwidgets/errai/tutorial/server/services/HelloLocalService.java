package com.gwidgets.errai.tutorial.server.services;

import com.gwidgets.errai.tutorial.client.local.ReceiverMessagePanel;
import org.jboss.errai.bus.client.api.Local;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.bus.server.annotations.Service;
import org.slf4j.Logger;

import javax.inject.Inject;


@Local
@Service
public class HelloLocalService extends AbstractMessageCallback {

    public static final String LOCAL_SUBJECT = "HelloLocalService";

    @Inject
    public HelloLocalService(RequestDispatcher dispatcher, Logger logger) {
        super(dispatcher, logger);
    }

    @Override
    public void callback(Message message) {
        String messageText = message.get(String.class, "text");
        logger.debug("HelloLocalService received " + messageText);
        MessageBuilder.createMessage()
                .toSubject(ReceiverMessagePanel.CLIENT_SUBJECT)
                .signalling()
                .with("text", LOCAL_SUBJECT + ": " + messageText)
                .noErrorHandling()
                .sendNowWith(dispatcher);
    }
}
