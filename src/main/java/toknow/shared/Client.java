package toknow.shared;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Created by dmitry on 11.07.15.
 */
public class Client implements Serializable, Comparable{
  private boolean isSecondAdmin;
  private boolean isSuperAdmin;
  private long id;

  private long creationalTime;
  private String name;
  private String comment;
  private long startTime;
  private long totalSum;
  private boolean isFirstAdmin;
  private boolean isInProgress;
  private long limitTime = 1000 * 60 * 60 * 5;
  private boolean accepted;

  public Client(boolean isInProgress, boolean isSuperAdmin, boolean isFirstAdmin, boolean isSecondAdmin, long id, String name, String comment, long startTime, long totalSum) {
    this.isInProgress = isInProgress;
    this.isSuperAdmin = isSuperAdmin;
    this.isFirstAdmin = isFirstAdmin;
    this.isSecondAdmin = isSecondAdmin;
    this.id = id;
    this.name = name;
    this.comment = comment;
    this.startTime = startTime;
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

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
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

  public boolean isSecondAdmin() {
    return isSecondAdmin;
  }

  public void setSecondAdmin(boolean isSecondAdmin) {
    this.isSecondAdmin = isSecondAdmin;
  }

  public boolean isSuperAdmin() {
    return isSuperAdmin;
  }

  public void setSuperAdmin(boolean isSuperAdmin) {
    this.isSuperAdmin = isSuperAdmin;
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

  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }

  public boolean isAccepted() {
    return accepted;
  }

  public long getCreationalTime() {
    return creationalTime;
  }

  public void setCreationalTime(long creationalTime) {
    this.creationalTime = creationalTime;
  }

  public int compareTo(@NotNull Object o) {
    Client compared = (Client) o;
    if (getCreationalTime() > compared.getCreationalTime()) {
     return 1;
    }
    return -1;
  }
}
