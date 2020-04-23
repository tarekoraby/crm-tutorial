package com.vaadin.tutorial.crm.ui.views.login;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

import java.util.Collections;

@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver, PageConfigurator {

    LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login.setAction("login");

        add(
                new H1("Vaadin CRM"),
                login,
                new Html("<div>" +
                        "<p>Log in with user: <b>user</b> and password: <b>password</b>." +
                        "</p>" +
                        "The database in this demo is reset every few hours." +
                        "</div>")
        );
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .getOrDefault("error", Collections.emptyList())
                .isEmpty()) {
            login.setError(true);
        }
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("og:title", "Vaadin CRM - Full stack Spring Boot and Vaadin app");
        settings.addMetaTag("og:description", "Installable progressive web app with a database and Spring Security Login.");
        settings.addMetaTag("og:url", "https://crm.demo.vaadin.com/");
        settings.addMetaTag("og:image", "https://crm.demo.vaadin.com/images/spring-boot-vaadin-tutorial.png");
        settings.addMetaTag("og:type", "website");
    }
}
