package toknow.client.anticafe;


import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import toknow.server.anticafe.*;

import java.math.BigDecimal;


/**
 * Created by dmitry on 11.07.15.
 */
public class ClientSessionPanel extends Composite {
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
  private Label commentLabel;
  private TextBox clientCommentInput;
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

  public boolean isFirstAdmin() {
    return isFirstAdmin;
  }

  public ClientSessionPanel(final boolean isFirstAdmin, long id, String name, String comment, long totalTime, long totalSum) {
    notificationEmailAudio = Audio.createIfSupported();
    notificationEmailAudio.setSrc("sounds/email_notification.wav");
    this.isFirstAdmin = isFirstAdmin;
    this.clientId = id;
    mainPanel = new HorizontalPanel();
    initWidget(mainPanel);
    mainPanel.setWidth("500px");
    setHeight("20px");
    mainPanel.addStyleName("session-panel");

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
//    clientNameInput.getElement().getStyle().setBackgroundColor("blue");
    mainPanel.add(clientNameInput);

    commentLabel = new Label("Комментарий: ");
    commentLabel.addStyleName("name-label");
    mainPanel.add(commentLabel);

    clientCommentInput = new TextBox();
    clientCommentInput.addStyleName("custom-input");
    clientCommentInput.setWidth("277px");
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

    startSessionButton = new Button("Начать");
    startSessionButton.addStyleName("start-button");
    mainPanel.add(startSessionButton);

    final Label totalTimeLabel = new Label("Время:");
    totalTimeLabel.addStyleName("total-time-style");
    mainPanel.add(totalTimeLabel);

    final Label totalTimeValue = new Label("00:00:00");
    totalTimeValue.addStyleName("total-time-style");
    mainPanel.add(totalTimeValue);

    startSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        startTime = System.currentTimeMillis();
        clientsServiceAsync.addClient(isFirstAdmin, 0, clientNameInput.getValue(clientNameInput.getSelectedIndex()), clientCommentInput.getValue(),
                startTime, totalSumCurrentValue , new AsyncCallback<Long>() {
                  public void onFailure(Throwable caught) {
                    // Show the RPC error message to the user
                    String s = "dfd";
                  }

                  public void onSuccess(Long result) {
                    clientId = result;
                  }
                });

        finishTime = startTime + Long.valueOf(remindAfterInput.getValue()) * 1000;

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

        refreshTotalSumTimer = new Timer() {
          @Override
          public void run() {
            currentTimeValue = System.currentTimeMillis() - startTime;
            totalTimeValue.setText(getMinutesString(currentTimeValue));
            long seconds = getSeconds(currentTimeValue);
            if (seconds <= getSeconds(minTime)) {
              totalSumCurrentValue = minPayment;
              totalSumValue.setText(getPrettyMoney(minPayment));
            } else {
              if ((seconds - minTime / 1000) % 60 == 0) {
                BigDecimal totalSum = BigDecimal.valueOf(totalSumCurrentValue + 50);
                totalSumCurrentValue = totalSum.longValue();
                totalSumValue.setText(getPrettyMoney(totalSum.longValue()));
              }
            }
          }
        };
        refreshTotalSumTimer.scheduleRepeating(1000);

      }
    });

    createTotalSumSection();

    stopTheSessionButton = new Button("Остановить");
    stopTheSessionButton.addStyleName("stop-button");
    stopTheSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        remindTimeTimer.cancel();
        refreshTotalSumTimer.cancel();
      }
    });
    mainPanel.add(stopTheSessionButton);

    closeTheSessionButton = new Button("Удалить");
    closeTheSessionButton.addStyleName("remove-button");
    closeTheSessionButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        clientsServiceAsync.removeSession(clientId, new AsyncCallback<Void>() {
          public void onFailure(Throwable caught) {
            String s = "dfd";
          }

          public void onSuccess(Void result) {
            System.out.println("removed");
          }
        });
        ClientSessionPanel.this.removeFromParent();
      }
    });
    mainPanel.add(closeTheSessionButton);
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

}
