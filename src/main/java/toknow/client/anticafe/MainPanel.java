package toknow.client.anticafe;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 11.07.15.
 */
public class MainPanel extends Composite {
  private Button addClientSessionButton;
  private VerticalPanel verticalPanel;
  private List<ClientSessionPanel> clientSessionPanelList;

  public MainPanel() {

    addClientSessionButton = new Button("Add Session");
    addClientSessionButton.addStyleName("add-session-button");
    addClientSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
       verticalPanel.add(new AddSessionDialog());
//       verticalPanel.add(new ClientSessionPanel(new Client("name", "comment")));
      }
    });


    verticalPanel = new VerticalPanel();
    verticalPanel.setWidth("800px");
//    verticalPanel.setHeight("800px");
    initWidget(verticalPanel);
    verticalPanel.getElement().setId("mainPanel");
    verticalPanel.add(addClientSessionButton);
    clientSessionPanelList = new ArrayList<ClientSessionPanel>();

  }

  @Override
  protected void onLoad() {
    super.onLoad();
//    for (ClientSessionPanel clientSessionPanel)
  }

  public List<ClientSessionPanel> getClientSessionPanelList() {
    return clientSessionPanelList;
  }



}
