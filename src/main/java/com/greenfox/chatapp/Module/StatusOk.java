package com.greenfox.chatapp.Module;



public class StatusOk implements ReturnMessage{

  private String status = "ok";

  public StatusOk() {
  }

  public StatusOk(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
