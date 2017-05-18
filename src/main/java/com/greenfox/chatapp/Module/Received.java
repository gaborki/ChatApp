package com.greenfox.chatapp.Module;


public class Received {

  private Client client;
  private Message message;

  public Received() {
  }

  public Received(Client client, Message message) {
    this.client = client;
    this.message = message;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }
}
