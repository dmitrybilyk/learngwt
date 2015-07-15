package toknow.client;

//import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.EntryPoint;
        import com.google.gwt.user.client.ui.*;
        import toknow.client.anticafe.LoginPage;
import toknow.client.anticafe.MainPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AllInOne implements EntryPoint {
  public void onModuleLoad() {
//    InlineLabel inlineLabel = new InlineLabel("Test label");
//    Label wrapLabel = Label.wrap(DOM.getElementById("logo"));
//    wrapLabel.setText("new Text");
//    SimplePanel simplePanel = new SimplePanel();
//    simplePanel.addStyleName("simplePanelClass");
//    HTML html = new HTML();
//    html.setHeight("400px");
//    html.setWidth("400px");
//    html.setHTML("<div class='htmlClass'>This is HTML</div>");
//    simplePanel.add(inlineLabel);
//    simplePanel.add(html);

    RootPanel.get().add(new MainPanel());
//    RootPanel.get().add(new MainPanel());
//    RootPanel.get().add(new WidgetRenderingExample());
//    RootPanel.get().add(new FormsExample());
//    CheckBoxListViewExample checkBoxListViewExample = new CheckBoxListViewExample();
//    RootPanel.get().add(checkBoxListViewExample);
  }
}
