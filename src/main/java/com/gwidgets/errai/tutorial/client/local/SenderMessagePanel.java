package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.*;
import com.gwidgets.errai.tutorial.server.services.BroadcasterService;
import com.gwidgets.errai.tutorial.server.services.GetterService;
import com.gwidgets.errai.tutorial.server.services.HelloServerService;
import com.gwidgets.errai.tutorial.server.services.SetterService;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.common.client.protocols.MessageParts;

import javax.inject.Inject;

public class SenderMessagePanel extends HorizontalPanel {

    private RequestDispatcher dispatcher;

    private TextBox requestTextBox = new TextBox();

    @Inject
    public SenderMessagePanel(RequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.add(buttonsPanel());
        this.add(requestPanel());
    }

    private VerticalPanel buttonsPanel() {
        VerticalPanel toReturn = new VerticalPanel();
        Label label = new Label("SENDERS");
        label.addStyleName("headLabel");
        toReturn.add(label);
        toReturn.add(sendClientMessageButton());
        toReturn.add(sendServerMessageButton());
        toReturn.add(sendBroadcastedMessageButton());
        toReturn.add(setVariabledMessageButton());
        toReturn.add(getVariabledMessageButton());
        toReturn.addStyleName("verticalPanel");
        return toReturn;
    }

    private VerticalPanel requestPanel() {
        VerticalPanel toReturn = new VerticalPanel();
        Label label = new Label("REQUEST");
        label.addStyleName("headLabel");
        toReturn.add(label);
        toReturn.add(new Label("Text"));
        toReturn.add(requestTextBox);
        toReturn.addStyleName("verticalPanel");
        return toReturn;
    }


    private Button sendClientMessageButton() {
        Button button = new Button(ReceiverMessagePanel.CLIENT_SUBJECT);
        button.addClickHandler(event -> MessageBuilder.createMessage()
                .toSubject(ReceiverMessagePanel.CLIENT_SUBJECT)
                .signalling()
                .with("text", requestTextBox.getText())
                .noErrorHandling()
                .sendNowWith(dispatcher)
        );
        return button;
    }

    private Button sendServerMessageButton() {
        Button button = new Button(HelloServerService.SERVER_SUBJECT);
        button.addClickHandler(event -> MessageBuilder.createMessage()
                .toSubject(HelloServerService.SERVER_SUBJECT)
                .signalling()
                .with("text", requestTextBox.getText())
                .noErrorHandling()
                .sendNowWith(dispatcher)
        );
        return button;
    }

    private Button sendBroadcastedMessageButton() {
        Button button = new Button(BroadcasterService.BROADCASTER_SUBJECT);
        button.addClickHandler(event -> MessageBuilder.createMessage()
                .toSubject(BroadcasterService.BROADCASTER_SUBJECT)
                .signalling()
                .with("text", requestTextBox.getText())
                .noErrorHandling()
                .sendNowWith(dispatcher)
        );
        return button;
    }

    private Button setVariabledMessageButton() {
        Button button = new Button(SetterService.SETTER_SUBJECT);
        button.addClickHandler(event -> MessageBuilder.createMessage()
                .toSubject(SetterService.SETTER_SUBJECT)
                .signalling()
                .with("text", requestTextBox.getText())
                .noErrorHandling()
                .sendNowWith(dispatcher)
        );
        return button;
    }

    private Button getVariabledMessageButton() {
        Button button = new Button(GetterService.GETTER_SUBJECT);
        button.addClickHandler(event -> MessageBuilder.createMessage()
                .toSubject(GetterService.GETTER_SUBJECT)
                .signalling()
                .with(MessageParts.ReplyTo, ReceiverMessagePanel.VARIABLE_SUBJECT)
                .noErrorHandling()
                .sendNowWith(dispatcher)
        );
        return button;
    }

}
