package com.greenfox.chatapp.Module;


public class ErrorStatus {

  private String status = "error";
  private String message;

  public ErrorStatus() {
  }

  public ErrorStatus(String error) {
    this.message = "Missing filed(s): " + error;
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
