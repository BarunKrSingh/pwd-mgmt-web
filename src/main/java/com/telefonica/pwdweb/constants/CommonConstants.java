package com.telefonica.pwdweb.constants;

public interface CommonConstants {

	String USER_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	String PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";

	int MINIMUM_PASSWORD_LENGTH = 8;

}
