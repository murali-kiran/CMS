package com.sumadga.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.MarkUp;
import net.sourceforge.wurfl.core.WURFLEngine;
import net.sourceforge.wurfl.core.exc.CapabilityNotDefinedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sumadga.dao.MediaContentPurposDao;

public class CaptureRequestInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = Logger.getLogger(CaptureRequestInterceptor.class);
	
	@Autowired
	ServletContext context;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
        WURFLEngine holder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		
		Device device = holder.getDeviceForRequest(request);
				
		logger.info("Device: " + device.getId());
		logger.info("Capability: " + device.getCapability("preferred_markup"));

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
