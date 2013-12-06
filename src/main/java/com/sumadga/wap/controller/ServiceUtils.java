package com.sumadga.wap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumadga.dao.MsisdnDao;
import com.sumadga.dto.Msisdn;



@Component
public class ServiceUtils {
	Logger logger=Logger.getLogger(ServiceUtils.class);

	@Autowired
	MsisdnDao msisdnDao;
	
	
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
		
		String operator=null;
		if(request.getParameter("operator")!=null)
		{
			operator=request.getParameter("operator");
		}else if(request.getSession().getAttribute("operator")!=null)
			operator=request.getSession().getAttribute("operator").toString();
		
	
		if(msisdn!=null && !msisdn.trim().isEmpty()){
			List<Msisdn> msisdnList=msisdnDao.findByProperty("msisdn", msisdn);
			
				if((msisdnList==null || msisdnList.isEmpty()) && operator!=null && !operator.trim().isEmpty() && !operator.trim().equalsIgnoreCase("null")){
					Msisdn msisdn2=new Msisdn();
					msisdn2.setMsisdn(Long.parseLong(msisdn));
					msisdn2.setNetwork(operator);
					msisdnDao.save(msisdn2);
				}else if(msisdnList!=null && !msisdnList.isEmpty() && (operator==null || operator.trim().isEmpty() || operator.trim().equalsIgnoreCase("null"))) {
					for (Msisdn msisdn2 : msisdnList) {
						operator=msisdn2.getNetwork();
					}
				}
			
		}
	
		if(operator!=null && !operator.trim().isEmpty() && !operator.trim().equalsIgnoreCase("null"))
			request.getSession().setAttribute("operator", operator);
			
		if(msisdn!=null && !msisdn.trim().isEmpty() && !msisdn.trim().equalsIgnoreCase("null"))
			request.getSession().setAttribute("msisdn", msisdn);
		
       logger.warn("Msisdn in serviceUtils GetMsisdn Method : " + msisdn + "operator : "+operator);
		return msisdn;
	}	
}
