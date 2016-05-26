package com.telefonica.pwdweb.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.telefonica.pwdweb.controller.GenerateNewPasswordController;
import com.telefonica.pwdweb.model.ChangePassword;

@Component
public class ChangePasswordValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateNewPasswordController.class);

	private static final int MINIMUM_PASSWORD_LENGTH = 8;
	
	public boolean supports(Class<?> paramClass) {
		return ChangePassword.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "valid.emptyEmailId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "valid.newPassword");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPasswordConf", "valid.newPasswordConf");*/
		
		String emailidregex= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		ChangePassword chgpwd = (ChangePassword) obj;
		String regex = "^[a-zA-Z0-9]+$";
		
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher= pattern.matcher(chgpwd.getNewPassword());
		
		Pattern emailpattern=Pattern.compile(emailidregex);
		Matcher emailmatcher= emailpattern.matcher(chgpwd.getUserId());
		if(emailmatcher.matches())
		{			
			if(chgpwd.getCurrentPassword().length()==0)
			{
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "valid.currentPassword");
			}
			else 
			{
				
			
				if(matcher.matches())
				{
					if (chgpwd.getNewPassword() != null && chgpwd.getNewPassword().trim().length() == MINIMUM_PASSWORD_LENGTH) 
					{
						 if (!chgpwd.getNewPassword().equals(chgpwd.getNewPasswordConf())) 
						{
							 errors.rejectValue("newPasswordConf", "valid.passwordConfDiff");
						}
				    }
					else
					{
						errors.rejectValue("newPassword", "valid.passwordLength");
					}			
				}
				else
				{
					errors.rejectValue("newPassword", "valid.passwordPattern"); 
	
				}	
			}
		}
		else 
		{
			errors.rejectValue("userId", "valid.emailId");
		} 
	}
}
