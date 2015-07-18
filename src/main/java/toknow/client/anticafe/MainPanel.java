package toknow.client.anticafe;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import toknow.shared.Client;

import java.math.BigDecimal;
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
  private CheckBox isShowAccepted = new CheckBox("Показывать завершенные");
  private Label totalSumLabel = new Label();



  private Button addClientSessionButton;
  private VerticalPanel verticalPanel;
  private List<ClientSessionPanel> clientSessionPanelList;
  private final ClientsServiceAsync clientsServiceAsync = GWT.create(ClientsService.class);

  public MainPanel() {
    addFirstClientToCheck();


    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.addStyleName("top-panel");
    horizontalPanel.setWidth("100%");

    final VerticalPanel sessionsPanel = new VerticalPanel();
    getExistingSessions();

    addClientSessionButton = new Button("Добавить сессию");
    addClientSessionButton.addStyleName("add-session-button");
    addClientSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
//       verticalPanel.add(new AddSessionDialog());
        boolean isCurrentlyFirst = verticalPanel.getStyleName().contains("first-admin-style");
        boolean isCurrentlySecond = verticalPanel.getStyleName().contains("second-admin-style");
        boolean isCurrentlyAdmin = verticalPanel.getStyleName().contains("super-admin-style");
        addSessionToUser(isCurrentlyFirst, isCurrentlySecond, isCurrentlyAdmin, sessionsPanel);

      }
    });

    verticalPanel = new VerticalPanel();
    initWidget(verticalPanel);

    verticalPanel.add(sessionsPanel);
    sessionsPanel.setVisible(false);
    addClientSessionButton.setVisible(false);

    horizontalPanel.add(addClientSessionButton);

    totalSumLabel.setVisible(false);
    totalSumLabel.addStyleName("total-sum-style");
    horizontalPanel.add(totalSumLabel);

    final Timer updateTotalSumTimer = creatTotalSumTimer(sessionsPanel);

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
        totalSumLabel.setVisible(false);
        userNameInput.setValue("");
//        totalSumLabel.setText("0.00");
//        updateTotalSumTimer.cancel();

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
        totalSumLabel.setVisible(false);
        sessionsPanel.setVisible(true);
        logoutButton.setVisible(true);
//        userNameInput.setValue("could be any)");
        addClientSessionButton.setVisible(true);
        verticalLoginPanel.setVisible(false);
        verticalPanel.setVisible(false);
        verticalPanel.setVisible(true);
        totalSumLabel.setVisible(true);

        logoutButton.setText("Выход пользователя " + " " + userNameInput.getValue());
//        Timer updateTotalSumTimer = creatTotalSumTimer(sessionsPanel);

        if (userNameInput.getValue().equals("1")) {
          MainPanel.this.removeStyleName("second-admin-style");
          MainPanel.this.removeStyleName("super-admin-style");
          verticalPanel.addStyleName("first-admin-style");
        } else if (userNameInput.getValue().equals("2")) {
          MainPanel.this.removeStyleName("first-admin-style");
          MainPanel.this.removeStyleName("super-admin-style");
          verticalPanel.addStyleName("second-admin-style");
        } else if (userNameInput.getValue().equals("admin")) {
          MainPanel.this.removeStyleName("first-admin-style");
          MainPanel.this.removeStyleName("second-admin-style");
          verticalPanel.addStyleName("super-admin-style");
        }

        Iterator<Widget> vPanelWidgets = sessionsPanel.iterator();
        while (vPanelWidgets.hasNext()) {
          Widget childWidget = vPanelWidgets.next();
          if (childWidget instanceof ClientSessionPanel) {
            boolean isCurrentlyFirst = verticalPanel.getStyleName().contains("first-admin-style");
            boolean isCurrentlySecond = verticalPanel.getStyleName().contains("second-admin-style");
            boolean isCurrentlyAdmin = verticalPanel.getStyleName().contains("super-admin-style");
            if (isCurrentlyFirst) {
              childWidget.setVisible(((ClientSessionPanel) childWidget).isFirstAdmin());
              childWidget.removeStyleName("second-admin-style");
              childWidget.removeStyleName("super-admin-style");
              childWidget.addStyleName("first-admin-style");
            } else if (isCurrentlySecond) {
              childWidget.removeStyleName("first-admin-style");
              childWidget.removeStyleName("super-admin-style");
              childWidget.addStyleName("second-admin-style");
              childWidget.setVisible(((ClientSessionPanel) childWidget).isSecondAdmin());
            } else if (isCurrentlyAdmin) {
              childWidget.removeStyleName("second-admin-style");
              childWidget.removeStyleName("first-admin-style");
              childWidget.addStyleName("super-admin-style");
              childWidget.setVisible(true);
            } else {
              childWidget.removeStyleName("second-admin-style");
              childWidget.removeStyleName("super-admin-style");
              childWidget.removeStyleName("first-admin-style");
              childWidget.setVisible(false);
            }
          }
        }
//        addFirstClientToCheck();
      }
    });


    logoutButton.setVisible(false);
    sessionsPanel.setVisible(false);

  }

  private void addSessionToUser(boolean isCurrentlyFirst, boolean isCurrentlySecond, boolean isCurrentlyAdmin, VerticalPanel sessionsPanel) {
    if (isCurrentlyFirst) {
      sessionsPanel.add(new ClientSessionPanel(false, true, false, 0, "", "", 0, 0 ));
    } else if (isCurrentlySecond){
      sessionsPanel.add(new ClientSessionPanel(false, false, true, 0, "", "", 0, 0 ));
    } else if (isCurrentlyAdmin) {
      sessionsPanel.add(new ClientSessionPanel(true, false, false, 0, "", "", 0, 0 ));
    } else {
      sessionsPanel.add(new ClientSessionPanel(false, false, false, 0, "", "", 0, 0 ));
    }
  }

  private Timer creatTotalSumTimer(final VerticalPanel sessionsPanel) {
    final Timer updateTotalSumTimer = new Timer() {
      @Override
      public void run() {
        boolean isCurrentlyFirst = verticalPanel.getStyleName().contains("first-admin-style");
        boolean isCurrentlySecond = verticalPanel.getStyleName().contains("second-admin-style");
        boolean isCurrentlyAdmin = verticalPanel.getStyleName().contains("super-admin-style");
        long totalSum = 0;
        Iterator<Widget> vPanelWidgets = sessionsPanel.iterator();
        while (vPanelWidgets.hasNext()){

            Widget childWidget = vPanelWidgets.next();
            if (childWidget instanceof ClientSessionPanel) {
              if (isCurrentlyFirst) {
                if (((ClientSessionPanel) childWidget).isFirstAdmin()) {
                  totalSum += ((ClientSessionPanel) childWidget).getTotalSumCurrentValue();
                }
              } else if (isCurrentlySecond) {
                if (((ClientSessionPanel) childWidget).isSecondAdmin()) {
                  totalSum += ((ClientSessionPanel) childWidget).getTotalSumCurrentValue();
                }
              } else if (isCurrentlyAdmin) {
                totalSum += ((ClientSessionPanel) childWidget).getTotalSumCurrentValue();
              }
          }
        }
        totalSumLabel.setText(getPrettyMoney(totalSum));
      }
    };
    updateTotalSumTimer.scheduleRepeating(1000);
    return updateTotalSumTimer;
  }

  private void addFirstClientToCheck() {
    clientsServiceAsync.addClient(false, true, false, 0, "first name", "first comment", 1000, 1000, new AsyncCallback<Long>() {
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
        boolean isCurrentlyFirst = verticalPanel.getStyleName().contains("first-admin-style");
        boolean isCurrentlySecond = verticalPanel.getStyleName().contains("second-admin-style");
        boolean isCurrentlyAdmin = verticalPanel.getStyleName().contains("super-admin-style");
        for (Client client: result) {
          verticalPanel.insert(new ClientSessionPanel(verticalPanel.getStyleName().contains("super-admin-style"),
                  verticalPanel.getStyleName().contains("first-admin-style"),
                  verticalPanel.getStyleName().contains("second-admin-style"),
                  client.getId(), client.getName(), client.getComment(),
                  client.getTotalTime(), client.getTotalSum()), 1);

          addSessionToUser(isCurrentlyFirst, isCurrentlySecond, isCurrentlyAdmin, verticalPanel);
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

  private String getPrettyMoney(long minPayment) {
    return new BigDecimal(minPayment).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
  }


}
