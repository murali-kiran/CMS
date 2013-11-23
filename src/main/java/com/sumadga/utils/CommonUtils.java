package com.sumadga.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumadga.wap.controller.ServiceUtils;

@Component
public class CommonUtils {
	
	Logger logger=Logger.getLogger(CommonUtils.class);
	
	public static final int MEDIA_CONTENT_PRIVIEW = 1;
	public static final int MEDIA_CONTENT_NON_PRIVIEW = 2;
	public static final int PRIVIEW_WIDTH = 100;
	public static final int PRIVIEW_HEIGHT = 100;
	
	@Autowired
	HttpSession session;
	
	public static Map<Integer,Integer> paginationStringToMap(String paginationStr,int typeId,int pageId){
		
		String [] ss = paginationStr.split(",");
		
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(String s1 : ss){
			String [] s2 = s1.split(":");
			
			if(Integer.parseInt(s2[0]) == typeId)
				map.put(typeId, pageId);
			else
				map.put(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
		}
		
		return map;
	}
	
	public static void AppendInfoToCsv(String filePath,String catureRequestStr){
		try
		{
			File file = new File(filePath);
		    FileWriter writer = new FileWriter(file,true);
	 
		    writer.append(catureRequestStr);
		   
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	}
	
	public static Timestamp convertDateToTimeStamp(Date date) {
		
	      return new Timestamp((new Date()).getTime());
		
	}
	
	public String getMsisdn(HttpServletRequest request){
		String msisdn = request.getParameter("msisdn");
		if(msisdn==null)
			request.getHeader("msisdn");
		
		if(msisdn==null)
		{
			if(session.getAttribute("msisdn")!=null)
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
			
       logger.warn("Msisdn in serviceUtils GetMsisdn Method : " + msisdn);
		return msisdn;
	}

}
