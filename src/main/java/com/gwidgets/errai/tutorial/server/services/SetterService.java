package com.gwidgets.errai.tutorial.server.services;

import org.jboss.errai.bus.client.api.QueueSession;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.common.client.protocols.Resources;
import org.slf4j.Logger;

import javax.inject.Inject;


@Service
public class SetterService extends AbstractReplierService {

    public static final String SETTER_SUBJECT = "SetterSubject";

    @Inject
    public SetterService(MessageBus messageBus, RequestDispatcher dispatcher, Logger logger) {
        super(messageBus, dispatcher, SETTER_SUBJECT, logger);
    }

    @Override
    public MessageCallback getMessageCallback() {
        MessageCallback toReturn = message -> {
            logger.debug("Received " + message.toString());
            QueueSession sess = message.getResource(QueueSession.class, Resources.Session.name());
            String sessionId = sess.getSessionId();
            logger.debug("Session: " + sessionId);
            String messageText = message.get(String.class, "text");
            sess.setAttribute("MyAtt", messageText);
        };
        return toReturn;
    }
}
