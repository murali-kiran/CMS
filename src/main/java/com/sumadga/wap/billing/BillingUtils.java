package com.sumadga.wap.billing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

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
		url.append("&returnurl=http://49.50.68.139:8080/Wap/service/detectMsisdn");
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
		billingModel.setRedirecturl("http://49.50.68.139:8080/Wap/service/billing");
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
}
