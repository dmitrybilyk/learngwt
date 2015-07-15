package toknow.server.anticafe;

import toknow.shared.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dmitry on 13.07.15.
 */
public class ClientsHolder {
  public ClientsHolder() {
    clientMap.put(getClientId(), new Client(true, getClientId(), "first name", "first comment", 1000, 1000));
  }

  private Map<Long, Client> clientMap = new LinkedHashMap<Long, Client>();
  public Long addClient(boolean isFirstAdmin, long id, String name, String comment, long time, long sum) {
    long nextId = getClientId();
    clientMap.put(nextId, new Client(isFirstAdmin, nextId, name, comment, time, sum));
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
