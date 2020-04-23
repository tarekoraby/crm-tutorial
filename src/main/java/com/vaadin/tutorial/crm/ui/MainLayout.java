package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.tutorial.crm.ui.views.dashboard.DashboardView;
import com.vaadin.tutorial.crm.ui.views.list.ListView;

@PWA(
    name = "Vaadin CRM",
    shortName = "CRM",
    offlineResources = {
        "./styles/offline.css",
        "./images/offline.png"
    },
    enableInstallPrompt = false
)
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");

        Anchor logout = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        Anchor tutorialButton = new Anchor("https://vaadin.com/learn/tutorials/modern-web-apps-with-spring-boot-and-vaadin", new Text("Read tutorial"),  VaadinIcon.EXTERNAL_LINK.create());
        tutorialButton.setTarget("_blank");
        tutorialButton.addClassName("button");

        VerticalLayout navigation = new VerticalLayout(
                listLink,
                new RouterLink("Dashboard", DashboardView.class),
                tutorialButton
        );
        navigation.setHeight("100%");
        addToDrawer(navigation);
    }


}
