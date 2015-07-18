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
  private Button stopTheSessionButton;
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
  private long finishTime;
  private long minTime = 60000; // minimum period 1 minutes
  private long minPayment = 3500;

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

  public boolean isFirstAdmin() {
    return isFirstAdmin;
  }

  public ClientSessionPanel(final boolean isSuperAdmin, final boolean isFirstAdmin, final boolean isSecondAdmin, long id, String name, String comment, final long startTime, long totalSum) {


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
    for (int i = 0; i < clientNameInput.getItemCount(); i++) {
      if(clientNameInput.getItemText(i).equals(name)) {
        clientNameInput.setSelectedIndex(i);
      }
    }

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
    clientCommentInput.setWidth("277px");
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

    Label remindLabel = new Label("Напомнить: ");
    remindLabel.addStyleName("name-label");
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

    final Label totalTimeLabel = new Label("Время:");
    totalTimeLabel.addStyleName("total-time-style");
    mainPanel.add(totalTimeLabel);

    final Label totalTimeValue = new Label("00:00:00");
    totalTimeValue.addStyleName("total-time-style");
    mainPanel.add(totalTimeValue);

    startSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {

        if (clientId == 0) {
          ClientSessionPanel.this.startTime = System.currentTimeMillis();
          clientsServiceAsync.addClient(isSuperAdmin, isFirstAdmin, isSecondAdmin, 0, clientNameInput.getValue(clientNameInput.getSelectedIndex()), clientCommentInput.getValue(),
                  ClientSessionPanel.this.startTime, totalSumCurrentValue , new AsyncCallback<Long>() {
                    public void onFailure(Throwable caught) {
                      // Show the RPC error message to the user
                      String s = "dfd";
                    }

                    public void onSuccess(Long result) {
                      clientId = result;
                    }
                  });
        }


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
      refreshTotalSumTimer.scheduleRepeating(1000);
    }



    mainPanel.add(startSessionButton);
    stopTheSessionButton = new Button();
    stopTheSessionButton.addStyleName("stop-button");
    stopTheSessionButton.setTitle("Стоп");
    stopTheSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        clientsServiceAsync.stopSession(clientId, new AsyncCallback<Void>() {
          public void onFailure(Throwable caught) {
            String s = "sdfsd";
          }

          public void onSuccess(Void result) {
            stopSession();
          }
        });

      }
    });
    mainPanel.add(stopTheSessionButton);

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

  public void stopSession() {
    remindTimeTimer.cancel();
    refreshTotalSumTimer.cancel();
  }

  private void updateTotalSum(Label totalTimeValue) {
    currentTimeValue = System.currentTimeMillis() - this.startTime;
    totalTimeValue.setText(getMinutesString(currentTimeValue));
    long currentIntervalSeconds = getSeconds(currentTimeValue);
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
    totalSumLabel = new Label("СУММА:");
    totalSumLabel.addStyleName("total-sum");
    mainPanel.add(totalSumLabel);

    totalSumValue = new Label("00,00");
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
}
