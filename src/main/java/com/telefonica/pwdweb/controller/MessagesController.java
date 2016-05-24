package com.telefonica.pwdweb.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.telefonica.pwdweb.model.MessagesList;

public class MessagesController extends SimpleFormController{

  public MessagesController(){
    setCommandClass(MessagesList.class);
    setCommandName("messages");
  }

  @Override
  protected Object formBackingObject(HttpServletRequest request){
    List<String> messages = new ArrayList<String>();
    HttpSession session = request.getSession();
    if(session != null){
      Enumeration attributes = session.getAttributeNames();
      while(attributes.hasMoreElements()){
        String attribute = (String)attributes.nextElement();
        if(attribute.startsWith("Message_")){
          messages.add((String)session.getAttribute(attribute));
        }
      }
    }
    MessagesList messagesList = new MessagesList();
    messagesList.setMessagesList(messages);
    return messagesList;
  }

  @Override
  protected ModelAndView onSubmit(HttpServletRequest request, 
      HttpServletResponse response, Object command, BindException errors){
    if(request.getParameter("clear") != null){
      HttpSession session = request.getSession();
      if(session != null){
        session.invalidate();
      }
      return new ModelAndView("messages");
    } else {
      return new ModelAndView("redirect:index.htm");
    }
  }
}