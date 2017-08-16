package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwidgets.errai.tutorial.client.shared.Contact;
import com.gwidgets.errai.tutorial.client.shared.ContactOperation;
import com.gwidgets.errai.tutorial.client.shared.Operation;
import org.jboss.errai.bus.client.api.messaging.MessageBus;
import org.slf4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class ReceiverEventPanel extends VerticalPanel {


    @Inject
    private Logger logger;

    private TextBox operationTextBox = new TextBox();
    private TextBox contactTextBox = new TextBox();

    public ReceiverEventPanel() {
        Label label = new Label("RESPONSE");
        label.addStyleName("headLabel");
        this.add(label);
        this.add(new Label("Operation"));
        this.add(operationTextBox);
        this.add(new Label("Contact"));
        this.add(contactTextBox);
        this.addStyleName("verticalPanel");
    }

    /**
     * This is called in response to Errai CDI {@link javax.enterprise.event.Event Events} fired from the server when a
     * new {@link Contact} is created. In this way we can display newly created contacts from other browser sessions.
     */
    public void onRemoteCreated(final @Observes @Operation(Operation.OperationType.CREATE) ContactOperation contactOperation) {
        logger.debug("onRemoteCreated " + contactOperation.getSourceQueueSessionId());
        operationTextBox.setText(Operation.OperationType.CREATE.name());
        contactTextBox.setText(contactOperation.getContact().toString());
    }


}
