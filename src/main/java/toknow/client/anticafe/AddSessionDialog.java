package toknow.client.anticafe;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Created by dmitry on 12.07.15.
 */
public class AddSessionDialog extends Composite{

  private FormPanel formPanel;
  private TextBox clientNameTextBox;
  private Button okButton;
  private Button cancelButton;

  public AddSessionDialog() {
    formPanel = new FormPanel();
    initWidget(formPanel);
    clientNameTextBox = new TextBox();
    formPanel.add(clientNameTextBox);
    okButton = new Button("CREATE");
    okButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (!clientNameTextBox.getValue().isEmpty()) {
          RootPanel.get("mainPanel").add(new ClientSessionPanel(new Client(clientNameTextBox.getValue(), clientNameTextBox.getValue())));
        }
      }
    });
    formPanel.add(okButton);
    cancelButton = new Button("Cancel");
    formPanel.add(cancelButton);
  }
}
