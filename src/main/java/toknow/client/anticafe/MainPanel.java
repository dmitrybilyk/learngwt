package toknow.client.anticafe;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import toknow.shared.Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitry on 11.07.15.
 */
public class MainPanel extends Composite {
  private VerticalPanel verticalLoginPanel;
  private Label loginMessageLabel;
  private TextBox userNameInput;
  private TextBox passwordInput;
  private Button loginButton;



  private Button addClientSessionButton;
  private VerticalPanel verticalPanel;
  private List<ClientSessionPanel> clientSessionPanelList;
  private final ClientsServiceAsync clientsServiceAsync = GWT.create(ClientsService.class);

  public MainPanel() {
//    addFirstClientToCheck();
//    getExistingSessions();
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.addStyleName("top-panel");
    horizontalPanel.setWidth("100%");

    final VerticalPanel sessionsPanel = new VerticalPanel();

    addClientSessionButton = new Button("Добавить сессию");
    addClientSessionButton.addStyleName("add-session-button");
    addClientSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
//       verticalPanel.add(new AddSessionDialog());
        boolean isCurrentlyFirst = verticalPanel.getStyleName().contains("first-style-name");
        if (isCurrentlyFirst) {
          sessionsPanel.add(new ClientSessionPanel(true, 0, "", "", 0, 0 ));
        } else {
          sessionsPanel.add(new ClientSessionPanel(false, 0, "", "", 0, 0 ));
        }

      }
    });

    verticalPanel = new VerticalPanel();
    initWidget(verticalPanel);

    verticalPanel.add(sessionsPanel);
    sessionsPanel.setVisible(false);
    addClientSessionButton.setVisible(false);

    horizontalPanel.add(addClientSessionButton);


    verticalLoginPanel = new VerticalPanel();

    horizontalPanel.add(verticalLoginPanel);


    verticalPanel.setWidth("100%");
//    verticalPanel.setHeight("800px");

    verticalPanel.getElement().setId("mainPanel");
    verticalPanel.addStyleName("main-panel-style");
    verticalPanel.add(horizontalPanel);

    final Button logoutButton = new Button("Выход");
    logoutButton.addStyleName("logout-button-style");
    horizontalPanel.add(logoutButton);
    logoutButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
//       MainPanel.this.setVisible(false);
        verticalLoginPanel.setVisible(true);
        verticalLoginPanel.addStyleName("vertical-login-panel");
        addClientSessionButton.setVisible(false);
        sessionsPanel.setVisible(false);
        logoutButton.setVisible(false);

      }
    });

    clientSessionPanelList = new ArrayList<ClientSessionPanel>();


    loginMessageLabel = new Label("Введите имя и пароль:");
    loginMessageLabel.addStyleName("login-message");
    verticalLoginPanel.add(loginMessageLabel);

    userNameInput = new TextBox();
    userNameInput.addStyleName("login-input");
    verticalLoginPanel.add(userNameInput);

    passwordInput = new TextBox();
    passwordInput.addStyleName("login-input");
    verticalLoginPanel.add(passwordInput);

    loginButton = new Button("Войти");
    logoutButton.addStyleName("login-message");
    verticalLoginPanel.add(loginButton);

    loginButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        sessionsPanel.setVisible(true);
        logoutButton.setVisible(true);
//        userNameInput.setValue("could be any)");
        addClientSessionButton.setVisible(true);
        verticalLoginPanel.setVisible(false);
        verticalPanel.setVisible(false);
        verticalPanel.setVisible(true);

        if (userNameInput.getValue().equals("1")) {
          MainPanel.this.removeStyleName("second-admin-style");
          verticalPanel.addStyleName("first-admin-style");
        } else if (userNameInput.getValue().equals("2")) {
          MainPanel.this.removeStyleName("first-admin-style");
          verticalPanel.addStyleName("second-admin-style");
        }

        Iterator<Widget> vPanelWidgets = sessionsPanel.iterator();
        while (vPanelWidgets.hasNext()){
          Widget childWidget = vPanelWidgets.next();
          if (childWidget instanceof ClientSessionPanel) {

            boolean isCurrentlyFirst = verticalPanel.getStyleName().contains("first-admin-style ");
            childWidget.setVisible((isCurrentlyFirst && ((ClientSessionPanel) childWidget).isFirstAdmin()) ||
                    isCurrentlyFirst==false && ((ClientSessionPanel) childWidget).isFirstAdmin() == false);
//            if(isCurrentlyFirst && ((ClientSessionPanel) childWidget).isFirstAdmin()) {
//              childWidget.setVisible(true);
//            } else if (!isCurrentlyFirst && ((ClientSessionPanel) childWidget).isFirstAdmin()) {
//              childWidget.setVisible(false);
//            }
          }
        }
//        addFirstClientToCheck();
      }
    });


    logoutButton.setVisible(false);
    sessionsPanel.setVisible(false);

  }

  private void addFirstClientToCheck() {
    clientsServiceAsync.addClient(true, 0, "first name", "first comment", 1000, 1000, new AsyncCallback<Long>() {
      public void onFailure(Throwable caught) {
        String s = "dfd";
      }

      public void onSuccess(Long result) {
        getExistingSessions();
      }
    });
  }

  private void getExistingSessions() {
    clientsServiceAsync.getClients(new AsyncCallback<ArrayList<toknow.shared.Client>>() {
      public void onFailure(Throwable caught) {
        String s = "dfd";
      }

      public void onSuccess(ArrayList<Client> result) {
        for (Client client: result) {
          verticalPanel.add(new ClientSessionPanel(MainPanel.this.getStyleName().contains("first-admin-style"), client.getId(), client.getName(), client.getComment(),
                  client.getTotalTime(), client.getTotalSum()));
        }
      }
    });
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
