package com.sumadga.wap.billing;

import in.verse.ipayy.crypto.CryptoException;
import in.verse.ipayy.crypto.CryptoUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumadga.dao.RequestDao;
import com.sumadga.dto.Request;
import com.sumadga.utils.ApplicationProperties;

@Component
public class BillingUtils {
	
	Logger logger=Logger.getLogger(BillingUtils.class);
	private static Map< String, String> errorMessages = new HashMap<String, String>();
	@Autowired
	RequestDao requestDao;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	
	public String getMsisdnDetectionURL(HttpServletRequest httpServletRequest){
		
		Request request=new Request();
		Long msisdn=null;
		if(httpServletRequest.getSession().getAttribute("msisdn")!=null){ 
			String mdn=(String)httpServletRequest.getSession().getAttribute("msisdn");
			if(!mdn.trim().isEmpty())
			msisdn=Long.parseLong(mdn);
		}
		request.setMsisdn(msisdn);
		
		StringBuffer redirectUrl=httpServletRequest.getRequestURL();
		redirectUrl.append("?t1=t");
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration= httpServletRequest.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String param=enumeration.nextElement();
			if(httpServletRequest.getParameter(param)!=null && !httpServletRequest.getParameter(param).trim().isEmpty())
			redirectUrl.append("&"+param +"="+httpServletRequest.getParameter(param));
		}
		request.setRequestedURL(redirectUrl.toString());
		request.setRedirectURL(redirectUrl.toString());
		
		requestDao.save(request);
		
		StringBuffer url=new StringBuffer();
		
		url.append(applicationProperties.getMsisdnReturnURL());
		url.append("?username="+applicationProperties.getUsername());
		url.append("&password="+applicationProperties.getPassword());
		url.append("&requestid="+request.getRequestId());
		url.append("&key="+getMD5(applicationProperties.getSecretKey()+request.getRequestId()));
		url.append("&returnurl=http://faltutv.co.in/Wap/service/detectMsisdn");
		//url.append("&returnurl=http://localhost:8080/Wap/service/detectMsisdn");
		
		return url.toString();
	}
	
public BillingModel getEventBilling(HttpServletRequest httpServletRequest,Long msisdn,String operator,String productid){
		
		Request request=new Request();
		
		request.setMsisdn(msisdn);
		
		StringBuffer redirectUrl=httpServletRequest.getRequestURL();
		redirectUrl.append("?t1=t");
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration= httpServletRequest.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String param=enumeration.nextElement();
			if(httpServletRequest.getParameter(param)!=null && !httpServletRequest.getParameter(param).trim().isEmpty())
			redirectUrl.append("&"+param +"="+httpServletRequest.getParameter(param));
		}
		
		request.setRedirectURL(redirectUrl.toString());
		request.setRequestedURL(redirectUrl.toString()); 
		
		
		requestDao.save(request);

		BillingModel billingModel=new BillingModel();
		billingModel.setKey(getMD5(applicationProperties.getSecretKeyOtherAPI()+request.getRequestId()));
		billingModel.setMsisdn(msisdn+"");
		billingModel.setOperation("EventCharge");
		billingModel.setOperator(operator);
		billingModel.setPassword(applicationProperties.getPasswordOtherAPI());
		billingModel.setProductid(productid);
		billingModel.setRedirecturl("http://faltutv.co.in/Wap/service/billing?operator="+httpServletRequest.getSession().getAttribute("operator"));
		billingModel.setRequestid(request.getRequestId()+"");
		billingModel.setUsername(applicationProperties.getUsernameOtherAPI());
		billingModel.setUrl(applicationProperties.getUrlOtherAPI());
		
		logger.info("Billing Model "+billingModel);
		return billingModel;
	}
	
public String getBillingErrorMessage(int errorCode){
	String status = "";
	if(errorCode == 101)
		status = "success";
	else if(errorCode == 102)
		status = "Authentication failure";
	else if(errorCode == 103)
		status = "Operator not supported";
	else if(errorCode == 104)
		status = "Duplicate request id";
	else if(errorCode == 105)
		status = "Charging failed, insufficient balance";
	else if(errorCode == 106)
		status = "Undetermined error";
	else if(errorCode == 107)
		status = "Invalid key";
	else if(errorCode == 108)
		status = "Failure";
	else if(errorCode == 109)
		status = "Invalid MSISDN";
	else if(errorCode == 110)
		status = "Invalid product ID";
	else if(errorCode == 111)
		status = "Operator not supported";
	else 
		status = "Unknown Error While billing";
	return status;
}

public String getPaymentURLIpayy(HttpServletRequest httpServletRequest, Long msisdn){
	
	String merchantKey = applicationProperties.getMerchantKey(); // your merchant_key
	String applicationKey = applicationProperties.getFaltutv_applicationKey(); //your application_key
	String  encryptedString = null;
	try
	{
	//String requestId = "1"; // unique identifier for each request
	Request request=new Request();
	
	request.setMsisdn(msisdn);
	
	StringBuffer redirectUrl=httpServletRequest.getRequestURL();
	redirectUrl.append("?t1=t");
	@SuppressWarnings("unchecked")
	Enumeration<String> enumeration= httpServletRequest.getParameterNames();
	while(enumeration.hasMoreElements()) {
		String param=enumeration.nextElement();
		if(httpServletRequest.getParameter(param)!=null && !httpServletRequest.getParameter(param).trim().isEmpty())
		redirectUrl.append("&"+param +"="+httpServletRequest.getParameter(param));
	}
	
	request.setRedirectURL(redirectUrl.toString());
	request.setRequestedURL(redirectUrl.toString()); 
	
	
	requestDao.save(request); 
	//create payment request item 
	String itemCode="item1";
	String itemName="item1";
	String itemPrice="2";
	String currencyCode="INR";
	
	
	      Map<String, String> parameterMap = new HashMap<String, String>();
	      parameterMap.put(CryptoUtils.MERCHANT_KEY_PARAM, merchantKey);
	      parameterMap.put(CryptoUtils.APPLICATION_KEY_PARAM, applicationKey);
	      parameterMap.put(CryptoUtils.ITEM_CODE_PARAM, itemCode);
	      parameterMap.put(CryptoUtils.ITEM_NAME_PARAM, itemName);
	      parameterMap.put(CryptoUtils.ITEM_PRICE_PARAM,itemPrice);
	      parameterMap.put(CryptoUtils.CURRENCY_PARAM, currencyCode);
	      parameterMap.put(CryptoUtils.REQUEST_TOKEN_PARAM, request.getRequestId()+"");
	 
	     //Using encryption library to create encryption string
	      encryptedString = CryptoUtils.getEncryptedString(parameterMap);
	}
	/*catch (CryptoException e)
	{
	    // Handle Encryption error
	}*/catch (Exception e) {
		// TODO: handle exception
	}
	
	String rediURL = applicationProperties.getIpayyBillingURL()+encryptedString;
	logger.info("ipay billing url:"+rediURL);
	return rediURL;
}
	
	private String getMD5(String string){
	    try {
	    	   MessageDigest m=MessageDigest.getInstance("MD5");
			   m.update(string.getBytes(),0,string.length());
			   String md5 = new BigInteger(1,m.digest()).toString(16);
			   logger.info("MD5: "+md5 );
			   return md5;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

	}

	public static void setIpayErrorCodes(){
		errorMessages.put("CFG0201", "The application is currently not live for the operator");
		errorMessages.put("IRQ0101", "An incorrect value was specified for a mandatory parameter Application");
		errorMessages.put("IRQ0104", "An incorrect value was specified for a mandatory parameter Circle");
		errorMessages.put("IRQ0105", "An incorrect value was specified for a mandatory parameter Customer");
		errorMessages.put("IRQ0106", "An incorrect value was specified for a mandatory parameter Customer Profile");
		errorMessages.put("IRQ0107", "An incorrect value was specified for a mandatory parameter Merchant");
		errorMessages.put("IRQ0108", "An incorrect value was specified for a mandatory parameter Merchant Category");
		errorMessages.put("IRQ0109", "An incorrect value was specified for a mandatory parameter Operator");
		errorMessages.put("IRQ0116", "An incorrect value was specified for a mandatory parameter Transaction");
		errorMessages.put("IRQ0119", "An incorrect value was specified for a mandatory parameter Operator IP Range");
		errorMessages.put("IRQ0122", "An incorrect value was specified for a mandatory parameter Country");
		errorMessages.put("IRQ0123", "An incorrect value was specified for a mandatory parameter Currency");
		errorMessages.put("IRQ0124", "An incorrect value was specified for a mandatory parameter Merchant Operator Properties");
		errorMessages.put("IRQ0125", "An incorrect value was specified for a mandatory parameter Aggregator");
		errorMessages.put("IRQ0126", "An incorrect value was specified for a mandatory parameter Content Partner");
		errorMessages.put("IRQ0201", "A required parameter was not passed / provided");
		errorMessages.put("IRQ0301", "The request format was incorrect or the values specified was unacceptable");
		errorMessages.put("IRQ0401", "A resource to be created already exists with the specified values");
		errorMessages.put("IRQ0601", "The value or format for a parameter was incorrect");
		errorMessages.put("IRQ0606", "The payment parameter values provided are incorrect");
		errorMessages.put("ICR0101", "Credentials were incorrect");
		errorMessages.put("ICR0201", "The OTP pin did not match");
		errorMessages.put("ICR0301", "The OTP pin was not specified");
		errorMessages.put("ICR0601", "Customer is not authorized for billing (in UAT / TESTING phase)");
		errorMessages.put("ISC0101", "MSISDN could not be discovered");
		errorMessages.put("ISC0201", "Operator could not be discovered");
		errorMessages.put("ISC0301", "Customer does not exist");
		errorMessages.put("ISC0401", "Malformed MSISDN provided by the customer");
		errorMessages.put("SER0101", "Error occurred while sending sms");
		errorMessages.put("SER0201", "Error occurred during billing");
		errorMessages.put("SER0301", "Waiver could not be created");
		errorMessages.put("SER0401", "Error occurred while storing / fetching payment request data");
		errorMessages.put("SER0501", "Error occurred during payment");
		errorMessages.put("SER0701", "Generic Data Store Access Error");
		errorMessages.put("SER0901", "Waiver could not be created");
		errorMessages.put("SER1001", "Creation of a ticket for transaction not existing for merchant");
		errorMessages.put("SER1101", "Ticket creation (Waiver) for a non-success transaction");
		errorMessages.put("SER1301", "Internal Configuration Error");
		errorMessages.put("SER1401", "Error occurred during ussd push");
		errorMessages.put("UXE0001", "Unexpected Error");
		
		
	}
	public String getipayErrorMessage(String responseCode) {
		// TODO Auto-generated method stub
		/*String ec = paramaterMap.get("ec");
		String em = paramaterMap.get("em");*/
		String errorReason = errorMessages.get(responseCode);
		return errorReason;
	}
}
