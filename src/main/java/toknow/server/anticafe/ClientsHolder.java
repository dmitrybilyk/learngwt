package toknow.server.anticafe;

import toknow.shared.Client;

import java.util.*;

/**
 * Created by dmitry on 13.07.15.
 */
public class ClientsHolder {
  public ClientsHolder() {
//    Timer timer = new Timer();
//    timer.schedule(new TimerTask() {
//      @Override
//      public void run() {
//        // Your database code here
//      }
//    }, 2*60*1000);
//    clientMap.put(getClientId(), new Client(true, true, getClientId(), "first name", "first comment", 1000, 1000));
  }

  private Map<Long, Client> clientMap = new LinkedHashMap<Long, Client>();
  public Long addClient(boolean isFirstAdmin, long id, String name, String comment, long startTime, long sum) {
    long nextId = getClientId();
    clientMap.put(nextId, new Client(true, isFirstAdmin, nextId, name, comment, startTime, sum));
    return nextId;
  }

  public void removeClient(long id) {
    clientMap.remove(id);
  }

  public ArrayList<Client> getClients() {
    ArrayList<Client> clientArrayList = new ArrayList<Client>();
    for (Client client: clientMap.values()) {
      clientArrayList.add(client);
    }
    return clientArrayList;
  }

  private long getClientId(){
    long id = 0;
    for (Client client: clientMap.values()) {
      if (client.getId() > id) {
        id = client.getId();
      }
    }
    return id + 1;
  }

}
