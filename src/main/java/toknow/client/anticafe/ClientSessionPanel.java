package toknow.client.anticafe;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Created by dmitry on 11.07.15.
 */
public class ClientSessionPanel extends Composite {
  private HorizontalPanel mainPanel;
  private Label clientNameLabel;
  private Button start30MinutesButton;
  private Button checkLeftTimeButton;
  private Button removeFromParentButton;
  private Label clientStartTimeLabel;
  private Label clientEndTimeLabel;
  private Label leftTimeLabel;
  private Label defaultHoursLabel;
  private Label overTimeHoursLabel;
  private Label totalSumLabel;
  private Label timeIsOverLabel;
  private Timer timer;
  private Timer refreshLeftTimeLabelTimer;
  private long scheduledTime = 10000;
  private long startTime = System.currentTimeMillis();
  private long finishTime = startTime + scheduledTime;

  public ClientSessionPanel(Client client) {
    mainPanel = new HorizontalPanel();
    initWidget(mainPanel);
    mainPanel.setWidth("500px");
    setHeight("100px");
    mainPanel.getElement().getStyle().setBackgroundColor("yellow");
    clientNameLabel = new Label("first label");
    clientNameLabel.setWidth("100px");
    clientNameLabel.getElement().getStyle().setBackgroundColor("blue");


    timeIsOverLabel = new Label("Time is over!!!");
    timeIsOverLabel.setVisible(false);
    timeIsOverLabel.getElement().getStyle().setColor("red");

    mainPanel.add(timeIsOverLabel);


    start30MinutesButton = new Button("Start 3 sec");
    start30MinutesButton.addStyleName("start-button");
    mainPanel.add(start30MinutesButton);
    start30MinutesButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        startTime = System.currentTimeMillis();
        finishTime = startTime + scheduledTime;
        timer = new Timer() {
          @Override
          public void run() {
            if (timeIsOverLabel.isVisible()){
             timeIsOverLabel.setVisible(false);
            } else {
              timeIsOverLabel.setVisible(true);
            }
          }
        };
        timer.schedule((int) scheduledTime);
        refreshLeftTimeLabelTimer = new Timer() {
          @Override
          public void run() {
            long timeLeft = finishTime - System.currentTimeMillis();
            if (timeLeft > 0) {
              leftTimeLabel.setText(String.valueOf(timeLeft));
            } else {
              leftTimeLabel.setText("Your ordered time is over!!!");
            }
          }
        };
        refreshLeftTimeLabelTimer.scheduleRepeating(1000);
      }
    });

    leftTimeLabel = new Label("Left time:");
    mainPanel.add(leftTimeLabel);

    checkLeftTimeButton = new Button("Check time left");
    mainPanel.add(checkLeftTimeButton);
    checkLeftTimeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Window.alert(String.valueOf(finishTime - System.currentTimeMillis()));
      }
    });

    removeFromParentButton = new Button("Finalize Session");
    removeFromParentButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
       ClientSessionPanel.this.removeFromParent();
      }
    });
    mainPanel.add(removeFromParentButton);

    mainPanel.add(clientNameLabel);
  }
}
