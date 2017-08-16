package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
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
    @Inject
    private SenderEventPanel senderEventPanel;
    @Inject
    private ReceiverEventPanel receiverEventPanel;


    @PostConstruct
    public void onLoad() {
        logger.debug("onLoad");
        RootPanel.get().add(mainTabPanel());
    }

    private TabPanel mainTabPanel() {
        TabPanel toReturn = new TabPanel();
        toReturn.add(messagesPanel(), "MESSAGES");
        toReturn.add(eventsPanel(), "EVENTS");
        return toReturn;
    }

    private HorizontalPanel messagesPanel() {
        HorizontalPanel toReturn = new HorizontalPanel();
        toReturn.add(senderMessagePanel);
        toReturn.add(receiverMessagePanel);
        return toReturn;
    }

    private HorizontalPanel eventsPanel() {
        HorizontalPanel toReturn = new HorizontalPanel();
        toReturn.add(senderEventPanel);
        toReturn.add(receiverEventPanel);
        return toReturn;
    }

}
