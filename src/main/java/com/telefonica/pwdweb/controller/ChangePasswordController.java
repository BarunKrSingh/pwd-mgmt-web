package com.telefonica.pwdweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telefonica.pwdweb.constants.ControllerActions;
import com.telefonica.pwdweb.model.ChangePassword;
import com.telefonica.pwdweb.service.PasswordManagementService;
import com.telefonica.pwdweb.validator.ChangePasswordValidator;
import com.telefonica.pwdweb.wsdlimported.OperationResponse;

@Controller
@RequestMapping(value=ControllerActions.CHANGE_PASSWORD)
public class ChangePasswordController {	
	
	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);
	
	@Autowired
	private ChangePasswordValidator chgPwdValidator;
	
	@Autowired
	private PasswordManagementService pwdManageService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model){
		ChangePassword chgpwd = new ChangePassword();
		model.addAttribute("chgpwd", chgpwd);
		return ControllerActions.CHANGE_PASSWORD;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitForm(@ModelAttribute("chgpwd") ChangePassword chgpwd, BindingResult result) {	
		
		logger.info("Processing the change password request for the user:{}",chgpwd.getUserId());
		
		ModelAndView modelAndView = new ModelAndView();	
		chgPwdValidator.validate(chgpwd, result);
		
		if(result.hasErrors())
		{
			modelAndView.setViewName(ControllerActions.CHANGE_PASSWORD);
		} 
		else 
		{					
			OperationResponse operationResponse = pwdManageService.changeExistingPassword(chgpwd.getUserId(), chgpwd.getCurrentPassword(), chgpwd.getNewPassword());
			modelAndView.addObject("operationResponse", operationResponse);
			
			if((1 == operationResponse.getCode() ) || (2 == operationResponse.getCode()) || (3 == operationResponse.getCode()) || (4 == operationResponse.getCode()))
			{
				modelAndView.setViewName(ControllerActions.CHANGE_PASSWORD);
				logger.debug("Successfully changed the password for the user:{}",chgpwd.getUserId());
			}
			else if (5 == operationResponse.getCode()) {
				modelAndView.setViewName(ControllerActions.USER_BLOCKED);
				logger.debug("The user:{} is blocked",chgpwd.getUserId());
			}
			else{
				modelAndView.setViewName(ControllerActions.CHANGE_PASSWORD_SUCCESS);
				logger.debug("Successfully changed the password for the user:{}",chgpwd.getUserId());
			}						
		}		
		return modelAndView	;
	}

}