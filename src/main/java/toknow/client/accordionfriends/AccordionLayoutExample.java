package toknow.client.accordionfriends;


//import com.extjs.gxt.samples.resources.client.Resources;
//import com.extjs.gxt.samples.resources.client.TestData;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import toknow.client.anticafe.MainPanel;

public class AccordionLayoutExample extends LayoutContainer {

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);
    setLayout(new FlowLayout(10));

    ContentPanel panel = new ContentPanel();
    panel.setHeadingHtml("AccordionLayout");
    panel.setBodyBorder(false);

    panel.setLayout(new AccordionLayout());
//    panel.setIcon(Resources.ICONS.accordion());

    ContentPanel cp = new ContentPanel();
    cp.setAnimCollapse(false);
    cp.setHeadingHtml("Online Users");
    cp.setLayout(new FitLayout());
    panel.add(cp);

    TreeStore<ModelData> store = new TreeStore<ModelData>();
    TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
    tree.setIconProvider(new ModelIconProvider<ModelData>() {

      public AbstractImagePrototype getIcon(ModelData model) {
        if (model.get("icon") != null) {
          return IconHelper.createStyle((String) model.get("icon"));
        } else {
          return null;
        }
      }

    });
    tree.setDisplayProperty("name");

    ModelData m = newItem("Family", null);
    store.add(m, false);

    store.add(m, newItem("Darrell", "user"), false);
    store.add(m, newItem("Maro", "user-girl"), false);
    store.add(m, newItem("Lia", "user-kid"), false);
    store.add(m, newItem("Alec", "user-kid"), false);
    store.add(m, newItem("Andrew", "user-kid"), false);
    tree.setExpanded(m, true);

    m = newItem("Friends", null);
    store.add(m, false);

    store.add(m, newItem("Bob", "user"), false);
    store.add(m, newItem("Mary", "user-girl"), false);
    store.add(m, newItem("Sally", "user-girl"), false);
    store.add(m, newItem("Jack", "user"), false);

    tree.setExpanded(m, true);

    cp.add(tree);

    cp = new ContentPanel();
    cp.setAnimCollapse(false);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingHtml("Settings");
    cp.addText("some text");
    panel.add(cp);

    cp = new ContentPanel();
    cp.setAnimCollapse(false);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingHtml("Stuff");
    cp.addText("some text");
    panel.add(cp);

    cp = new ContentPanel();
    cp.setAnimCollapse(false);
    cp.setBodyStyleName("pad-text");
    cp.setHeadingHtml("More Stuff");
    cp.addText("some text");
    panel.add(new MainPanel());
    panel.setSize(200, 325);

    add(panel);
  }

  private ModelData newItem(String text, String iconStyle) {
    ModelData m = new BaseModelData();
    m.set("name", text);
    m.set("icon", iconStyle);
    return m;
  }

}