package toknow.client.anticafe;

import java.io.Serializable;

/**
 * Created by dmitry on 11.07.15.
 */
public class Client implements Serializable{
  private String name;
  private String comment;

  public Client(String name, String comment) {
    this.name = name;
    this.comment = comment;
  }

  public Client() {
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
}
