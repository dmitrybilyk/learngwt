package toknow.client.anticafe;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import toknow.shared.*;

import java.util.ArrayList;
import java.util.Collection;

public interface ClientsServiceAsync {
  void addClient(boolean isFirstAdmin, long id, String name, String comment, long startTime, long totalSum, AsyncCallback<Long> async);
  void addSession(long id, String name, String comment, long totalTime, long totalSum, AsyncCallback<Void> async);
  void updateSession(long id, String name, String comment, long totalTime, long totalSum, AsyncCallback<Void> async);

  void getClients(AsyncCallback<ArrayList<toknow.shared.Client>> async);

  void removeSession(long id, AsyncCallback<Void> async);

  void sendCompleteNotification(long id, String name, String comment, long totalTime, long totalSum, AsyncCallback<Void> async);
}
