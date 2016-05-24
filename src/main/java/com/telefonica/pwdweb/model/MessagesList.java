package com.telefonica.pwdweb.model;

import java.util.ArrayList;
import java.util.List;

public class MessagesList {
  private List<String> messages = new ArrayList<String>();

  public void setMessagesList(List messages){
    this.messages = messages;
  }

  public List<String> getMessagesList(){
    return messages;
  }

}
