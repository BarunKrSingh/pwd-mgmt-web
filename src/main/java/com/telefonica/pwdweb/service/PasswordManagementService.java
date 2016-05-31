package com.telefonica.pwdweb.service;

import com.telefonica.pwdweb.wsdlimported.OperationResponse;

public interface PasswordManagementService {
	
	public OperationResponse generateNewPassword(String userId);
	
	public OperationResponse changeExistingPassword(String userId, String currentPassword, String newPassword);

}
