package com.greenfox.chatapp.Module;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MessageToSend {

  @Id
  private long id;
  private String userName;
  private String text;

  public MessageToSend() {
    this.id = (long)(Math.random()*1000000 + 8999999);
  }

  public MessageToSend(String userName, String text) {
    this.id = (long)(Math.random()*1000000 + 8999999);
    this.userName = userName;
    this.text = text;
  }

  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
