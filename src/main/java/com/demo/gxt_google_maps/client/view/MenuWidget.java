package com.demo.gxt_google_maps.client.view;

import com.demo.gxt_google_maps.client.rpc.GWTService;
import com.demo.gxt_google_maps.client.rpc.StoreUtil;
import com.demo.gxt_google_maps.shared.TitleTransit;
import com.demo.gxt_google_maps.shared.Transit;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowExpander;

import java.util.ArrayList;
import java.util.List;

public class MenuWidget extends Grid<Transit> {

	private static final GWTService service = GWT.create(GWTService.class);

    public static final DateTimeFormat                       df = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
	private static ColumnConfig<Transit, Boolean>       viewCol = new ColumnConfig<Transit, Boolean>(service.checked1(), 30, SafeHtmlUtils.fromSafeConstant("<img alt='View' src='img/view.png' width='23' style='margin-top:-4px; margin-left:-1px;'/>"));
    private static ColumnConfig<Transit, Boolean>      foundCol = new ColumnConfig<Transit, Boolean>(service.checked2(), 30, SafeHtmlUtils.fromSafeConstant("<img alt='Found' src='img/found.png' width='23' style='margin-top:-4px; margin-left:-1px;'/>"));
	private static ColumnConfig<Transit, TitleTransit> objectCol = new ColumnConfig<Transit, TitleTransit>(service.title(), 230, SafeHtmlUtils.fromSafeConstant("<center>Объект</center>"));
    private static ColumnConfig<Transit, Integer>      phoneCol = new ColumnConfig<Transit, Integer>(service.speed(), 40, "км/ч");
    private static ColumnConfig<Transit, Boolean>       wifiCol = new ColumnConfig<Transit,Boolean>(service.wifi(),30, SafeHtmlUtils.fromSafeConstant("<img alt='Wi-Fi' src='img/wifi.png' width='23' style='margin-top:-4px; margin-left:-1px;'/>"));
    private static RowExpander<Transit>             expanderRow = new RowExpander<Transit>(new AbstractCell<Transit>() {
        @Override
        public void render(Context context, Transit transit, SafeHtmlBuilder sb) {
            sb.appendHtmlConstant("" + new ExpanderBuilder(transit));
        }
    });

    public MenuWidget() {
        super(loadData(), createColumns());

        this.setAllowTextSelection(true);
        this.getView().setStripeRows(true);
        this.getView().setColumnLines(true);
        this.getView().setAutoExpandColumn(objectCol);
        this.setBorders(false);
        this.setColumnReordering(true);
        this.setStateful(false);
        expanderRow.initPlugin(this);
    }

    private static ListStore<Transit> loadData(){
        ListStore<Transit> store = new ListStore<Transit>(service.key());
        store.addAll(StoreUtil.getData());

        return store;
    }

	private static ColumnModel<Transit> createColumns(){
        viewCol.setCell(new AbstractCell<Boolean>() {
            @Override
            public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
                if(value){
                    sb.appendHtmlConstant("<input name='checked1' id='checked1' type='checkbox' checked></input>");
                } else {
                    sb.appendHtmlConstant("<input name='checked1' id='checked1' type='checkbox'></input>");
                }
            }
        });
        foundCol.setCell(new AbstractCell<Boolean>() {
            @Override
            public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
                if(value){
                    sb.appendHtmlConstant("<input name='checked2' id='checked2' type='checkbox' checked></input>");
                } else {
                    sb.appendHtmlConstant("<input name='checked2' id='checked2' type='checkbox'></input>");
                }
            }
        });
        objectCol.setCell(new AbstractCell<TitleTransit>() {
            @Override
            public void render(Context context, TitleTransit title, SafeHtmlBuilder sb) {
                sb.appendHtmlConstant("<table cellspacing='3' cellpadding='0' border='0'>");
                sb.appendHtmlConstant("<tr><td rowspan='2'><img alt='"+title.getVehicleType()+"' src='img/"+title.getVehicleType()+".png' width='30'/></td><td><font color='blue'><b>"+title.getNameVehicleType()+"</b></font></td></tr>");
                sb.appendHtmlConstant("<tr><td>"+df.format(title.getTime())+"</td></tr>");
                sb.appendHtmlConstant("</table>");
            }
        });
        wifiCol.setCell(new AbstractCell<Boolean>() {
            @Override
            public void render(Context context, Boolean value, SafeHtmlBuilder sb) {
                if(value){
                    sb.appendHtmlConstant("<img src='img/wifi-enable.png'>");
                } else {
                    sb.appendHtmlConstant("<img src='img/wifi-disable.png'>");
                }
            }
        });

        List<ColumnConfig<Transit, ?>> columns = new ArrayList<ColumnConfig<Transit, ?>>();
        expanderRow.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        viewCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        foundCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        phoneCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        wifiCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        columns.add(expanderRow);
        columns.add(viewCol);
        columns.add(foundCol);
        columns.add(objectCol);
        columns.add(phoneCol);
        columns.add(wifiCol);
		return new ColumnModel<Transit>(columns);
	}

    static class ExpanderBuilder extends TabPanel {
        public ExpanderBuilder(Transit transit){
            StringBuilder expanderStringBuilder = new StringBuilder();
            expanderStringBuilder.append("<table class='underline'>");
            expanderStringBuilder.append("<tr><td>Водитель:</td> <td><font color='blue'>"+transit.getFirstName()+" "+transit.getLastName()+"</font></td></tr>");
            expanderStringBuilder.append("<tr><td>Время (позиция):</td> <td>"+df.format(transit.getTimePosition())+"</td></tr>");
            expanderStringBuilder.append("<tr><td>Время (сервер):</td> <td>"+df.format(transit.getTimeServer())+"</td></tr>");
            expanderStringBuilder.append("<tr><td>Высота:</td> <td>"+transit.getHeight()+" м</td></tr>");
            expanderStringBuilder.append("<tr><td>Модель:</td> <td>"+transit.getModel()+"</td></tr>");
            expanderStringBuilder.append("<tr><td>Номер:</td> <td>"+transit.getNumber()+"</td></tr>");
            expanderStringBuilder.append("<tr><td>Одометр:</td> <td>"+transit.getDistance()+"</td></tr>");
            expanderStringBuilder.append("<tr><td>Позиция:</td> <td><a href='https://www.google.com.ua/maps/@"+transit.getPosition().getLatitude()+","+transit.getPosition().getLongitude()+",8.75z' target='_blank' title='Показать на карте'>" +transit.getPosition().getLatitude()+"', "+transit.getPosition().getLongitude() +"'</a></td></tr>");
            expanderStringBuilder.append("<tr><td>Угол:</td> <td>"+transit.getDegree()+"'</td></tr>");
            expanderStringBuilder.append("</table>");

            ContentPanel currentPanel = new ContentPanel();
            ContentPanel routesPanel = new ContentPanel();
            ContentPanel notificationsPanel = new ContentPanel();
            currentPanel.add(new HTML(expanderStringBuilder.toString()));
            routesPanel.add(new HTML("none"));
            notificationsPanel.add(new HTML("none"));
            currentPanel.setHeaderVisible(false); routesPanel.setHeaderVisible(false); notificationsPanel.setHeaderVisible(false);

            add(currentPanel, "Сейчас");
            add(routesPanel, "Маршруты");
            add(notificationsPanel, "Оповещения");
            setAutoSelect(true);
        }
    }
}
