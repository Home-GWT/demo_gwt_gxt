package com.demo.gxt_google_maps.client.view;

import com.demo.gxt_google_maps.client.view.maps.MapsHandler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class GoogleMapsWidget implements IsWidget {

    private SimpleContainer container;

    @Override
    public Widget asWidget() {
        if (container == null) {
            container = new SimpleContainer();

            createJsniCallbackFunction(new MapsHandler() {
                @Override
                public void mapsInitialized() {
                    drawMap(container.getElement());
                }
            });

            loadMaps();
        }
        return container;
    }

    private native void drawMap(XElement element) /*-{
        var mapOptions = {
            center : new $wnd.google.maps.LatLng(-34.397, 150.644),
            zoom : 8
        };

        var map = new $wnd.google.maps.Map(element, mapOptions);
    }-*/;

    public void loadMaps() {
        String key = "AIzaSyA1_IcNvk4Ee_HmTKsuhVpuTL84njwxz8w";

        Document doc = Document.get();
        ScriptElement script = doc.createScriptElement();
        script.setSrc("https://maps.googleapis.com/maps/api/js?v=3.exp&callback=mapsInitialized&key=" + key);
        script.setType("text/javascript");
        doc.getBody().appendChild(script);
    }

    public native void createJsniCallbackFunction(MapsHandler handler) /*-{
        $wnd.mapsInitialized = function mapsCallback() {
            // TODO if copying, change the package naming to this class accordingly!
            //$entry(@com.sencha.sandbox.client.GoogleMaps::mapsInitialized(com/sencha/sandbox/client/MapsHandler;)(handler));
        }
    }-*/;

    public static void mapsInitialized(MapsHandler handler) {
        handler.mapsInitialized();
    }

}
