package com.telefonica.pwdweb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.telefonica.pwdweb.service.PasswordManagementService;
import com.telefonica.pwdweb.wsdlimported.GenerateNewPwdRequest;
import com.telefonica.pwdweb.wsdlimported.GenerateNewPwdResponse;
import com.telefonica.pwdweb.wsdlimported.OperationResponse;

@Service
public class PasswordManagementServiceImpl implements PasswordManagementService{
	
	private static final Logger logger = LoggerFactory.getLogger(PasswordManagementServiceImpl.class);
	
	@Autowired
	private WebServiceTemplate passwordWebServiceTemplate;

	@Override
	public GenerateNewPwdResponse generateNewPassword(String userId) {
		
		logger.info("Generating new password for the user:{}",userId);
		
		GenerateNewPwdResponse response = null;
		GenerateNewPwdRequest newPwdRequest = new GenerateNewPwdRequest();
		
		try {						
			newPwdRequest.setUserId(userId);
			response = (GenerateNewPwdResponse) passwordWebServiceTemplate.marshalSendAndReceive(newPwdRequest);			
		} catch (Exception e) {
			logger.error("Exception occured invoking the the SOAP based service for new password genearion for the user:{} due to: {}",userId,e);
		}
		return response;
	}

	@Override
	public OperationResponse changeExistingPassword() {
		return null;
	}

}
