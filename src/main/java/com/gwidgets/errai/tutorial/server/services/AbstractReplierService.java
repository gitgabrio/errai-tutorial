package com.gwidgets.errai.tutorial.server.services;

import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.slf4j.Logger;



public abstract class AbstractReplierService {

    protected Logger logger;
    protected RequestDispatcher dispatcher;
    protected String subject;

    public AbstractReplierService(MessageBus messageBus, RequestDispatcher dispatcher, String subject, org.slf4j.Logger logger) {
        this.subject = subject;
        messageBus.subscribe(subject, getMessageCallback());
        this.logger = logger;
        this.dispatcher = dispatcher;
    }

    public abstract MessageCallback getMessageCallback();

}
