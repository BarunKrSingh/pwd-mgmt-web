package com.telefonica.pwdweb.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.telefonica.pwdweb.model.Message;

public class IndexController extends SimpleFormController {

  public IndexController(){
    setCommandClass(Message.class);
    setCommandName("index");
  }

  @Override
  protected Object formBackingObject(HttpServletRequest request){
    Message message = new Message();
    message.setRequest(request);
    return message;
  }

  @Override
  public ModelAndView onSubmit(HttpServletRequest request, 
      HttpServletResponse response, Object command, BindException errors){
    HttpSession session = request.getSession();
    if(session != null){
      Message message = (Message)command;
      saveMessage(session, message.getUserId());           
      session.removeAttribute("captchaVerified");
      return new ModelAndView(getSuccessView());
    } else {
      return new ModelAndView(getFormView());
    }
  }

  private void saveMessage(HttpSession session, String userId){
    Date timeStamp = new Date();
    String message = userId + " says: " + "Test...";
    session.setAttribute("Message_"+timeStamp.getTime(), message);
  }
}