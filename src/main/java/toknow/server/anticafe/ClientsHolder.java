package toknow.server.anticafe;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import toknow.server.anticafe.spring.quartz.HelloJob;
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
  public Long addClient(boolean isSuperAdmin, boolean isFirstAdmin, boolean isSecondAdmin, long id, String name, String comment, long startTime, long sum) {
    long nextId = getClientId();
//    try {
//      runQuartz();
//    } catch (SchedulerException e) {
//      e.printStackTrace();
//    }
    clientMap.put(nextId, new Client(true, isSuperAdmin, isFirstAdmin, isSecondAdmin, nextId, name, comment, startTime, sum));
    return nextId;
  }

  public Client getClientById(long id) {
    return clientMap.get(id);
  }

  private void runQuartz() throws SchedulerException {
    //Quartz 1.6.3
    //JobDetail job = new JobDetail();
    //job.setName("dummyJobName");
    //job.setJobClass(HelloJob.class);
    JobDetail job = JobBuilder.newJob(HelloJob.class)
            .withIdentity("dummyJobName", "group1").build();

    //Quartz 1.6.3
    //CronTrigger trigger = new CronTrigger();
    //trigger.setName("dummyTriggerName");
    //trigger.setCronExpression("0/5 * * * * ?");

    Trigger trigger = TriggerBuilder
            .newTrigger()
            .withIdentity("dummyTriggerName", "group1")
            .withSchedule(
                    CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
            .build();

    //schedule it
    Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    scheduler.start();
    scheduler.scheduleJob(job, trigger);

  }

  public void removeClient(long id) {
    clientMap.remove(id);
  }

  public void acceptClientSession(long id) {
    clientMap.get(id).setAccepted(true);
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
