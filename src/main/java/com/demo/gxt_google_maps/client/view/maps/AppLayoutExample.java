package com.demo.gxt_google_maps.client.view.maps;

import com.demo.gxt_google_maps.client.view.GoogleMapsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class AppLayoutExample {

    public AppLayoutExample() {
        SimpleContainer container = new SimpleContainer();
        container.add(createLayoutBlc(), new MarginData(40));

        Viewport root = new Viewport();
        RootPanel.get().add(root);
        root.add(container);
    }

    private Widget createLayoutBlc() {
        BorderLayoutData layoutDataNorth = new BorderLayoutData();
        layoutDataNorth.setMargins(new Margins(10));
        MarginData layoutDataCenter = new MarginData(10);

        BorderLayoutContainer main = new BorderLayoutContainer();
        main.setNorthWidget(getNorthWidget(), layoutDataNorth);
        main.setCenterWidget(getCenterWidget(), layoutDataCenter);

        return main;
    }

    public Widget getNorthWidget() {
        SimpleContainer w = new SimpleContainer();
        w.setBorders(true);
        w.getElement().getStyle().setBackgroundColor("#FFF");

        return w;
    }

    public Widget getCenterWidget() {
        GoogleMapsWidget googleMaps = new GoogleMapsWidget();

        SimpleContainer w = new SimpleContainer();
        w.setBorders(true);
        w.getElement().getStyle().setBackgroundColor("#FFF");
        w.setWidget(googleMaps);
        return w;
    }

}
