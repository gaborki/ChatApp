package com.greenfox.chatapp.Module;


public class StatusError implements ReturnMessage {

  private String status = "error";
  private String message;

  public StatusError() {
  }

  public StatusError(String errorMessage) {
    this.message = "Missing field(s) :" + errorMessage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() { return message;
  }

  public void setMessage(String error) {
    this.message = error;
  }
}
