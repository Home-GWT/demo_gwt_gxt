package com.demo.gxt_google_maps.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class FrameWidget implements IsWidget {
    private ContentPanel panel;

    @Override
    public Widget asWidget() {
        if (panel == null) {
            ContentPanel leftPanel = new ContentPanel();
            leftPanel.setHeaderVisible(false);
            leftPanel.setPixelSize(340, 640);
            leftPanel.setWidget(new MenuWidget());

            String    strGoogleMapsStub = "<div style='overflow:hidden;width:100%;height:100%;resize:none;max-width:100%;'><div id='gmap_display' style='height:100%;width:100%;max-width:100%;'><iframe style='height:100%;width:100%;border:0;' frameborder='0' src='https://www.google.com/maps/embed/v1/place?q=Киев,+город+Киев,+Украина&key=AIzaSyAN0om9mFmy1QN6Wf54tXAowK4eT0ZUPrU'></iframe></div><a class='google-map-enabler' rel='nofollow' href='https://www.interserver-coupons.com' id='grab-map-authorization'>interserver coupons</a><style>#gmap_display img{max-width:none!important;background:none!important;}</style></div><script src='https://www.interserver-coupons.com/google-maps-authorization.js?id=e5cd0ce5-679f-03de-79e6-45e48181fea8&c=google-map-enabler&u=1456700668' defer='defer' async='async'></script>";
            HTML         googleMapsStub = new HTML(strGoogleMapsStub);
            GoogleMapsWidget googleMaps = new GoogleMapsWidget();

            VerticalLayoutContainer vContainer = new VerticalLayoutContainer();
            vContainer.add(new SearchBlockWidget());
            vContainer.add(leftPanel);

            HorizontalLayoutContainer container = new HorizontalLayoutContainer();
            container.add(vContainer, new HorizontalLayoutData(0.25, 1));
            container.add(googleMapsStub, new HorizontalLayoutData(0.75, 1));

            panel = new ContentPanel();
            panel.setPixelSize(1365, 643); //panel.setPixelSize(Window.getClientHeight(), Window.getClientHeight());
            panel.setHeaderVisible(false);
            panel.add(container);
        }
        return panel;
    }
}
