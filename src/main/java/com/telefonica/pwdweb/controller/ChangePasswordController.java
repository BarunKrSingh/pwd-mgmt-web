package com.telefonica.pwdweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.telefonica.pwdweb.model.ChangePassword;
import com.telefonica.pwdweb.service.PasswordManagementService;
import com.telefonica.pwdweb.validator.ChangePasswordValidator;
import com.telefonica.pwdweb.validator.NewPasswordValidator;
import com.telefonica.pwdweb.wsdlimported.ChangePwdResponse;
import com.telefonica.pwdweb.wsdlimported.OperationResponse;

@Controller
@RequestMapping("/chgpwd.htm")
public class ChangePasswordController {	
	
	@Autowired
	private ChangePasswordValidator chgPwdValidator;
	
	@Autowired
	private PasswordManagementService pwdManageService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model){
		ChangePassword chgpwd = new ChangePassword();
		model.addAttribute("chgpwd", chgpwd);
		return "chgpwd";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitForm(@ModelAttribute("chgpwd") ChangePassword chgpwd, BindingResult result) {		
		
		ModelAndView modelAndView = new ModelAndView();	
		chgPwdValidator.validate(chgpwd, result);
		
		if(result.hasErrors())
		{
			modelAndView.setViewName("chgpwd");
		} 
		else 
		{					
			OperationResponse operationResponse = pwdManageService.changeExistingPassword(chgpwd.getUserId(), chgpwd.getCurrentPassword(), chgpwd.getNewPassword());
			modelAndView.addObject("operationResponse", operationResponse);
			
			if(operationResponse.getCode() == 1 || operationResponse.getCode() == 2 || operationResponse.getCode() == 3 || operationResponse.getCode() == 4)
			{
				modelAndView.setViewName("chgpwd");				
			}
			else if (operationResponse.getCode() == 5) {
				modelAndView.setViewName("userblocked");				
			}
			else{
				modelAndView.setViewName("chgpwdsuccess");
			}						
		}		
		return modelAndView	;
	}

}