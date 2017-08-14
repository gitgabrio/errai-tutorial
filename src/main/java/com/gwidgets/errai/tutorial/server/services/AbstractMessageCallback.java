package com.gwidgets.errai.tutorial.server.services;

import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.slf4j.Logger;


public abstract class AbstractMessageCallback implements MessageCallback {

    protected Logger logger;
    protected RequestDispatcher dispatcher;

    public AbstractMessageCallback(RequestDispatcher dispatcher, Logger logger) {
        String className = this.getClass().getSimpleName();
        logger.debug(className + " constructor");
        this.logger = logger;
        this.dispatcher = dispatcher;
    }
}
