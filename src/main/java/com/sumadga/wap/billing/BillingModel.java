package com.sumadga.wap.billing;

public class BillingModel {
	private String username;
	private String password;
	private String requestid;
	private String operation;
	private String productid;
	private String msisdn;
	private String key;
	private String redirecturl;
	private String message;
	private String logopath;
	private String operator;
	
	private String serviceKeypriceKey;
	private String secretKeyOtherAPI;
	private String url;
	
	

	public String getSecretKeyOtherAPI() {
		return secretKeyOtherAPI;
	}
	public void setSecretKeyOtherAPI(String secretKeyOtherAPI) {
		this.secretKeyOtherAPI = secretKeyOtherAPI;
	}
	public String getServiceKeypriceKey() {
		return serviceKeypriceKey;
	}
	public void setServiceKeypriceKey(String serviceKeypriceKey) {
		this.serviceKeypriceKey = serviceKeypriceKey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String usename) {
		this.username = usename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getRedirecturl() {
		return redirecturl;
	}
	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLogopath() {
		return logopath;
	}
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "BillingModel [username=" + username + ", password=" + password
				+ ", requestid=" + requestid + ", operation=" + operation
				+ ", productid=" + productid + ", msisdn=" + msisdn + ", key="
				+ key + ", redirecturl=" + redirecturl + ", message=" + message
				+ ", logopath=" + logopath + ", operator=" + operator
				+ ", serviceKeypriceKey=" + serviceKeypriceKey
				+ ", secretKeyOtherAPI=" + secretKeyOtherAPI + ", url=" + url
				+ "]";
	}
	
}
