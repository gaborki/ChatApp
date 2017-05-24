package com.greenfox.chatapp.Module;

import org.springframework.stereotype.Component;

@Component
public class JsonMessage {

  private Client Client;
  private Message message;

  public JsonMessage() {
  }

  public JsonMessage(Client someone, Message text) {
    this.Client = someone;
    this.message = text;
  }

  public Client getClient() {
    return Client;
  }

  public void setClient(Client Client) {
    this.Client = Client;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }
}
