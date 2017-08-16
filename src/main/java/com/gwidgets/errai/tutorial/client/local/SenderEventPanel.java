package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.*;
import com.gwidgets.errai.tutorial.client.shared.Contact;
import com.gwidgets.errai.tutorial.client.shared.ContactOperation;
import com.gwidgets.errai.tutorial.client.shared.ContactStorageService;
import com.gwidgets.errai.tutorial.server.ContactStorageServiceImpl;
import org.jboss.errai.bus.client.api.ClientMessageBus;
import org.jboss.errai.common.client.api.Caller;

import javax.inject.Inject;

public class SenderEventPanel extends HorizontalPanel {

    @Inject
    private ClientMessageBus bus;

    private TextBox fullnameTextBox = new TextBox();
    private TextBox nicknameTextBox = new TextBox();

    /**
     * This is a simple interface for calling a remote HTTP service. Behind this interface, Errai has generated an HTTP
     * request to the service defined by {@link ContactStorageService} (a JaxRS service).
     */
    @Inject
    private Caller<ContactStorageService> contactService;

    @Inject
    public SenderEventPanel(ClientMessageBus bus) {
        this.bus = bus;
        this.add(buttonsPanel());
        this.add(requestPanel());
    }

    private VerticalPanel buttonsPanel() {
        VerticalPanel toReturn = new VerticalPanel();
        Label label = new Label("SENDERS");
        label.addStyleName("headLabel");
        toReturn.add(label);
        toReturn.add(sendServerMessageButton());
        toReturn.addStyleName("verticalPanel");
        return toReturn;
    }

    private VerticalPanel requestPanel() {
        VerticalPanel toReturn = new VerticalPanel();
        Label label = new Label("REQUEST");
        label.addStyleName("headLabel");
        toReturn.add(label);
        toReturn.add(new Label("Full Name"));
        toReturn.add(fullnameTextBox);
        toReturn.add(new Label("NickName"));
        toReturn.add(nicknameTextBox);
        return toReturn;
    }

    private Button sendServerMessageButton() {
        Button button = new Button(ContactStorageServiceImpl.CONTACTSTORAGE_SUBJECT);
        button.addClickHandler(event -> {
//                    MessageBuilder.createMessage()
//                            .toSubject(ContactStorageServiceImpl.CONTACTSTORAGE_SUBJECT)
//                            .signalling()
//                            .with("fullname", fullnameTextBox.getText())
//                            .with("nickname", nicknameTextBox.getText())
//                            .with("operationType", Operation.OperationType.CREATE.name())
//                            .with("sessionId", bus.getSessionId())
//                            .noErrorHandling()
//                            .sendNowWith(dispatcher);
                    createNewContactFromEditor();
                }
        );
        return button;
    }

    private void createNewContactFromEditor() {
        final Contact editorModel = new Contact(fullnameTextBox.getText(), nicknameTextBox.getText());
        contactService.call((final Response response) -> {
            // Set the id if we successfully create this contact.
            if (response.getStatusCode() == Response.SC_CREATED) {
                final String createdUri = response.getHeader("Location");
                final String idString = createdUri.substring(createdUri.lastIndexOf('/') + 1);
                final long id = Long.parseLong(idString);
                editorModel.setId(id);
            }
        }).create(new ContactOperation(editorModel, bus.getSessionId()));
    }


}
