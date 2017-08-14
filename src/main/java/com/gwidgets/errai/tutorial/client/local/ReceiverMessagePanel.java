package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import javax.inject.Inject;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.slf4j.Logger;

public class ReceiverMessagePanel extends VerticalPanel {

    private MessageBus bus;
    private Logger logger;

    private TextBox senderTextBox = new TextBox();
    private TextBox responseTextBox = new TextBox();


    public static final String CLIENT_SUBJECT = "ClientSubject";
    public static final String VARIABLE_SUBJECT = "VariableSubject";

    @Inject
    public ReceiverMessagePanel(MessageBus bus, Logger logger) {
        this.bus = bus;
        this.logger = logger;
        Label label = new Label("RESPONSE");
        label.addStyleName("headLabel");
        this.add(label);
        this.add(new Label("Subject"));
        this.add(senderTextBox);
        this.add(new Label("Text"));
        this.add(responseTextBox);
        this.addStyleName("verticalPanel");
        subscriptions();
    }

    private void subscriptions() {
        subscribe(CLIENT_SUBJECT);
        subscribe(VARIABLE_SUBJECT);
    }

    private void subscribe(String subject) {
        bus.subscribe(subject, message -> {
            String messageText = message.get(String.class, "text");
            logger.debug("Received " + messageText + " on " + subject);
            showReceived(subject, messageText);
        });
    }

    private void showReceived(String sender, String message) {
        senderTextBox.setText(sender);
        responseTextBox.setText(message);
    }



}
