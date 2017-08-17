package com.gwidgets.errai.tutorial.client.local;

import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.common.client.dom.Body;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.NavigationPanel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static org.jboss.errai.common.client.dom.Window.getDocument;


@EntryPoint
public class App {

    @Inject
    private NavigationPanel navPanel;

    @Inject
    private NavBar navbar;

    @Inject
    private JQueryProducer.JQuery $;

    @PostConstruct
    public void init() {
        RootPanel.get("rootPanel").add(navPanel);
        final Body body = getDocument().getBody();
        $.wrap($.wrap(body).children().first()).before(navbar.getElement());
    }

}
