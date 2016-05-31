package com.telefonica.pwdweb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.telefonica.pwdweb.service.PasswordManagementService;
import com.telefonica.pwdweb.wsdlimported.ChangePwdRequest;
import com.telefonica.pwdweb.wsdlimported.ChangePwdResponse;
import com.telefonica.pwdweb.wsdlimported.GenerateNewPwdRequest;
import com.telefonica.pwdweb.wsdlimported.GenerateNewPwdResponse;
import com.telefonica.pwdweb.wsdlimported.OperationResponse;

@Service
public class PasswordManagementServiceImpl implements PasswordManagementService{
	
	private static final Logger logger = LoggerFactory.getLogger(PasswordManagementServiceImpl.class);
	
	@Autowired
	private WebServiceTemplate passwordWebServiceTemplate;

	@Override
	public OperationResponse generateNewPassword(String userId) {
		
		logger.info("Generating new password for the user:{}",userId);
		
		GenerateNewPwdResponse generateNewPwdResponse = null;
		GenerateNewPwdRequest newPwdRequest = new GenerateNewPwdRequest();
		
		try {						
			newPwdRequest.setUserId(userId);
			generateNewPwdResponse = (GenerateNewPwdResponse) passwordWebServiceTemplate.marshalSendAndReceive(newPwdRequest);			
		} catch (Exception e) {
			logger.error("Exception occured processing the SOAP request for new password genearion for the user:{} due to: {}",userId,e);
		}
		return generateNewPwdResponse.getOperationResponse();
	}

	@Override
	public OperationResponse changeExistingPassword(String userId, String currentPassword, String newPassword) {
		
		logger.info("Changing the existing password for the user:{}",userId);
		
		if ((null == userId) || (null == currentPassword) || (null == newPassword)) {
			throw new IllegalArgumentException("userID, currentPassword and newPassword parameters cannot be null");
		}
		
		ChangePwdResponse changePwdResponse = null;
		ChangePwdRequest changePwdRequest = new ChangePwdRequest();
		
		try {			
			changePwdRequest.setUserId(userId);
			changePwdRequest.setCurrentPassword(currentPassword);
			changePwdRequest.setNewPassword(newPassword);
			
			changePwdResponse = (ChangePwdResponse) passwordWebServiceTemplate.marshalSendAndReceive(changePwdRequest);			
			
		} catch (Exception e) {
			logger.error("Exception occured processing the SOAP request for new password genearion for the user:{} due to: {}",userId,e);
		}		
		return changePwdResponse.getOperationResponse();
		
	}

}
