package com.example.smmousavi.maktab_hw82_remindemelater.mvc.model;

import java.util.UUID;

public class User {

  private UUID mId;
  private String mName;
  private String mPassword;


  public User() {
    mId = UUID.randomUUID();
  }


  public UUID getId() {
    return mId;
  }


  public void setId(UUID mId) {
    this.mId = mId;
  }


  public String getName() {
    return mName;
  }


  public void setName(String mName) {
    this.mName = mName;
  }


  public String getPassword() {
    return mPassword;
  }


  public void setPassword(String mPassword) {
    this.mPassword = mPassword;
  }


}