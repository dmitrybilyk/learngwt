package toknow.client.anticafe;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import toknow.shared.WhoseSessionEnum;

import java.util.ArrayList;

/**
 * Created by dmitry on 13.07.15.
 */
@RemoteServiceRelativePath("clientsService")
public interface ClientsService extends RemoteService{
  public Long addClient(WhoseSessionEnum whoseSessionEnum, long id, String name, String comment, long totalTime, long totalSum);
  public ArrayList<toknow.shared.Client> getClients();

  void addSession(long id, String name, String comment, long totalTime, long totalSum);

  void updateSession(long id, String name, String comment);
  void removeSession(long id);
  void sendCompleteNotification(long id, String name, String comment, long totalTime, long totalSum);

  void updateSessionOwner(long id, WhoseSessionEnum whoseSession);

  void stopSession(long id);

  void acceptSession(long id);

  void startSession(long id, long startTime);
}
