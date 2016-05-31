package com.telefonica.pwdweb.model;

public class ChangePassword {

	private String userId;
	private String currentPassword;
	private String newPassword;
	private String newPasswordConf;
	private String authenticationfails;
	private String date;
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConf() {
		return newPasswordConf;
	}

	public void setNewPasswordConf(String newPasswordConf) {
		this.newPasswordConf = newPasswordConf;
	}

	public String getAuthenticationfails() {
		return authenticationfails;
	}

	public void setAuthenticationfails(String authenticationfails) {
		this.authenticationfails = authenticationfails;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
