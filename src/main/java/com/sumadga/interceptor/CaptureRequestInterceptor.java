package com.sumadga.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sumadga.wap.controller.ServiceUtils;

public class CaptureRequestInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = Logger.getLogger(CaptureRequestInterceptor.class);
	private static final Logger requestLogger = Logger.getLogger("REQUEST_HEADER");
	
	@Autowired
	ServletContext context;
	
	@Autowired
	WebApplicationContext wac;
	

	@Autowired
	ServiceUtils serviceUtils;
	
	Long beforeTime=null;
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
        	 
		StringBuffer other_details=new StringBuffer();
		
		Enumeration<String> headers=request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = (String) headers.nextElement();
			other_details.append(header+":"+request.getHeader(header)+"||");
		}
		StringBuffer query_string=new StringBuffer();
		
		Enumeration<String> params=request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = (String) params.nextElement();
			query_string.append(param+":"+request.getParameter(param)+"&");
		}
		
		
		

		StringBuilder captureRequestStr = new StringBuilder();
		captureRequestStr.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//created_time
		captureRequestStr.append("##");
		captureRequestStr.append(request.getParameter("channel"));// channel
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRemoteHost());//host 
		captureRequestStr.append("##");
		captureRequestStr.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//modified_time
		captureRequestStr.append("##");
		captureRequestStr.append(serviceUtils.getMsisdn(request)); // msisdn
		captureRequestStr.append("##");
		captureRequestStr.append(other_details.toString()); // other_details
		captureRequestStr.append("##");
		captureRequestStr.append(query_string.toString()); // query_string
		captureRequestStr.append("##");
		captureRequestStr.append(request.getHeader("referer")); // referer
		captureRequestStr.append("##");
		captureRequestStr.append(request.getRequestURL()); // requestURL
		captureRequestStr.append("##");
		captureRequestStr.append(request.getSession().getId());//session id
		captureRequestStr.append("##");
		captureRequestStr.append(request.getHeader("user-agent"));

		requestLogger.info(captureRequestStr.toString().replace("null", "NULL"));
		
		beforeTime=System.currentTimeMillis();
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info(request.getRequestURL()+" Service Reqeust Processing took : "+((System.currentTimeMillis()-beforeTime)/1000) + " ms");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
