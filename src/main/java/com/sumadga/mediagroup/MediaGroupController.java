package com.sumadga.mediagroup;

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

import com.sumadga.upload.MediaUploadModel;



@Controller
public class MediaGroupController {

	private static final Logger logger = Logger.getLogger(MediaGroupController.class);
	
	
	@Autowired
	MediaGroupService mediaGroupService;
	
	/*@RequestMapping("/show")
	public void show(){
		
		logger.info("log4j");
	}*/
	
	@RequestMapping("/addGroup")
	public String createGroup(ModelMap model){
		
		logger.info("upload");
		
		mediaGroupService.createGroup(model);
		
		return "addGroup";
		
	}
	@RequestMapping(value="/saveGroup",  method=RequestMethod.POST)
	public String saveGroup(@ModelAttribute("addGroup") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			System.out.println("mm"+mediaGroupModel);
			mediaGroupService.validate(mediaGroupModel, result);
			
			if (result.hasErrors()) {
				//if validator failed
				return "addGroup";
			} else {
				try{
					mediaGroupService.saveGroup(mediaGroupModel);
				
				status.setComplete();
				message = "upload success";
				mediaGroupService.createGroup(model);
				mediaGroupService.listGroup(model);
				return "mediaGroupList";
				}catch (Exception e) {
					message=e.getMessage();
					logger.error("Exception while saving uploaded files", e);
				}
				return "addGroup";
			}
	}
	@RequestMapping("/listMediaGroup")
	public String listGroup(ModelMap model){
		
		logger.info("upload");
		mediaGroupService.listGroup(model);
		return "mediaGroupList";
		
	}
	
	@RequestMapping(value = "/showSearchMap",  method=RequestMethod.GET)
	//@ResponseBody
	public String getFiles(@RequestParam("mgid") Integer mediaGroupId,ModelMap model){
		mediaGroupService.getMedia(model, mediaGroupId);
		model.addAttribute("mgid", mediaGroupId);
		return "selectMedia";
	}
	@RequestMapping(value="/showRemMedia",  method=RequestMethod.POST)
	public String searchMedia(@ModelAttribute("searchMedia") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model){
		mediaGroupService.getMedia(model, mediaUploadModel.getMgid());
		mediaGroupService.getRemainingMedia(mediaUploadModel, model);
		
		return "selectMedia";//MediaContentModelList;
		
	}
	
	@RequestMapping(value="/remAddOrderMedia",  method=RequestMethod.POST)
	public String remAddOrderMedia(@ModelAttribute("mediaList") MediaModel mediaModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		if(mediaModel != null && mediaModel.getSelectedMedia() != null && mediaModel.getSelectedMedia().length > 0){
		try {
			mediaGroupService.mapMedia(mediaModel);
			message = "mapping success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "mapping failed";
		}
		}
		mediaGroupService.getMedia(model, mediaModel.getMgid());
		model.addAttribute("mgid", mediaModel.getMgid());
		return "selectMedia";
	}
	@RequestMapping(value="/saveMappedMedia",  method=RequestMethod.POST)
	public String saveMappedMedia(@ModelAttribute("remMediaList") MediaModel mediaModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		if(mediaModel != null && mediaModel.getSelectedMedia() != null && mediaModel.getSelectedMedia().length > 0){
		try {
			mediaGroupService.addMedia(mediaModel);
			message = "mapping success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "mapping failed";
		}
		}
		mediaGroupService.listGroup(model);
		return "mediaGroupList";
	}
	@RequestMapping("/welcome")
	public String welcome(ModelMap model){
		
		logger.info("upload");
		return "welcome";
		
	}
}
