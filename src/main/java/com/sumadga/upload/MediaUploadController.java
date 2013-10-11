package com.sumadga.upload;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MediaUploadController {

	private static final Logger logger = Logger.getLogger(MediaUploadController.class);
	
	
	@Autowired
	MediaUploadService mediaUploadService;
	
	@RequestMapping("/show")
	public void show(){
		
		logger.info("log4j");
	}
	
	@RequestMapping("/upload")
	public String upload(ModelMap model){
		
		logger.info("upload");
		
		mediaUploadService.upload(model);
		
		return "uploadFile";
		
	}
	
		
	@RequestMapping(value = "/getFiles",  method=RequestMethod.GET)
	//@ResponseBody
	public String getFiles(@RequestParam("mediaTypeId") Integer mediaTypeId,ModelMap model){
		
		mediaUploadService.getFiles(model, mediaTypeId);
		
		return "showFile";//MediaContentModelList;
		
	}
	
}
