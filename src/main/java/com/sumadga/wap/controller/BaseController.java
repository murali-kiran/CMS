package com.sumadga.wap.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLEngine;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class BaseController {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(BaseController.class);
	public static final String NO_MSISDN_ERROR_MESSAGE = "Sorry, you are not authorised to view this page. ";
	
	protected String bannerUrl;
	protected String bannerClickDestinationUrl;
	protected String isAdExist;
	String isActucalSizeAvaiable = "no"; 
	
	@Autowired
	ServletContext context;
	
	@Autowired
	WebApplicationContext wac;
	
	public static String getMSISDN(HttpServletRequest request){
		String msisdn = request.getHeader("x-up-calling-line-id");
		if (msisdn!=null&&msisdn.isEmpty()) {
			msisdn=null;
		}
		if (msisdn==null) {
			msisdn=request.getHeader("x-msisdn");
		}
		
		if (msisdn==null) {
			msisdn=request.getHeader("msisdn");
		}
		
		if (msisdn==null) {
			msisdn=request.getHeader("nbg-imp-msisdn");
		}
		if (msisdn==null) {
			msisdn=request.getHeader("x-nokia-msisdn");
		}
		if (msisdn==null) {
			msisdn=request.getHeader("_rapmin");
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
		
		if(msisdn==null)
			msisdn=request.getParameter("msisdn");
		else if(msisdn.trim().length()==0)
			msisdn=null;
		else if(msisdn.trim().length()==10)
			msisdn="91"+msisdn;
			
     //  logger.warn("getMSISDN(HttpServletRequest request)" + msisdn);
		return msisdn;
	}
	
	//For WURFL to get the resolution width... 
	private int getResolutionSize(HttpServletRequest request){
		WURFLEngine wurflHolder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		Device device = wurflHolder.getDeviceForRequest(request);
		/*logger.info("-----------Device-----------"+ device.getId());
		logger.info("-----------brand_name--------"+device.getCapability("brand_name"));
		logger.info("-----------model_name--------"+device.getCapability("model_name"));
		logger.info("-----------marketing_name------"+device.getCapability("marketing_name"));
		logger.info("----------model_extra_info-------"+device.getCapability("model_extra_info"));
		logger.info("----------nokia_series---------"+device.getCapability("nokia_series"));
		logger.info("----------nokia_edition--------"+device.getCapability("nokia_edition"));
		logger.info("---------device_os----------"+device.getCapability("device_os"));
		logger.info("---------device_os_version-------"+device.getCapability("device_os_version"));
		logger.info("---------mobile_browser-------"+device.getCapability("mobile_browser"));
		logger.info("---------columns-----------"+device.getCapability("columns"));
		logger.info("---------rows--------"+device.getCapability("rows"));
		logger.info("---------max_image_width----------"+device.getCapability("max_image_width"));
		logger.info("--------max_image_height--------"+device.getCapability("max_image_height"));
		logger.info("---------physical_screen_width-------"+device.getCapability("physical_screen_width"));
		logger.info("---------physical_screen_height-------"+device.getCapability("physical_screen_height"));
		logger.info("---------wallpaper_max_width---------"+device.getCapability("wallpaper_max_width"));
		logger.info("---------wallpaper_max_height---------"+device.getCapability("wallpaper_max_height"));
		logger.info("---------wallpaper_preferred_width-------"+device.getCapability("wallpaper_preferred_width"));
		logger.info("---------wallpaper_preferred_height-------"+device.getCapability("wallpaper_preferred_height"));
		logger.info("----------hinted_progressive_download------"+device.getCapability("hinted_progressive_download"));
		logger.info("---------playback_3gpp--------"+device.getCapability("playback_3gpp"));
		logger.info("--------");*/
		/*Map<String, String> map = device.getCapabilities();
		logger.info(map);
		Set set = map.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			logger.info(iterator.next().toString()+"==========="+map.get(iterator.next()));
		}*/
		logger.info("-------Resolution Width-------------"+device.getCapability("resolution_width"));
		logger.info("-------Resolution Height-------------"+device.getCapability("resolution_height"));
		
		int size = Integer.parseInt(device.getCapability("resolution_width"));
		return size;
	}
	
	private Integer [] getMobileResolutionWidthHeight(HttpServletRequest request){
		WURFLEngine wurflHolder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		Device device = wurflHolder.getDeviceForRequest(request);
		int width = Integer.parseInt(device.getCapability("resolution_width"));
		int height = Integer.parseInt(device.getCapability("resolution_height"));
		Integer [] resolution = new Integer[2];
		resolution[0] = width;
		resolution[1] = height;
		return resolution;
	}
	
	private String getResolutionWidthHeight(HttpServletRequest request){
		WURFLEngine wurflHolder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		Device device = wurflHolder.getDeviceForRequest(request);
		int width = Integer.parseInt(device.getCapability("resolution_width"));
		int height = Integer.parseInt(device.getCapability("resolution_height"));
		Integer [] resolution = new Integer[2];
		resolution[0] = width;
		resolution[1] = height;
		return width+"x"+height;
	}
	
	public Map<String,String> getDeviceCapbilities(HttpServletRequest request){
		WURFLEngine wurflHolder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		Device device = wurflHolder.getDeviceForRequest(request);
		String width = device.getCapability("resolution_width");
		String height = device.getCapability("resolution_height");
		String userAgent = request.getHeader("User-Agent");
		
		Map<String, String> mobileCapbilityMap = new HashMap<String, String>();
		mobileCapbilityMap.put("width", width);
		mobileCapbilityMap.put("height", height);
		mobileCapbilityMap.put("userAgent", userAgent);
		
		return mobileCapbilityMap;
	}
	
	public String getRequestWidthHeight(HttpServletRequest request) {
		Vector<Integer> width = new Vector<Integer>(0);
		Vector<Integer> height = new Vector<Integer>(0);
		width.add(128);width.add(176);width.add(208);width.add(240);width.add(320);
		height.add(128);height.add(160);height.add(208);height.add(220);height.add(240);height.add(320);
		StringTokenizer widthHeight = new StringTokenizer(getResolutionWidthHeight(request), "x");
		int actualWidth=0;
		int actualHeight=0;
		int assignedWidth=0;
		int assignedHeight=0;
		while(widthHeight.hasMoreTokens())
		{
			actualWidth = Integer.parseInt(widthHeight.nextToken());
			actualHeight = Integer.parseInt(widthHeight.nextToken());
		}
		if (width.contains(actualWidth)) {
			assignedWidth = actualWidth;
		}
		else if (actualWidth < width.get(0)) {
			assignedWidth = width.get(0);
		}
		else if (actualWidth > width.get(width.size()-1)) {
			assignedWidth = width.get(width.size()-1);
		}
		else {
			for (int i = 0; i < width.size(); i++) {
				if (width.get(i) > actualWidth) {
					assignedWidth = width.get(i-1);
					break;
				}
			}
		}
		if (height.contains(actualHeight)) {
			assignedHeight = actualHeight;
		}
		else if (actualHeight < height.get(0)) {
			assignedHeight = height.get(0);
		}
		else if (actualHeight > height.get(height.size()-1)) {
			assignedHeight = height.get(height.size()-1);
		}
		else {
			for (int i = 0; i < height.size(); i++) {
				if (height.get(i) > actualHeight) {
					assignedHeight = height.get(i-1);
					break;
				}
			}
		}
		logger.warn("---actual ht:"+actualHeight+"--actualwd:"+actualWidth);
		logger.warn("---assignd ht:"+assignedHeight+"--as width:"+assignedWidth);
		String size = assignedWidth+"x"+assignedHeight;
		if(size.contains("128x128")||size.contains("128x160")||size.contains("176x220")||size.contains("176x208")||size.contains("208x208")||size.contains("240x320")||size.contains("320x240")||size.contains("320x320"))
			return assignedWidth+"x"+assignedHeight;
		else
			if(assignedWidth==128 && assignedHeight>128)
				return "128x160";
			else if(assignedWidth==128 && assignedHeight<=128)
				return "128x128";
		else
			if(assignedWidth==176 && assignedHeight>208)
				return "176x220";
			else if(assignedWidth==176 && assignedHeight<=208)
				return "176x208";
		else
			if(assignedWidth==208)
				return "208x208";
		else
			if(assignedWidth==240)
				return "240x320";
		else
			if(assignedWidth==320 && assignedHeight>240)
				return "320x320";
			else if(assignedWidth==320 && assignedHeight<=240)
				return "320x240";
		else
			return "240x320";
	}
	
/*	public String getRequestFullWidthHeight(HttpServletRequest request) {
		Vector<Integer> width = new Vector<Integer>(0);
		Vector<Integer> height = new Vector<Integer>(0);
		width.add(128);width.add(176);width.add(208);width.add(240);width.add(320);width.add(360);width.add(480);
		height.add(128);height.add(160);height.add(208);height.add(220);height.add(240);height.add(320);height.add(640);height.add(800);
		StringTokenizer widthHeight = new StringTokenizer(getResolutionWidthHeight(request), "x");
		int actualWidth=0;
		int actualHeight=0;
		int assignedWidth=0;
		int assignedHeight=0;
		
		while(widthHeight.hasMoreTokens())
		{
			actualWidth = Integer.parseInt(widthHeight.nextToken());
			actualHeight = Integer.parseInt(widthHeight.nextToken());
		}
		if (width.contains(actualWidth)) {
			assignedWidth = actualWidth;
		}
		else if (actualWidth < width.get(0)) {
			assignedWidth = width.get(0);
		}
		else if (actualWidth > width.get(width.size()-1)) {
			assignedWidth = width.get(width.size()-1);
		}
		else {
			for (int i = 0; i < width.size(); i++) {
				if (width.get(i) > actualWidth) {
					assignedWidth = width.get(i-1);
					break;
				}
			}
		}
		if (height.contains(actualHeight)) {
			assignedHeight = actualHeight;
		}
		else if (actualHeight < height.get(0)) {
			assignedHeight = height.get(0);
		}
		else if (actualHeight > height.get(height.size()-1)) {
			assignedHeight = height.get(height.size()-1);
		}
		else {
			for (int i = 0; i < height.size(); i++) {
				if (height.get(i) > actualHeight) {
					assignedHeight = height.get(i-1);
					break;
				}
			}
		}
		logger.warn("---actual ht:"+actualHeight+"--actual width:"+actualWidth);
		logger.warn("---assignd ht:"+assignedHeight+"--assigned  width:"+assignedWidth);
		String size = assignedWidth+"x"+assignedHeight;
		if(size.contains("128x128")||size.contains("128x160")||size.contains("176x220")||size.contains("176x208")||size.contains("208x208")||size.contains("240x320")||size.contains("320x240")||size.contains("320x320")||size.contains("360x640")||size.contains("480x800"))
			return assignedWidth+"x"+assignedHeight;
		else
			if(assignedWidth==128 && assignedHeight>128)
				return "128x160";
			else if(assignedWidth==128 && assignedHeight<=128)
				return "128x128";
		else
			if(assignedWidth==176 && assignedHeight>208)
				return "176x220";
			else if(assignedWidth==176 && assignedHeight<=208)
				return "176x208";
		else
			if(assignedWidth==208)
				return "208x208";
		else
			if(assignedWidth==240)
				return "240x320";
		else
			if(assignedWidth==320 && assignedHeight>240 && assignedHeight<480)
				return "320x320";
			else if(assignedWidth==320 && assignedHeight<=240)
				return "320x240";
			else if(assignedWidth==320 && assignedHeight>=480)
				return "320x320";
		else
			if(assignedWidth==360 && assignedHeight>480)
				return "360x640";
		else
			if(assignedWidth==480 && assignedHeight<640)
				return "360x640";
			else if(assignedWidth==480 && assignedHeight>640)
				return "480x800";
		else
			return "240x320";
	}
*/	//For getting midp value for a game content
	public String getMidpForRequest(HttpServletRequest request)
	{
		WURFLEngine wurflHolder = (WURFLEngine)wac.getBean(WURFLEngine.class.getName());
		Device device = wurflHolder.getDeviceForRequest(request);
		//Map<String,String> a=device.getCapabilities();
		logger.warn("----midp values in wap---"+device.getCapability("j2me_midp_2_0"));
		logger.warn("----midp values---"+device.getCapability("j2me_midp_1_0"));
		if(device.getCapability("j2me_midp_2_0").toLowerCase().contains("true"))
			return "midp_2_0";
		if(device.getCapability("j2me_midp_1_0").toLowerCase().contains("true"))
			return "midp_1_0";
		return "false";
	}
	//For WURFL to get final width size... 
	public Integer getRequestResolutionSize(HttpServletRequest request) {
		Vector<Integer> v = new Vector<Integer>(0);
		v.add(65);v.add(80);v.add(110);v.add(128);v.add(142);v.add(144);v.add(160);
		v.add(176);v.add(208);v.add(220);v.add(240);v.add(320);v.add(416);
		Integer resolution = getResolutionSize(request);
		Integer size = 0 ;
		
		if (v.contains(resolution)) {
			size = resolution;
		}
		else if (resolution < v.get(0)) {
			size = v.get(0);
		}
		else if (resolution > v.get(v.size()-1)) {
			size = v.get(v.size()-1);
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > resolution) {
					size = v.get(i-1);
					break;
				}
			}
		}
		return size;
	}
	//For WURFL to get final width size... 
	public Integer getRequestResolutionBanners(HttpServletRequest request) {
		Vector<Integer> v = new Vector<Integer>(0);
		v.add(110);v.add(120);v.add(165);v.add(180);v.add(210);v.add(230);v.add(240);
		v.add(250);v.add(280);v.add(295);v.add(320);v.add(360);v.add(480);v.add(515);
		Integer resolution = getResolutionSize(request);
		Integer size = 0 ;
		
		if (v.contains(resolution)) {
			size = resolution;
		}
		else if (resolution < v.get(0)) {
			size = v.get(0);
		}
		else if (resolution > v.get(v.size()-1)) {
			size = v.get(v.size()-1);
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > resolution) {
					size = v.get(i-1);
					break;
				}
			}
		}
		return size;
	}
	//For WURFL to get final width size... 
	public Integer getColorLogosRequestResolutionSize(HttpServletRequest request) {
		Vector<Integer> v = new Vector<Integer>(0);
		v.add(25);v.add(50);v.add(80);v.add(100);
		Integer resolution = getResolutionSize(request);
		Integer size = 0 ;
		
		if (v.contains(resolution)) {
			size = resolution;
		}
		else if (resolution < v.get(0)) {
			size = v.get(0);
		}
		else if (resolution > v.get(v.size()-1)) {
			size = v.get(v.size()-1);
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > resolution) {
					size = v.get(i-1);
					break;
				}
			}
		}
		return size;
	}
	public Integer getBannerWidthforADS(HttpServletRequest request) {
		Vector<Integer> v = new Vector<Integer>(0);
		v.add(80);v.add(84);v.add(90);v.add(92);v.add(96);v.add(98);
		v.add(101);v.add(120);v.add(128);v.add(130);v.add(132);v.add(150);v.add(160);
		v.add(176);v.add(208);v.add(220);v.add(232);v.add(240);v.add(300);v.add(310);v.add(312);
		v.add(315);v.add(316);v.add(316);v.add(320);v.add(352);v.add(360);v.add(480);v.add(640);
		Integer resolution = getResolutionSize(request);
		Integer size = 0 ;
		
		if (v.contains(resolution)) {
			size = resolution;
		}
		else if (resolution < v.get(0)) {
			size = v.get(0);
		}
		else if (resolution > v.get(v.size()-1)) {
			size = v.get(v.size()-1);
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > resolution) {
					size = v.get(i-1);
					break;
				}
			}
		}
		return size;
	}
	
	//This is for getting resolution for Animations and Wallpapers...
	//Here we are getting Height...
	public Integer getResolutionSizeAnimationWallPaper(HttpServletRequest request) {
		Vector<Integer> v = new Vector<Integer>(0);
		v.add(65);v.add(80);v.add(110);v.add(128);v.add(142);v.add(144);v.add(160);
		v.add(176);v.add(208);v.add(220);v.add(240);v.add(320);v.add(416);
		Integer resolution = getResolutionSize(request);
		Integer size = 0 ;
		
		if (v.contains(resolution)) {
			size = resolution;
		}
		else if (resolution < v.get(0)) {
			size = v.get(0);
		}
		else if (resolution > v.get(v.size()-1)) {
			size = v.get(v.size()-1);
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > resolution) {
					size = v.get(i-1);
					break;
				}
			}
		}
		return size;
	}
	public Integer getRequestResolutionCategoryBanners(HttpServletRequest request) {
		Vector<Integer> v = new Vector<Integer>(0);
		v.add(90);v.add(128);v.add(176);v.add(240);v.add(320);
		Integer resolution = getResolutionSize(request);
		Integer size = 0 ;
		
		if (v.contains(resolution)) {
			size = resolution;
		}
		else if (resolution < v.get(0)) {
			size = v.get(0);
		}
		else if (resolution > v.get(v.size()-1)) {
			size = v.get(v.size()-1);
		}
		else {
			for (int i = 0; i < v.size(); i++) {
				if (v.get(i) > resolution) {
					size = v.get(i-1);
					break;
				}
			}
		}
		return size;
	}
	public String getMSISDNVerifyMessage(Integer operatorId){
		String message = null;
		switch (operatorId) {
		case 1:
			message = "Please access this using Airtel Live or Mobile Office APN.";
			break;
		case 2:
			message = "Please access this using Tata.";
			break;
		case 3:
			message = "Please access this using DOCOMO DIVE-IN or DOCOMO Internet APN.";
			break;
		case 4:
			message = "Please access this using Idea Fresh or Idea Internet APN.";
			break;
		case 5:
			message = "Please access this using Aircel WAP or Aircel Internet APN.";
			break;

		default:
			message = "Please access this using your WAP or Internet APN.";
			break;
		}
		
		return message;
	}
	
	


	public String getIsAdExist() {
		return isAdExist;
	}


	public void setIsAdExist(String isAdExist) {
		this.isAdExist = isAdExist;
	}


	public String getBannerUrl() {
		return bannerUrl;
	}


	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}


	public String getBannerClickDestinationUrl() {
		return bannerClickDestinationUrl;
	}


	public void setBannerClickDestinationUrl(String bannerClickDestinationUrl) {
		this.bannerClickDestinationUrl = bannerClickDestinationUrl;
	}
	
	public String getOperaStatus(HttpServletRequest request, int opid){
		String userAgent = request.getHeader("user-agent");
		//String userAgent = "opera mini/4.2";
		logger.warn(" user agent check for opera"+userAgent);
		if(opid == 1 && userAgent != null && userAgent.toLowerCase().contains("opera mini/4.2")){
			String opera = request.getParameter("opera");
			if(opera == null){
				logger.warn("showing opera download page for user agent "+userAgent);
				return request.getRequestURL().toString()+"?"+request.getQueryString()+"&opera=NR"; 
			}
		}
		return null;
	}
	
	

	
	

}
