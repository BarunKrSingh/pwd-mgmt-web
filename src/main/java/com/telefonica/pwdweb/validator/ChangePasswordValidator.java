package com.telefonica.pwdweb.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.telefonica.pwdweb.constants.CommonConstants;
import com.telefonica.pwdweb.controller.GenerateNewPasswordController;
import com.telefonica.pwdweb.model.ChangePassword;

@Component
public class ChangePasswordValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateNewPasswordController.class);	
	
	public boolean supports(Class<?> paramClass) {
		return ChangePassword.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "empty.userId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "empty.currentPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "empty.newPassword");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPasswordConf", "empty.newPasswordConf");				
		
		ChangePassword chgpwd = (ChangePassword) obj;		
		
		Pattern newPasswordPattern = Pattern.compile(CommonConstants.PASSWORD_PATTERN);
		Matcher newPasswordMatcher= newPasswordPattern.matcher(chgpwd.getNewPassword());
		
		Pattern userIdPattern = Pattern.compile(CommonConstants.USER_ID_PATTERN);
		
		if(!chgpwd.getUserId().isEmpty() && null !=chgpwd.getUserId())
		{			
			Matcher userIdMatcher = userIdPattern.matcher(chgpwd.getUserId());
			if(userIdMatcher.matches())
			{	
				if(!chgpwd.getNewPassword().isEmpty())
				{
					if(newPasswordMatcher.matches() && chgpwd.getNewPassword().trim().length() >= CommonConstants.MINIMUM_PASSWORD_LENGTH)
					{
						if (!chgpwd.getNewPasswordConf().isEmpty() && !chgpwd.getNewPassword().equals(chgpwd.getNewPasswordConf())) 
						{
							errors.rejectValue("newPasswordConf", "invalid.passwordConfDiff");
						}			
					}
					else
					{
						errors.rejectValue("newPassword", "invalid.newPassword"); 
					}
				}
							
			}
			else 
			{
				errors.rejectValue("userId", "invalid.userId");
			} 
		}
		
	}
}
