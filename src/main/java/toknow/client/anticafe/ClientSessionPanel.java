package toknow.client.anticafe;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.math.BigDecimal;


/**
 * Created by dmitry on 11.07.15.
 */
public class ClientSessionPanel extends Composite {

//  private boolean superAdmin;
//  public ClientSessionPanel(long id, String name, String comment, long totalTime, long totalSum) {
//    this.clientId = id;
//    this.clientNameInput.setText(name);
//    this.clientCommentInput.setText(comment);
//    this.totalTimeValue.setText(getMinutesString(totalTime));
//    this.totalSumValue.setText(getPrettyMoney(totalSum));
//  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  private Audio notificationEmailAudio;

  private long clientId;
  private long creationalTime;

  public long getCreationalTime() {
    return creationalTime;
  }

  public void setCreationalTime(long creationalTime) {
    this.creationalTime = creationalTime;
  }

  private HorizontalPanel mainPanel;
  private Label clientNameLabel;
  private ListBox clientNameInput;
//  private TextBox clientNameInput;
  private ListBox whoseBox;

  private Label commentLabel;
  private TextArea clientCommentInput;
  private CheckBox remindAfterCheckBox;
  private TextBox remindAfterInput;
  private TextBox remindAfterMinutes;
  private Button startSessionButton;
  private Label totalTimeLabel;
  private Button checkLeftTimeButton;
  private Button closeTheSessionButton;
  private Button stopSessionButton;
  private Label clientStartTimeLabel;
  private Label clientEndTimeLabel;
  private Label leftTimeLabel;
  private Label defaultHoursLabel;
  private Label overTimeHoursLabel;
  private Label totalTimeValue;
  private Label totalSumLabel;
  private Label totalSumValue;
  private Timer remindAfterTimer;
  private Timer remindTimeTimer;
  private Timer refreshTotalSumTimer;
  private long scheduledToRemindTime = 10000; // remind in 100 seconds
  private long startTime;

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  private long finishTime;
  private long minTime = 60000; // minimum period 1 minutes
  private long minPayment = 3500;
  private long maxLength = 1000 * 60 * 4;

  private final ClientsServiceAsync clientsServiceAsync = GWT.create(ClientsService.class);
  private long currentTimeValue;
  private long totalSumCurrentValue;

  public long getTotalSumCurrentValue() {
    return totalSumCurrentValue;
  }

  private boolean isFirstAdmin;
  private boolean isSecondAdmin;
  private boolean isSuperAdmin;

  private boolean isInProgress;
  private boolean isAccepted;

  public boolean isInProgress() {
    return isInProgress;
  }

  public void setInProgress(boolean isInProgress) {
    this.isInProgress = isInProgress;
  }

  public boolean isAccepted() {
    return isAccepted;
  }

  public void setAccepted(boolean isAccepted) {
    this.isAccepted = isAccepted;
  }

  public boolean isFirstAdmin() {
    return isFirstAdmin;
  }

  public void setFirstAdmin(boolean isFirstAdmin) {
    this.isFirstAdmin = isFirstAdmin;
  }

  public ClientSessionPanel(long creationalTime, final boolean isSuperAdmin, final boolean isFirstAdmin, final boolean isSecondAdmin, long id, String name, String comment, final long startTime, long totalSum, boolean inProgress) {

    this.creationalTime = creationalTime;
    notificationEmailAudio = Audio.createIfSupported();
    notificationEmailAudio.setSrc("sounds/email_notification.wav");
    this.isFirstAdmin = isFirstAdmin;
    this.isSuperAdmin = isSuperAdmin;
    this.isSecondAdmin = isSecondAdmin;
    this.clientId = id;
    mainPanel = new HorizontalPanel();
    initWidget(mainPanel);
    mainPanel.setWidth("500px");
    setHeight("20px");
    mainPanel.addStyleName("session-panel");


    whoseBox = new ListBox();
    whoseBox.setWidth("35px");
    whoseBox.addStyleName("custom-input");
    whoseBox.addItem(WhoseSessionEnum.FIRST.getShortName());
    whoseBox.addItem(WhoseSessionEnum.SECOND.getShortName());
    whoseBox.addItem(WhoseSessionEnum.ADMIN.getShortName());
    if (isSuperAdmin) {
      whoseBox.setSelectedIndex(2);
    } else if (isFirstAdmin) {
      whoseBox.setSelectedIndex(0);
    } else if (isSecondAdmin) {
      whoseBox.setSelectedIndex(1);
    }

    whoseBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
       ClientSessionPanel.this.isFirstAdmin = false;
       ClientSessionPanel.this.isSecondAdmin = false;
       ClientSessionPanel.this.isSuperAdmin = false;
        if (whoseBox.getSelectedIndex() == 0) {
          ClientSessionPanel.this.isFirstAdmin = true;
        } else if (whoseBox.getSelectedIndex() == 1) {
          ClientSessionPanel.this.isSecondAdmin = true;
        } else if (whoseBox.getSelectedIndex() == 2) {
          ClientSessionPanel.this.isSuperAdmin = true;
        }
        clientsServiceAsync.updateSessionOwner(clientId, ClientSessionPanel.this.isSuperAdmin,
                ClientSessionPanel.this.isFirstAdmin, ClientSessionPanel.this.isSecondAdmin, new AsyncCallback<Void>() {
                  public void onFailure(Throwable caught) {
                    String s = "dfsddf";
                  }

                  public void onSuccess(Void result) {
                    System.out.println("client is updated");
                  }
                });
      }
    });

    mainPanel.add(whoseBox);

    clientNameLabel = new Label("Имя: ");
    clientNameLabel.addStyleName("name-label");
    mainPanel.add(clientNameLabel);

    clientNameInput = new ListBox();
    clientNameInput.setWidth("150px");
    clientNameInput.addStyleName("custom-input");
    clientNameInput.addItem("Выберите имя");
//    clientNameInput.setSeValue(0, "Выберите имя");
    clientNameInput.addItem("Моцарт");
    clientNameInput.addItem("Бах");
    clientNameInput.addItem("Билык");
//    int foundIndex = 0;
    setClientNameValue(name);

    if (isSuperAdmin) {
      whoseBox.setSelectedIndex(2);
    } else if (isFirstAdmin) {
      whoseBox.setSelectedIndex(0);
    } else if (isSecondAdmin) {
      whoseBox.setSelectedIndex(1);
    }
//    clientNameInput.getElement().getStyle().setBackgroundColor("blue");
    mainPanel.add(clientNameInput);

    commentLabel = new Label("Комментарий: ");
    commentLabel.addStyleName("name-label");
    mainPanel.add(commentLabel);

    clientCommentInput = new TextArea();
    clientCommentInput.addStyleName("custom-input");
    clientCommentInput.setWidth("170px");
    clientCommentInput.setHeight("30px");
    clientCommentInput.setText(comment);
    mainPanel.add(clientCommentInput);

    remindAfterCheckBox = new CheckBox();
    remindAfterCheckBox.addStyleName("custom-checkbox");
    remindAfterCheckBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        remindAfterInput.setEnabled(remindAfterCheckBox.getValue());
        if (!remindAfterCheckBox.getValue()) {
          remindAfterTimer.cancel();
        }
      }
    });

    Label remindLabel = new Label();
    remindLabel.addStyleName("remind-style");
    mainPanel.add(remindLabel);
    mainPanel.add(remindAfterCheckBox);

    final Label remindAfterLabel = new Label("через: ");
    remindAfterLabel.addStyleName("name-label");
    mainPanel.add(remindAfterLabel);
    remindAfterInput = new TextBox();
//    remindAfterInput.addKeyPressHandler(new KeyPressHandler() {
////      @Override
//      public void onKeyPress(KeyPressEvent event) {
//        String input = remindAfterInput.getText();
//        if (!input.matches("[0-9]*")) {
//          // show some error
//          remindAfterInput.cancelKey();
//          return;
//        }
//        // do your thang
//      }
//    });
    remindAfterInput.addStyleName("remind-input");
    remindAfterInput.setValue(String.valueOf(scheduledToRemindTime / 1000));
    remindAfterInput.setWidth("50px");
    remindAfterInput.setEnabled(false);
    mainPanel.add(remindAfterInput);
    Label minutesLabel = new Label("мин.");
    minutesLabel.addStyleName("name-label");
    mainPanel.add(minutesLabel);

    startSessionButton = new Button();
    startSessionButton.addStyleName("start-button");
    startSessionButton.setTitle("Старт");
//    mainPanel.add(startSessionButton);

    final Label totalTimeLabel = new Label();
    totalTimeLabel.addStyleName("total-time-style");
    totalTimeLabel.addStyleName("time-style");
    mainPanel.add(totalTimeLabel);

    final Label totalTimeValue = new Label("00:00:00");
    totalTimeValue.addStyleName("total-time-style");
    mainPanel.add(totalTimeValue);

    startSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {

//        if (clientId == 0) {
          ClientSessionPanel.this.startTime = System.currentTimeMillis();
          clientsServiceAsync.startSession(clientId, ClientSessionPanel.this.startTime,  new AsyncCallback<Void>() {
                    public void onFailure(Throwable caught) {
                      // Show the RPC error message to the user
                      String s = "dfd";
                    }

                    public void onSuccess(Void result) {
//                      clientId = result;
                      startSessionButton.setEnabled(false);
                      startSessionButton.addStyleName("disabled-style");
                      stopSessionButton.setEnabled(true);
                      stopSessionButton.removeStyleName("disabled-style");
                      refreshTotalSumTimer.scheduleRepeating(1000);
                    }
                  });
//        } else {
//          refreshTotalSumTimer.scheduleRepeating(1000);
//        }


        finishTime = ClientSessionPanel.this.startTime + Long.valueOf(remindAfterInput.getValue()) * 1000;

        remindTimeTimer = new Timer() {
          @Override
          public void run() {
            long timeLeft = finishTime - System.currentTimeMillis();
            if (timeLeft > 0) {
              remindAfterInput.setText(getMinutesString(timeLeft));
            } else {
              notificationEmailAudio.play();
              remindAfterInput.setText("ALERT");
              remindAfterInput.getElement().getStyle().setBackgroundColor("red");
              remindTimeTimer.cancel();
            }
          }
        };
        if (remindAfterCheckBox.getValue()) {
          remindTimeTimer.scheduleRepeating(1000);
        }

        refreshTotalSumTimer.scheduleRepeating(1000);
      }
    });
    createTotalSumSection();

    this.startTime = startTime;
    refreshTotalSumTimer = new Timer() {
      @Override
      public void run() {
        updateTotalSum(totalTimeValue);
      }
    };
    if (clientId != 0) {
      currentTimeValue = System.currentTimeMillis() - this.startTime;
      totalTimeValue.setText(getMinutesString(currentTimeValue));
      long currentIntervalSeconds = getSeconds(currentTimeValue);
      if (currentIntervalSeconds <= getSeconds(minTime)) {
//          totalSumCurrentValue = minPayment;
        totalSumValue.setText(getPrettyMoney(minPayment));
        totalSumCurrentValue = minPayment;
      } else {

//            BigDecimal totalSum = BigDecimal.valueOf(totalSumCurrentValue + 50);
//            totalSumCurrentValue = totalSum.longValue();
          totalSum = minPayment + 50 * (currentIntervalSeconds - minTime / 1000) / 60;
          totalSumCurrentValue = totalSum;
          totalSumValue.setText(getPrettyMoney(totalSum));
      }

//      if (getSeconds(currentTimeValue) < maxLength/ 1000) {
      if (inProgress) {
        refreshTotalSumTimer.scheduleRepeating(1000);
      } else {
        stopSessionOnServer();
      }
    }



    mainPanel.add(startSessionButton);
    stopSessionButton = new Button();
    stopSessionButton.addStyleName("stop-button");
    stopSessionButton.setEnabled(false);
    stopSessionButton.addStyleName("disabled-style");
    stopSessionButton.setTitle("Стоп");
    stopSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        stopSession();
        clientsServiceAsync.stopSession(clientId, new AsyncCallback<Void>() {
          public void onFailure(Throwable caught) {
            String s = "sdfsd";
          }

          public void onSuccess(Void result) {
            stopSessionButton.setEnabled(false);
            stopSessionButton.addStyleName("disabled-style");
            startSessionButton.setEnabled(true);
            startSessionButton.removeStyleName("disabled-style");
          }
        });

      }
    });
    mainPanel.add(stopSessionButton);

toggleStartStopButtonsAvailable();

    closeTheSessionButton = new Button();
    closeTheSessionButton.addStyleName("remove-button");
    closeTheSessionButton.setTitle("В архив");
    closeTheSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        clientsServiceAsync.acceptSession(clientId, new AsyncCallback<Void>() {
          public void onFailure(Throwable caught) {
            String s = "dfd";
          }

          public void onSuccess(Void result) {
            ClientSessionPanel.this.setVisible(false);
            System.out.println("accepted");
          }
        });
        ClientSessionPanel.this.removeFromParent();
      }
    });
    mainPanel.add(closeTheSessionButton);
  }

  public void setClientNameValue(String name) {
    for (int i = 0; i < clientNameInput.getItemCount(); i++) {
      if(clientNameInput.getItemText(i).equals(name)) {
        clientNameInput.setSelectedIndex(i);
      }
    }
  }

  public void setClientCommentValue(String commentValue) {
    clientCommentInput.setText(commentValue);
  }

  public void stopSessionOnServer() {
    clientsServiceAsync.stopSession(clientId, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        String s = "dfdsf";
      }

      public void onSuccess(Void result) {
        System.out.println("Session is stopped");
      }
    });
  }

  public void stopSession() {
    if (remindTimeTimer != null && remindTimeTimer.isRunning()) {
      remindTimeTimer.cancel();
    }
    refreshTotalSumTimer.cancel();
  }

  public void startSession() {
    if (remindTimeTimer != null && remindTimeTimer.isRunning()) {
      remindTimeTimer.cancel();
    }
    refreshTotalSumTimer.scheduleRepeating(1000);
  }

  private void updateTotalSum(Label totalTimeValue) {
    currentTimeValue = System.currentTimeMillis() - this.startTime;
    totalTimeValue.setText(getMinutesString(currentTimeValue));
    long currentIntervalSeconds = getSeconds(currentTimeValue);
    //TODO
//    if (currentIntervalSeconds > maxLength/1000) {
//      stopSession();
//      stopSessionOnServer();
//      return;
//    }
    if (currentIntervalSeconds <= getSeconds(minTime)) {
//          totalSumCurrentValue = minPayment;
      totalSumValue.setText(getPrettyMoney(minPayment));
      totalSumCurrentValue = minPayment;
    } else {
      if ((currentIntervalSeconds - minTime / 1000) % 60 == 0) {

//            BigDecimal totalSum = BigDecimal.valueOf(totalSumCurrentValue + 50);
//            totalSumCurrentValue = totalSum.longValue();
        long totalSum = minPayment + 50 * (currentIntervalSeconds - minTime / 1000) / 60;
        totalSumCurrentValue = totalSum;
        totalSumValue.setText(getPrettyMoney(totalSum));
      }
    }
  }

  private void createTotalSumSection() {
    totalSumLabel = new Label();
    totalSumLabel.addStyleName("total-sum");
    totalSumLabel.addStyleName("money-style");
    mainPanel.add(totalSumLabel);

    totalSumValue = new Label("00.00");
    totalSumValue.addStyleName("total-sum");
    mainPanel.add(totalSumValue);
  }

  private String getPrettyMoney(long minPayment) {
    return new BigDecimal(minPayment).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
  }

  private long getMinutes(long timeLeft) {
    return (timeLeft/(1000*60))%60;
  }

  private long getSeconds(long timeLeft) {
    return (timeLeft/(1000));
  }

  private String getMinutesString(long timeLeft) {
    long minutes = (timeLeft / (1000 * 60)) % 60;
    String minutesString = padTimeValue(minutes);
    long hours = (timeLeft / (1000 * 60 * 60)) % 60;
    String hoursString = padTimeValue(hours);
    long seconds = (timeLeft / 1000) % 60;
    String secondsString = padTimeValue(seconds);
    return hoursString + ":"+ minutesString + ":" + secondsString;
  }

  private String padTimeValue(long timeUnit) {
    return timeUnit<10? "0"+timeUnit: String.valueOf(timeUnit);
  }

  public boolean isSecondAdmin() {
    return isSecondAdmin;
  }

  public void setSecondAdmin(boolean secondAdmin) {
    this.isSecondAdmin = secondAdmin;
  }

  public boolean isSuperAdmin() {
    return isSuperAdmin;
  }

  public void setSuperAdmin(boolean superAdmin) {
    this.isSuperAdmin = superAdmin;
  }

  public void toggleStartStopButtonsAvailable() {
    stopSessionButton.setEnabled(isInProgress);
    startSessionButton.setEnabled(!isInProgress);
    if (isInProgress) {
      stopSessionButton.removeStyleName("disabled-style");
      startSessionButton.addStyleName("disabled-style");
    } else {
      stopSessionButton.addStyleName("disabled-style");
      startSessionButton.removeStyleName("disabled-style");
    }
  }


}
