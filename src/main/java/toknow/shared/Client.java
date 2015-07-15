package toknow.shared;

import java.io.Serializable;

/**
 * Created by dmitry on 11.07.15.
 */
public class Client implements Serializable{
  private long id;

  private String name;
  private String comment;
  private long totalTime;
  private long totalSum;
  private boolean isFirstAdmin;

  public Client(boolean isFirstAdmin, long id, String name, String comment, long totalTime, long totalSum) {
    this.isFirstAdmin = isFirstAdmin;
    this.id = id;
    this.name = name;
    this.comment = comment;
    this.totalTime = totalTime;
    this.totalSum = totalSum;
  }

  public Client() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public long getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(long totalTime) {
    this.totalTime = totalTime;
  }

  public long getTotalSum() {
    return totalSum;
  }

  public void setTotalSum(long totalSum) {
    this.totalSum = totalSum;
  }

}
