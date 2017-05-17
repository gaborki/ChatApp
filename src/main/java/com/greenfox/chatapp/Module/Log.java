package com.greenfox.chatapp.Module;

import java.sql.Timestamp;
import javax.persistence.Entity;

@Entity
public class Log {

  private String path;
  private String method;
  private Timestamp timestamp;
  private String logLevel;
  private String requestData;

  public Log() {

  }


}
