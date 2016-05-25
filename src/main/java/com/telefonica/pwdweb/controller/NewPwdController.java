package com.telefonica.pwdweb.controller;

import java.util.Date;

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

import com.telefonica.pwdweb.model.Message;
import com.telefonica.pwdweb.service.PasswordManagementService;
import com.telefonica.pwdweb.validator.MessageValidator;
import com.telefonica.pwdweb.wsdlimported.GenerateNewPwdResponse;
import com.telefonica.pwdweb.wsdlimported.OperationResponse;

@Controller
@RequestMapping(value="newpwd.htm")
public class NewPwdController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewPwdController.class);

	@Autowired
	private MessageValidator validator;
	
	@Autowired
	private PasswordManagementService pwdManageService;

	@RequestMapping(method = RequestMethod.GET)
	public String  initForm(ModelMap model, HttpServletRequest request) {
		Message message = new Message();
		message.setRequest(request);
		model.addAttribute("newpwd", message);		
		return "newpwd";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("newpwd") Message message,
			BindingResult result, HttpServletRequest request) {
		
		logger.info("Validatig the userID with captcha");
		message.setRequest(request);
		validator.validate(message, result);

		if (result.hasErrors()) {
			return new ModelAndView("newpwd");
		} else {
			HttpSession session = request.getSession();
			if (session != null) {				
				GenerateNewPwdResponse response = pwdManageService.generateNewPassword(message.getUserId());
				session.removeAttribute("captchaVerified");
				ModelAndView model =null;
				if(response.getOperationResponse().getCode()==0){
					model = new ModelAndView("newpwdsuccess");
					model.addObject("operationResponse",response.getOperationResponse());
				}else{
					model = new ModelAndView("newpwd");
					model.addObject("operationResponse", response.getOperationResponse());
				}
				return model;
			} else {
				return new ModelAndView("newpwd");
			}
		}
	}	
}