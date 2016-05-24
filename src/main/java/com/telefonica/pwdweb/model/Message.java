package com.telefonica.pwdweb.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Message {

	private String userId;
	private String userCaptchaCode;
	private HttpServletRequest request;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCaptchaCode() {
		return userCaptchaCode;
	}

	public void setUserCaptchaCode(String userCaptchaCode) {
		this.userCaptchaCode = userCaptchaCode;
	}

	public String getCaptchaCodeTextBox() {
		return userCaptchaCode;
	}

	public void setCaptchaCodeTextBox(String userCaptchaCode) {
		this.userCaptchaCode = userCaptchaCode;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isCaptchaVerified() {
		if (this.request == null) {
			return false;
		}
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		}
		return session.getAttribute("captchaVerified") != null;
	}

}