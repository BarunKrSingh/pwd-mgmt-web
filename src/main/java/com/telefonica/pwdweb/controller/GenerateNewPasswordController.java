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
import com.telefonica.pwdweb.wsdlimported.GenerateNewPwdResponse;

@Controller
@RequestMapping(value="newpwd")
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
	public ModelAndView processSubmit(@ModelAttribute("newpwd") NewPassword newpassword, BindingResult result, HttpServletRequest request) {
		
		logger.info("Validatig the userID with captcha");
		newpassword.setRequest(request);
		validator.validate(newpassword, result);
		if (result.hasErrors()) {
			return new ModelAndView(ControllerActions.GENERATE_NEW_PASSWORD);
		} else
		{			
			HttpSession session = request.getSession();
			ModelAndView modelAndView = new ModelAndView();
			if (session != null) {				
				GenerateNewPwdResponse response = pwdManageService.generateNewPassword(newpassword.getUserId());
				session.removeAttribute("captchaVerified");
				
				if(response.getOperationResponse().getCode()==0){
					modelAndView.setViewName("newpwdsuccess");
					modelAndView.addObject("operationResponse",response.getOperationResponse());
				}else{
					modelAndView.setViewName(ControllerActions.GENERATE_NEW_PASSWORD);
					modelAndView.addObject("operationResponse", response.getOperationResponse());
				}
				return modelAndView;
			} else {	
				modelAndView.setViewName(ControllerActions.GENERATE_NEW_PASSWORD);
				return modelAndView;
			}
		}
	}	
}