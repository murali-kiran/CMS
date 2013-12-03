package com.sumadga.wap.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtils {
	Logger logger=Logger.getLogger(ServiceUtils.class);

	public String getMsisdn(HttpServletRequest request){
		String msisdn = request.getParameter("msisdn");
		if(msisdn==null)
			request.getHeader("msisdn");
		
		if(msisdn==null)
		{
			if(request.getSession().getAttribute("msisdn")!=null)
			msisdn=request.getSession().getAttribute("msisdn").toString();
		}
		
		if(msisdn==null)
		{
			Cookie[] cookie=request.getCookies();
			if(cookie!=null)
			{
				for (int i = 0; i < cookie.length; i++) {
					Cookie cookie2 = cookie[i];
					if(cookie2.getName().equalsIgnoreCase("msisdn"))
					{
						msisdn=cookie2.getValue();
					}
				}
			}
		}
		
		if(msisdn!=null && msisdn.trim().isEmpty())
			msisdn = null;
		if(msisdn!=null && msisdn.trim().equalsIgnoreCase("null"))
			msisdn=null;
		if(msisdn!=null && msisdn.trim().length()==10)
			msisdn="91"+msisdn;
		
		String operator="Vodafone";
		if(request.getParameter("operator")!=null)
		{
			operator=request.getParameter("operator");
		}else if(request.getSession().getAttribute("operator")!=null)
			operator=request.getSession().getAttribute("operator").toString();
		
		if(operator!=null && !operator.trim().isEmpty() && !operator.trim().equalsIgnoreCase("null"))
			request.getSession().setAttribute("operator", operator);
			
       logger.warn("Msisdn in serviceUtils GetMsisdn Method : " + msisdn);
		return msisdn;
	}	
}
