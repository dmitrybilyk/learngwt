package toknow.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.AggregationRenderer;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
//import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;
import toknow.client.checkboxlistviewexample.CheckBoxListViewExample;
import toknow.client.forms.FormsExample;
import toknow.client.grids.WidgetRenderingExample;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AllInOne implements EntryPoint {
  public void onModuleLoad() {
    InlineLabel inlineLabel = new InlineLabel("Test label");
    Label wrapLabel = Label.wrap(DOM.getElementById("logo"));
    wrapLabel.setText("new Text");
    SimplePanel simplePanel = new SimplePanel();
    simplePanel.addStyleName("simplePanelClass");
    HTML html = new HTML();
    html.setHeight("400px");
    html.setWidth("400px");
    html.setHTML("<div class='htmlClass'>This is HTML</div>");
//    simplePanel.add(inlineLabel);
    simplePanel.add(html);
    RootPanel.get().add(simplePanel);
//    RootPanel.get().add(new WidgetRenderingExample());
//    RootPanel.get().add(new FormsExample());
//    CheckBoxListViewExample checkBoxListViewExample = new CheckBoxListViewExample();
//    RootPanel.get().add(checkBoxListViewExample);
  }
}
