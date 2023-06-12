package org.edupoll.model.dto.request;

public class LoginRequestData {
	String loginId;
	String loginPass;
	
	
	public LoginRequestData() {
		super();
	}


	public LoginRequestData(String loginId, String loginPass) {
		super();
		this.loginId = loginId;
		this.loginPass = loginPass;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getLoginPass() {
		return loginPass;
	}


	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	

}
