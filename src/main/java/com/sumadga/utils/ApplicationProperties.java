package com.sumadga.utils;

public class ApplicationProperties {
	
	private String username;
	private String password;
	private String msisdnReturnURL;
	private String eventChargeReturnURL;
	private String qubeCellURL;
	private String secretKey;
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getQubeCellURL() {
		return qubeCellURL;
	}
	public void setQubeCellURL(String qubeCellURL) {
		this.qubeCellURL = qubeCellURL;
	}
	public String getEventChargeReturnURL() {
		return eventChargeReturnURL;
	}
	public void setEventChargeReturnURL(String eventChargeReturnURL) {
		this.eventChargeReturnURL = eventChargeReturnURL;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsisdnReturnURL() {
		return msisdnReturnURL;
	}
	public void setMsisdnReturnURL(String msisdnReturnURL) {
		this.msisdnReturnURL = msisdnReturnURL;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
