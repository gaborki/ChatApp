package com.greenfox.chatapp.Module;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
public class Message {

  @Id
  private long id;
  private String username;
  private String text;
  private Timestamp timestamp;

  public Message() {
    this.id = (long)(Math.random()*1000000 + 8999999);
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.username = "gaborki";
  }

  public Message(String message){
    this.id = (long)(Math.random()*1000000 + 8999999);
    this.username = "gaborki";
    this.text = message;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public Message(String userName, String text) {
    this.id = (long)(Math.random()*1000000 + 8999999);
    this.username = userName;
    this.text = text;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public Message(String userName, String text, long id, Timestamp timestamp) {
    this.id = id;
    this.username = userName;
    this.text = text;
    this.timestamp = timestamp;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
