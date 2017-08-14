package com.gwidgets.errai.tutorial.server.services;

import com.gwidgets.errai.tutorial.client.local.ReceiverMessagePanel;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.bus.server.annotations.Service;
import org.slf4j.Logger;

import javax.inject.Inject;


@Service
public class BroadcasterService extends AbstractReplierService {

    public static final String BROADCASTER_SUBJECT = "BroadcasterSubject";

    @Inject
    public BroadcasterService(MessageBus messageBus, RequestDispatcher dispatcher, Logger logger) {
        super(messageBus, dispatcher, BROADCASTER_SUBJECT, logger);
    }

    @Override
    public MessageCallback getMessageCallback() {
        MessageCallback toReturn = message -> {
            String messageText = message.get(String.class, "text");
            logger.debug("Received " + messageText);
            MessageBuilder.createMessage()
                    .toSubject(ReceiverMessagePanel.CLIENT_SUBJECT)
                    .signalling()
                    .with("text", subject + ": " + messageText)
                    .noErrorHandling()
                    .sendGlobalWith(dispatcher);
        };
        return toReturn;
    }
}
