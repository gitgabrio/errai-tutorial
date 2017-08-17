package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;
import org.jboss.errai.ui.nav.client.local.Navigation;
import org.jboss.errai.ui.nav.client.local.Page;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 17/08/17.
 */
@Page(path="/messages")
public class TutorialPage extends HorizontalPanel {

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

    @Inject
    private Navigation navigation;

    @PostConstruct
    public void onLoad() {
        logger.debug("onLoad");
        add(getTabPanel());
        add(pageButton());
    }

    private TabPanel getTabPanel() {
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

    private Button pageButton() {
        Button button = new Button("CONTACT BOOK");
        button.addClickHandler(event -> navigation.goTo("ContactListPage"));
        return button;
    }
}
