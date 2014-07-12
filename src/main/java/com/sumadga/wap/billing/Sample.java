package com.sumadga.wap.billing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//getMD5("f2543951365f4d608ec212ff9b21ad91"+"test_4");
		
		BillingUtils billingUtils = new BillingUtils();
		//System.out.println(":"+billingUtils.getIpayMsisdnDetectionUrl());
	}

	
	private static String getMD5(String string){
	    try {
	    	   MessageDigest m=MessageDigest.getInstance("MD5");
			   m.update(string.getBytes(),0,string.length());
			   String md5 = new BigInteger(1,m.digest()).toString(16);
			   System.out.println("MD5: "+md5 );
			   return md5;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
}
