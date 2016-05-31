package com.telefonica.pwdweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telefonica.pwdweb.constants.ControllerActions;
import com.telefonica.pwdweb.model.NewPassword;
import com.telefonica.pwdweb.service.PasswordManagementService;
import com.telefonica.pwdweb.validator.NewPasswordValidator;
import com.telefonica.pwdweb.wsdlimported.OperationResponse;

@Controller
@RequestMapping(value=ControllerActions.GENERATE_NEW_PASSWORD)
public class GenerateNewPasswordController {
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateNewPasswordController.class);

	@Autowired
	private NewPasswordValidator validator;
	
	@Autowired
	private PasswordManagementService pwdManageService;

	@RequestMapping(method = RequestMethod.GET)
	public String  initForm(ModelMap model, HttpServletRequest request) {
		NewPassword newpassword = new NewPassword();
		newpassword.setRequest(request);
		model.addAttribute("newpwd", newpassword);		
		return ControllerActions.GENERATE_NEW_PASSWORD;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("newpwd") NewPassword newPassword, BindingResult result, HttpServletRequest request) {
		
		logger.info("Processing the new password generation request for the user:{}",newPassword.getUserId());
		newPassword.setRequest(request);
		validator.validate(newPassword, result);
		if (result.hasErrors()) {
			return new ModelAndView(ControllerActions.GENERATE_NEW_PASSWORD);
		} else
		{			
			HttpSession session = request.getSession();
			ModelAndView modelAndView = new ModelAndView();
			if (session != null) {				
				OperationResponse operationResponse = pwdManageService.generateNewPassword(newPassword.getUserId());
				session.removeAttribute("captchaVerified");
				
				if(0 == operationResponse.getCode()){
					modelAndView.setViewName(ControllerActions.GENERATE_NEW_PASSWORD_SUCCESS);
					modelAndView.addObject("operationResponse",operationResponse);
					logger.debug("Successfully generated the new password for the user:{}",newPassword.getUserId());
				}else{
					modelAndView.setViewName(ControllerActions.GENERATE_NEW_PASSWORD);
					modelAndView.addObject("operationResponse", operationResponse);
					logger.debug("New password could not be generated successfully for the user: {}",newPassword.getUserId());
				}
				return modelAndView;
			} else {	
				modelAndView.setViewName(ControllerActions.GENERATE_NEW_PASSWORD);
				return modelAndView;
			}
		}
	}	
}