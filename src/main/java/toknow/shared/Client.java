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
  private boolean isInProgress;
  private long limitTime = 1000 * 60 * 60 * 5;

  public Client(boolean isInProgress, boolean isFirstAdmin, long id, String name, String comment, long totalTime, long totalSum) {
    this.isInProgress = isInProgress;
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

  public boolean isFirstAdmin() {
    return isFirstAdmin;
  }

  public void setFirstAdmin(boolean isFirstAdmin) {
    this.isFirstAdmin = isFirstAdmin;
  }

  public boolean isInProgress() {
    return isInProgress;
  }

  public void setInProgress(boolean isInProgress) {
    this.isInProgress = isInProgress;
  }

  public long getLimitTime() {
    return limitTime;
  }

  public void setLimitTime(long limitTime) {
    this.limitTime = limitTime;
  }
}
