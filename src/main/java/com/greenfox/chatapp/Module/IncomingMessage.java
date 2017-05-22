package com.greenfox.chatapp.Module;


public class IncomingMessage {

  private client client;
  private Message message;

  public IncomingMessage() {
  }

  public IncomingMessage(client someone, Message text) {
    this.client = someone;
    this.message = text;
  }

  public client getClient() {
    return client;
  }

  public void setClient(client client) {
    this.client = client;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }
}
