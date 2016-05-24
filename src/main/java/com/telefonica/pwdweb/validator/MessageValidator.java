package com.telefonica.pwdweb.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import botdetect.web.Captcha;

import com.telefonica.pwdweb.model.Message;

public class MessageValidator implements Validator {

  public boolean supports(Class type) {
    return Message.class.isAssignableFrom(type);
  }

  public void validate(Object o, Errors errors) {

    Message message = (Message)o;
    
    if(!isValidUserId(message.getUserId())){
      errors.rejectValue("userId", "length", "UserID format should be of email type");
    }  
    
    if(!isCaptchaValid(message.getRequest(),message.getCaptchaCodeTextBox())){
      errors.rejectValue("captchaCodeTextBox", "captcha", "*");
    }
  }

  private boolean isValidUserId(String userId){
	  if(userId == null){
	      return false;
	    }
	    return userId.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
  }  

  private boolean isCaptchaValid(HttpServletRequest request, 
      String userCaptchaCode){
    HttpSession session = request.getSession();
    if(session != null && session.getAttribute("captchaVerified") != null){
      return true;
    }
    // validate the Captcha to check we're not dealing with a bot
    Captcha captcha = Captcha.load(request, "springFormCaptcha");
    boolean isHuman = captcha.validate(request, userCaptchaCode);
    if (isHuman) {
      if(session == null){
        session = request.getSession(true);
      }
      session.setAttribute("captchaVerified", true);
      return true;
    } else {
      return false;
    }
  }

}