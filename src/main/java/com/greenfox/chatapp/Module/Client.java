package com.greenfox.chatapp.Module;

import org.springframework.stereotype.Component;


public class Client {

  private int id;

  public Client() {
  }

  public Client(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
