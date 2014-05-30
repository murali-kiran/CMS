
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
	@RequestMapping("/appUpload")
	public String appUpload(ModelMap model){
		
		logger.info("upload");
		
		mediaUploadService.uploadApp(model);
		
		return "uploadApp";
		
	}
	@RequestMapping(value="/saveapp",  method=RequestMethod.POST)
	public String saveapp(@ModelAttribute("uploadFile") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model){
		
		String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		
		mediaUploadService.validate(mediaUploadModel, result);
		
		if (result.hasErrors()) {
			//if validator failed
			message="";
			mediaUploadService.uploadApp(model);
			return "uploadFile";
		} else {
			try{
				mediaUploadService.saveUploadApp(mediaUploadModel);
			
			status.setComplete();
			message = "Media uploaded successfully";
			mediaUploadService.upload(model);
			
			}catch (Exception e) {
				message=e.getMessage();
				logger.error("Exception while saving uploaded files", e);
			}
			//mediaUploadService.searchMedia(model,null);
			model.addAttribute("message",message);
			mediaUploadService.searchMedia(model,null);
			mediaUploadService.search(model);
			
			return "showSearch";
			//return showSearch(model);
		}
		
	}
	@RequestMapping("/editMedia")
	public String upload(@RequestParam("mediaId") Integer mediaId,ModelMap model){
		
		logger.info("Edit Media");
		
		mediaUploadService.edit( model,mediaId);
		
		return "uploadFile";
		
	}
	
	@RequestMapping("/showMediaContent")
	public String showMediaFile(@RequestParam("mediaId") Integer mediaId,ModelMap model){
		
		logger.info("show Media Content ");
		
		mediaUploadService.showMediaContent( model,mediaId);
		
		return "mediaContent";
		
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
			message="";
			mediaUploadService.upload(model);
			return "uploadFile";
		} else {
			try{
				mediaUploadService.saveUpload(mediaUploadModel);
			
			status.setComplete();
			message = "Media uploaded successfully";
			mediaUploadService.upload(model);
			
			}catch (Exception e) {
				message=e.getMessage();
				logger.error("Exception while saving uploaded files", e);
			}
			//mediaUploadService.searchMedia(model,null);
			model.addAttribute("message",message);
			mediaUploadService.searchMedia(model,null);
			mediaUploadService.search(model);
			
			return "showSearch";
			//return showSearch(model);
		}
		
	}
	
	@RequestMapping("/showSearch")
	public String showSearch(ModelMap model){
		
		logger.info("upload");
		mediaUploadService.searchMedia(model,null);
		mediaUploadService.search(model);
		
		return "showSearch";
		
	}
	@RequestMapping(value="/searchMedia",  method=RequestMethod.POST)
	public String searchMedia(@ModelAttribute("searchMedia") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model){
	
        String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		//mediaUploadService.search(model);
		mediaUploadService.searchMedia(model,mediaUploadModel);
		
		return "showSearch";
		//return "searchList";
	}
	@RequestMapping(value="/searchMediaPaging",  method=RequestMethod.GET)
	public String searchMediaPaging(MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model){
	
        String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		//mediaUploadService.search(model);
		mediaUploadService.searchMedia(model,mediaUploadModel);
		
		return "showSearch";
		//return "searchList";
	}
}


