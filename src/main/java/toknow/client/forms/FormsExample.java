package toknow.client.forms;

import java.util.*;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;

public class FormsExample extends LayoutContainer {  
  
  LabelField privateLabelField;
  List<Component> components = new LinkedList<Component>();
  

  @Override  
  protected void onRender(Element parent, int index) {  
    super.onRender(parent, index);  
    createTagsForm();
  }


  private void createTagsForm() {
    Dialog dialog = new Dialog();
    dialog.setHeadingHtml("Interaction Tags");
    dialog.getElement().getStyle().setMargin(10, com.google.gwt.dom.client.Style.Unit.PX);
    dialog.setWidth(520);
    HorizontalPanel horizontalPanel = new HorizontalPanel();

    final LayoutContainer layoutContainer = new LayoutContainer();
    final ColumnLayout layout = new ColumnLayout();
    layoutContainer.setLayout(layout);
    layoutContainer.setHeight("250");
    layoutContainer.setBorders(true);
    layoutContainer.setScrollMode(Style.Scroll.AUTO);
    layoutContainer.setWidth("500");

    List<Stock> stocks = Stock.getStocks();
    Collections.sort(stocks, new Comparator<Stock>() {
      public int compare(Stock arg0, Stock arg1) {
        return arg0.getName().compareTo(arg1.getName());
      }
    });

    LabelField labelField = new LabelField("Type or select tag to apply");
    horizontalPanel.add(labelField);

    final ListStore<Stock> stockListStore = new ListStore<Stock>();
    stockListStore.add(Stock.getStocks());
    final ComboBox<Stock> combo = new ComboBox<Stock>();
    combo.setWidth(250);
    combo.setDisplayField("name");
    combo.setTriggerAction(TriggerAction.ALL);
    combo.setForceSelection(false);
    combo.setAllowBlank(true);

    combo.setStore(stockListStore);
    horizontalPanel.add(combo);

    Button addButton = new Button("Plus");
    dialog.addButton(addButton);

    addButton.addListener(Events.OnClick, new Listener<BaseEvent>() {
      public void handleEvent(BaseEvent be) {
        Stock comboValue = combo.getValue();
        if (comboValue == null) {
          String rawValue = combo.getRawValue();
          final LabelField widget = new LabelField(rawValue);
          widget.setAutoWidth(true);
          widget.setHeight(40);
          widget.setBorders(true);
          widget.setStyleName("bold-red-borders");
          widget.addListener(Events.OnClick, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
              widget.hide();
              removeTag(widget);
            }
          });
          widget.setStyleName("text-color-red");
          addTag(widget, rawValue);
          widget.getElement().getStyle().setColor("red");
          privateLabelField.show();
        } else {
          final LabelField widget = new LabelField(combo.getValue().getName());
          widget.setAutoWidth(true);
          widget.setHeight(40);
          widget.setBorders(true);
          widget.setStyleName("bold-borders");
          widget.addListener(Events.OnClick, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
              widget.hide();
              removeTag(widget);
            }
          });
          addTag(widget, combo.getValue().getName());
        }
      }

      private void addTag(LabelField widget, String name) {
        components.add(widget);
        Stock stockToSave = new Stock(name, "private", 33, 2);
        if (!isComboAlreadyContainsThisTag(name)) {
          combo.getStore().add(stockToSave);
        }
        repaintTextArea();
      }

      private boolean isComboAlreadyContainsThisTag(String name) {
        for (Stock stock: combo.getStore().getModels()){
          if(stock.getName().equals(name)) {
            return true;
          }
        }
        return false;
      }

      private void removeTag(LabelField widget){
        components.remove(widget);
        repaintTextArea();
      }

      private void repaintTextArea() {
        ColumnData layoutData = new ColumnData();
        layoutContainer.getItems().clear();
        for (Component component: components) {
            layoutContainer.add(component, layoutData);
        }
        layoutContainer.layout(true);
      }

    });
    horizontalPanel.add(addButton);
    dialog.add(horizontalPanel);

    privateLabelField = new LabelField("Tags in red will be created as Private tags");
    privateLabelField.setStyleName("text-color-red");
    privateLabelField.hide();
    privateLabelField.getElement().getStyle().setTextAlign(com.google.gwt.dom.client.Style.TextAlign.RIGHT);
    privateLabelField.getElement().getStyle().setMargin(10, com.google.gwt.dom.client.Style.Unit.PX);
    dialog.add(privateLabelField);

    LabelField tagsAppliedField = new LabelField("Tags Applied - Click to remove");
    dialog.add(tagsAppliedField);

    dialog.add(layoutContainer);

    dialog.show();
  }

}  