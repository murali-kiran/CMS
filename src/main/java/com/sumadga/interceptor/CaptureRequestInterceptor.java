package com.sumadga.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLEngine;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sumadga.utils.CommonUtils;

public class CaptureRequestInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = Logger.getLogger(CaptureRequestInterceptor.class);
	
	@Autowired
	ServletContext context;
	
	@Autowired
	WebApplicationContext wac;
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
        WURFLEngine holder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		
		Device device = holder.getDeviceForRequest(request);
		logger.info("I am in interceptor ");
				
	/*	
		String deviceCapabilities = device.getCapability("preferred_markup");
		Map<String, String> deviceCapabilitiesMap = device.getCapabilities();
	*/	
		
		request.setAttribute("resolution_width", device.getCapability("resolution_width"));
		request.setAttribute("resolution_height", device.getCapability("resolution_height"));
		 
		/*StringBuilder captureRequestStr = new StringBuilder();
		captureRequestStr.append(device.getId());
		captureRequestStr.append("##");// resolution_height resolution_width
		captureRequestStr.append(deviceCapabilitiesMap.get("resolution_height"));
		captureRequestStr.append("##");
		captureRequestStr.append(deviceCapabilitiesMap.get("resolution_width"));
		captureRequestStr.append("##");
		captureRequestStr.append(request.getHeader("User-Agent"));
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRemoteAddr());
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRemoteHost());
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRemoteUser());
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRequestURI());
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRequestURL()).toString();
		captureRequestStr.append("\n");
		
		CommonUtils.AppendInfoToCsv("/home/sravan/Desktop/capture.csv",captureRequestStr.toString());*/
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
