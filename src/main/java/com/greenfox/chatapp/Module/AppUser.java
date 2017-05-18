package com.greenfox.chatapp.Module;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "AppUser")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String username;

  public AppUser(){
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString(){
    return this.username;
  }
}
