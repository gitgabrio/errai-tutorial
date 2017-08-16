/**
 * Copyright (C) 2016 Red Hat, Inc. and/or its affiliates.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gwidgets.errai.tutorial.server;

import com.gwidgets.errai.tutorial.client.shared.Contact;
import com.gwidgets.errai.tutorial.client.shared.ContactOperation;
import com.gwidgets.errai.tutorial.client.shared.ContactStorageService;
import com.gwidgets.errai.tutorial.client.shared.Operation;
import org.jboss.errai.bus.client.api.messaging.RequestDispatcher;
import org.jboss.errai.bus.server.annotations.Service;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

import static com.gwidgets.errai.tutorial.client.shared.Operation.OperationType.*;


/**
 * Server-side implementation for the RPC service, {@link ContactStorageService}. Fires Errai CDI {@link Event Events} that are observed by clients over the
 * wire to publish creation, update, and deletion of {@link Contact Contacts}.
 */
@Stateless
@Service
public class ContactStorageServiceImpl implements ContactStorageService  {

    public static final String CONTACTSTORAGE_SUBJECT = "ContactStorageServiceImpl";

    @Inject
    private ContactEntityService entityService;

    @Inject
    protected Logger logger;

    @Inject
    @Operation(CREATE)
    private Event<ContactOperation> created;

    @Inject
    @Operation(UPDATE)
    private Event<ContactOperation> updated;

    @Inject
    @Operation(DELETE)
    private Event<Long> deleted;

    @Inject
    private RequestDispatcher dispatcher;

    @Override
    public List<Contact> getAllContacts() {
        return entityService.getAllContacts();
    }

    @Override
    public Response create(final ContactOperation contactOperation) {
        logger.debug("create " + contactOperation.getContact());
        entityService.create(contactOperation.getContact());
        // This event is delivered to call connected clients.
        created.fire(contactOperation);
        return Response.created(UriBuilder.fromResource(ContactStorageService.class)
                .path(String.valueOf(contactOperation.getContact().getId())).build()).build();
    }

    @Override
    public Response update(final ContactOperation contactOperation) {
        logger.debug("update " + contactOperation.getContact());
        entityService.update(contactOperation.getContact());
        // This event is delivered to call connected clients.
        updated.fire(contactOperation);
        return Response.noContent().build();
    }

    @Override
    public Response delete(Long id) {
        logger.debug("delete " + id);
        entityService.delete(id);
        // This event is delivered to call connected clients.
        deleted.fire(id);
        return Response.noContent().build();
    }

}
