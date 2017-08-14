package com.gwidgets.errai.tutorial.server.services;

import org.jboss.errai.bus.client.api.QueueSession;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.bus.server.util.LocalContext;
import org.jboss.errai.common.client.protocols.Resources;
import org.slf4j.Logger;

import javax.inject.Inject;


@Service
public class GetterService extends AbstractReplierService {

    public static final String GETTER_SUBJECT = "GetterSubject";

    @Inject
    public GetterService(MessageBus messageBus, RequestDispatcher dispatcher, Logger logger) {
        super(messageBus, dispatcher, GETTER_SUBJECT, logger);
    }

    @Override
    public MessageCallback getMessageCallback() {
        MessageCallback toReturn = message -> {
            logger.debug("Received " + message.toString());
            QueueSession sess = message.getResource(QueueSession.class, Resources.Session.name());
            String sessionId = sess.getSessionId();
            logger.debug("Session: " + sessionId);
            String myAtt = sess.getAttribute(String.class, "MyAtt");
            MessageBuilder.createConversation(message)
                    .subjectProvided()
                    .signalling()
                    .with("text", GETTER_SUBJECT + ": " + myAtt)
                    .noErrorHandling()
                    .reply();
        };
        return toReturn;
    }
}
