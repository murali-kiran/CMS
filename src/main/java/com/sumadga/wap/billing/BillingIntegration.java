package com.sumadga.wap.billing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sumadga.utils.ApplicationProperties;



public class BillingIntegration {
	@Autowired
	ApplicationProperties applicationProperties;
	private static final Logger logger = Logger.getLogger(BillingIntegration.class);
	private String status;
	
	//returns string msisdn+"$"+operator(MSISDN and operator name) if success
	public String getMsisdn() throws Exception{
	HttpClient client=new HttpClient();
	PostMethod method=new PostMethod(applicationProperties.getQubeCellURL());
	try{
		method.setParameter("username", applicationProperties.getUsername());
		method.setParameter("password", applicationProperties.getPassword());
		String requestid = getUuId();
		method.setParameter("requestid", requestid);
		String key = getMD5(applicationProperties.getSecretKey() + requestid);
		method.setParameter("key", key);
		method.setParameter("returnurl", applicationProperties.getMsisdnReturnURL());
		
		client.executeMethod(method);
		if (method.getStatusCode() == 101) {
			 //response = method.getResponseBodyAsString();
			InputStream inputStream=method.getResponseBodyAsStream();
			Writer writer = new StringWriter();
			char[] bytes = new char[1024];
			Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			int read;
			while ((read = reader.read(bytes))!= -1) {
				writer.write(bytes, 0, read);
			}
			String response=writer.toString();
			logger.warn("Msisdn Response = " + response);
			String msisdn = parseXML(response, "msisdn");
			String operator = parseXML(response, "operator");
			status = msisdn+"$"+operator;
			}else{
				
				logger.error("Response code from "+method.getStatusCode());
				status = "Error while communicating with Qubecell";
			}
	}catch (Exception e) {
		// TODO: handle exception
		logger.error("Error caught "+e.toString());
		throw e;
	}finally{
		if(method!=null)
		method.releaseConnection();
	}
	return status;
	}
	
	public String hitBilling(String operatorName, String redirectURL, Integer productId,String msisdn) throws Exception{
		HttpClient client=new HttpClient();
		PostMethod method=new PostMethod(applicationProperties.getQubeCellURL());
		try{
			logger.warn("Hitting URL = " + applicationProperties.getQubeCellURL());
			method.setParameter("username", applicationProperties.getUsername());
			method.setParameter("password", applicationProperties.getPassword());
			String requestid = getUuId();
			method.setParameter("requestid", requestid);
			String key = getMD5(applicationProperties.getSecretKey() + requestid);
			method.setParameter("key", key);
			method.setParameter("redirecturl", applicationProperties.getEventChargeReturnURL());
			String operation="EventCharge";
			method.setParameter("operation", operation);
			method.setParameter("productid", productId.toString());
			method.setParameter("msisdn", msisdn);
			
			
			client.executeMethod(method);
			if (method.getStatusCode() == 101 ) {
				 String response = method.getResponseBodyAsString();
				InputStream inputStream=method.getResponseBodyAsStream();
				Writer writer = new StringWriter();
				char[] bytes = new char[1024];
				Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				int read;
				while ((read = reader.read(bytes))!= -1) {
					writer.write(bytes, 0, read);
				}
				response=writer.toString();
				logger.warn("billing Response = " + response);
				status = "success" + response;
			}else{
				status = getBillingErrorMessage(method.getStatusCode());
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error caught "+e.toString());
			throw e;
		}finally{
			if(method!=null)
			method.releaseConnection();
		}
		return status;
		} 
	
	private String parseXML(String xml, String node){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(""));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName(node);
            System.out.println(list.item(0).getTextContent());
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }
        return "";
	}
	private String getMsisdnErrorMessage(int errorCode){
		String status = "";
		if(errorCode == 101)
			status = "success";
		else if(errorCode == 102)
			status = "Mandatory parameters missing";
		else if(errorCode == 103)
			status = "Authentication failure";
		else if(errorCode == 104)
			status = "Operator not integrated with Qubecell";
		else if(errorCode == 105)
			status = "Duplicate request id";
		else if(errorCode == 106)
			status = "Undetermined error";
		else if(errorCode == 107)
			status = "Invalid key";
		else if(errorCode == 108)
			status = "Invalid return URL";
		else 
			status = "Unknown Error While billing";
		return status;
	}
	
	private String getBillingErrorMessage(int errorCode){
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
	
	private String getUuId(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
