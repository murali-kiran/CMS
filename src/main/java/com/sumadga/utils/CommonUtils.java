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

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLEngine;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

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
	@Autowired
	WebApplicationContext wac;
	
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
		
		String operator=request.getParameter("operator");
		if(operator!=null)
			session.setAttribute("operator", operator);
       
		logger.warn("Msisdn in serviceUtils GetMsisdn Method : " + msisdn);
		return msisdn;
	}
	
	public Map<String,String> getDeviceCapbilities(HttpServletRequest request){
		WURFLEngine wurflHolder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		Device device = wurflHolder.getDeviceForRequest(request);
		String width = device.getCapability("resolution_width");
		String height = device.getCapability("resolution_height");
		String userAgent = request.getHeader("User-Agent");
		String device_os = device.getCapability("device_os");
		
		if(device_os.contains("Android")||device_os.contains("iPhone")){
			if(height.equals("264")){
				width = "320";height = "240";
			}
		}else{
			if(height.equals("264")){
				width = "320";height = "240";
			}
		}
		
		Map<String, String> mobileCapbilityMap = new HashMap<String, String>();
		
		// Android
		mobileCapbilityMap.put("width", width);
		mobileCapbilityMap.put("height", height);
		mobileCapbilityMap.put("userAgent", userAgent);
		mobileCapbilityMap.put("device_os", device_os);
		setPreviewWidthAndHeight(mobileCapbilityMap, width);
		
		return mobileCapbilityMap;
	}
	
public void setPreviewWidthAndHeight(Map<String, String> mobileCapbilityMap,String resolution_width){
		
		int width = Integer.parseInt(resolution_width);
		if(width > 350){
			mobileCapbilityMap.put("preview_width", "100px");
			mobileCapbilityMap.put("preview_height", "100px");
		}else if(width >= 240 && width <= 350){
			mobileCapbilityMap.put("preview_width", "75px");
			mobileCapbilityMap.put("preview_height", "75px");
		}else if(width < 240){
			mobileCapbilityMap.put("preview_width", "50px");
			mobileCapbilityMap.put("preview_height", "50px");
		}
		
		
	}

}
