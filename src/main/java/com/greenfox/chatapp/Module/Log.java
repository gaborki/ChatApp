package com.greenfox.chatapp.Module;


import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String path;
  private String method;
  private Timestamp timestamp;
  private String logLevel;
  private String requestData;

  public Log(){
    this.method = "method";
    this.path = "path";
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.logLevel = "INFO";
    this.requestData = "nothing";
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(String logLevel) {
    this.logLevel = logLevel;
  }

  public String getRequestData() {
    return requestData;
  }

  public void setRequestData(String requestData) {
    this.requestData = requestData;
  }
}
