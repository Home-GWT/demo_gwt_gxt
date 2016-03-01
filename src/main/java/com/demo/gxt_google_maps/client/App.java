package com.demo.gxt_google_maps.client;

import com.demo.gxt_google_maps.client.view.FrameWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        FrameWidget widget = new FrameWidget();
        RootPanel.get().add(widget);
    }

}