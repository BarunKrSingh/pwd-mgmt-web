package com.telefonica.pwdweb.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import botdetect.web.Captcha;

import com.telefonica.pwdweb.constants.CommonConstants;
import com.telefonica.pwdweb.controller.GenerateNewPasswordController;
import com.telefonica.pwdweb.model.NewPassword;

@Component
public class NewPasswordValidator implements Validator {
	
  private static final Logger logger = LoggerFactory.getLogger(GenerateNewPasswordController.class);

  public boolean supports(Class type) {
    return NewPassword.class.isAssignableFrom(type);
  }

  public void validate(Object o, Errors errors) {

    NewPassword message = (NewPassword)o;
    
    logger.info("Performing the field level validation for new password generation operation for the user:{}",message.getUserId());
    
    if(null !=message.getUserId() && !message.getUserId().isEmpty() && !isValidUserId(message.getUserId())){
    	errors.rejectValue("userId", "invalid.userId");
    }
    else
    {
    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "empty.userId");
    }
    
    if(!isCaptchaValid(message.getRequest(), message.getCaptchaCodeTextBox())){      
    	errors.rejectValue("userCaptchaCode", "empty.captchaValue");
    }
  }

  private boolean isValidUserId(String userId){
	  if(userId == null){
	      return false;
	    }
	    return userId.matches(CommonConstants.USER_ID_PATTERN);
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