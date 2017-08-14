package com.gwidgets.errai.tutorial.client.local;

import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.server.annotations.Service;
import org.slf4j.Logger;

import javax.inject.Inject;


@Service
public class MessageListener implements MessageCallback {

    private Logger logger;
    @Inject
    public MessageListener(Logger logger) {
        this.logger = logger;
        logger.debug("MessageListener constructor");
    }

    @Override
    public void callback(Message message) {
        String messageText = message.get(String.class, "text");
        logger.debug("MessageListener class received " + messageText);
    }
}
