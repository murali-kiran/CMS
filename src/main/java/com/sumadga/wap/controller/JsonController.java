package com.sumadga.wap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumadga.wap.model.MediaInfoBean;
import com.sumadga.wap.service.ServiceLayer;

@Controller
@Scope("request")
public class JsonController {
	
	Logger logger = Logger.getLogger(JsonController.class);
	
	@Autowired
	private ServiceLayer serviceLayer;
	
	@RequestMapping(value="/json/mediaInfo",method=RequestMethod.GET)
	public @ResponseBody MediaInfoBean getmediaInfo(Model model,HttpServletRequest request,@RequestParam(value="mediaId", required = false) Integer mediaId){
//		return serviceLayer.getMediaInfowithMediaContentofMedia(mediaId);
		
		return serviceLayer.getMediaInfoOfMedia(mediaId);
	}
	
	@RequestMapping(value="/json/allMediaInfo",method=RequestMethod.GET)
	public @ResponseBody List<MediaInfoBean> getAllMediaInfo(Model model,HttpServletRequest request,@RequestParam(value="mediaId", required = false) Integer mediaId){
		return serviceLayer.getAllMediaInfo();
	}
	
	@RequestMapping(value="/json/allMediaInfoOfTag",method=RequestMethod.GET)
	public @ResponseBody List<MediaInfoBean> getAllMediaInfoOfTag(Model model,HttpServletRequest request,@RequestParam(value="tag", required = false,defaultValue="") String tag){
		return serviceLayer.getAllMediaInfoFilterByTag(tag);
	}
	
	

}
