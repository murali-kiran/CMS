package com.sumadga.upload;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;



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
	
	@RequestMapping(value="/save",  method=RequestMethod.POST)
	public String save(@ModelAttribute("uploadFile") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model){
	
        String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		
		mediaUploadService.validate(mediaUploadModel, result);
		
		if (result.hasErrors()) {
			//if validator failed
			mediaUploadService.upload(model);
			return "uploadFile";
		} else {
			try{
				mediaUploadService.saveUpload(mediaUploadModel);
			
			status.setComplete();
			message = "upload success";
			mediaUploadService.upload(model);
			
			}catch (Exception e) {
				message=e.getMessage();
				logger.error("Exception while saving uploaded files", e);
			}
			return "uploadFile";
		}
		
	}
	
}
