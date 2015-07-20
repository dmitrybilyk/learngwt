package toknow.client.anticafe;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import toknow.shared.*;

import java.util.ArrayList;
import java.util.Collection;

public interface ClientsServiceAsync {
  void addClient(boolean isSuperAdmin,boolean isFirstAdmin, boolean isSecondAdmin, long id, String name, String comment, long startTime, long totalSum, AsyncCallback<Long> async);
  void addSession(long id, String name, String comment, long totalTime, long totalSum, AsyncCallback<Void> async);
  void updateSession(boolean isSuperAdmin,boolean isFirstAdmin, boolean isSecondAdmin, long id, String name, String comment, long startTime, long totalSum, AsyncCallback<Void> async);
  void updateSessionOwner(long id, boolean isSuperAdmin,boolean isFirstAdmin, boolean isSecondAdmin, AsyncCallback<Void> async);

  void getClients(AsyncCallback<ArrayList<toknow.shared.Client>> async);

  void removeSession(long id, AsyncCallback<Void> async);
  void acceptSession(long id, AsyncCallback<Void> async);
  void stopSession(long id, AsyncCallback<Void> async);
  void startSession(long id, long startTime, AsyncCallback<Void> async);

  void sendCompleteNotification(long id, String name, String comment, long totalTime, long totalSum, AsyncCallback<Void> async);
}
