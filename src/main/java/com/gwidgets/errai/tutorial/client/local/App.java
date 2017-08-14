package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.bus.client.ErraiBus;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


@EntryPoint
public class App {

    @Inject
    private Logger logger;
    @Inject
    private SenderMessagePanel senderMessagePanel;
    @Inject
    private ReceiverMessagePanel receiverMessagePanel;


    @PostConstruct
    public void onLoad() {
        logger.debug("onLoad");
        RootPanel.get().add(mainPanel());
    }

    private HorizontalPanel mainPanel() {
        HorizontalPanel toReturn = new HorizontalPanel();
        toReturn.add(senderMessagePanel);
        toReturn.add(receiverMessagePanel);
        return toReturn;
    }

}
