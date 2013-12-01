package com.sumadga.mediagroup;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.sumadga.model.JqGridInfo;
import com.sumadga.model.MediaGroupBean;
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
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String welcomePage(Model model){
		return "forward:/login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
	
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
				message = "Media Group created/updated successfully";
				mediaGroupService.createGroup(model);
				mediaGroupService.listGroup(model);
				model.addAttribute("message",message);
				return "mediaGroupList";
				}catch (Exception e) {
					message=e.getMessage();
					logger.error("Exception while saving/updating uploaded files", e);
					message = "Media Group creation/updation falied due to "+e.getMessage();
					model.addAttribute("message",message);
				}
				message = "Media Group creation/updation falied due to internal error";
				model.addAttribute("message",message);
				return "addGroup";
			}
	}
	@RequestMapping("/listMediaGroup")
	public String listGroup(ModelMap model){
		logger.info("upload");
		return "mediaGroupList";
	}

	
	@RequestMapping("/listMediaGroupAjaxCopy")
	public @ResponseBody JqGridInfo<MediaGroupBean> listGroupAjaxCopy(ModelMap model){
		logger.info("upload");
		return mediaGroupService.listAjaxGroup();
	}
	
	@RequestMapping(value = "/showSearchMap",  method=RequestMethod.GET)
	//@ResponseBody
	public String getFiles(@RequestParam("mgid") Integer mediaGroupId,ModelMap model){
		mediaGroupService.getMedia(model, mediaGroupId);
		model.addAttribute("mgid", mediaGroupId);
		return "selectMedia";
	}
	@RequestMapping(value = "/showMappedGroup",  method=RequestMethod.GET)
	//@ResponseBody
	public String getGroups(@RequestParam("mgid") Integer mediaGroupId,ModelMap model){
		mediaGroupService.getGroups(model, mediaGroupId);
		model.addAttribute("mgid", mediaGroupId);
		return "selectMediaGroup";
	}
	@RequestMapping(value = "/showGroupSearch",  method=RequestMethod.GET)
	//@ResponseBody
	public String showGroupSearch(@RequestParam("mgid") Integer mediaGroupId,ModelMap model){
		//mediaGroupService.getGroups(model, mediaGroupId);
		//model.addAttribute("mgid", mediaGroupId);
		MediaGroupModel mediaGroupModel = new MediaGroupModel();
		model.addAttribute("searchMediaGroup", mediaGroupModel);
		model.addAttribute("mgid", mediaGroupId);
		return "showGroupSearch";
	}
	@RequestMapping(value="/showRemMedia",  method=RequestMethod.POST)
	public String searchMedia(@ModelAttribute("searchMedia") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model){
		mediaGroupService.getMedia(model, mediaUploadModel.getMgid());
		mediaGroupService.getRemainingMedia(mediaUploadModel, model);
		
		return "selectMedia";//MediaContentModelList;
		
	}
	@RequestMapping(value="/showRemMediaGroup",  method=RequestMethod.POST)
	public String searchRemMediaGroup(@ModelAttribute("searchMediaGroup") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model){
		mediaGroupService.getGroups(model, mediaGroupModel.getParentmgId());
		mediaGroupService.getRemainingMediaGroup(mediaGroupModel, model);
		
		return "selectMediaGroup";//MediaContentModelList;
		
	}
	@RequestMapping(value="/remAddOrderMedia",  method=RequestMethod.POST)
	public String remAddOrderMedia(@ModelAttribute("mediaList") MediaModel mediaModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		if(mediaModel != null && mediaModel.getSelectedMedia() != null && mediaModel.getSelectedMedia().length > 0){
		try {
			mediaGroupService.mapMedia(mediaModel);
			message = "Media order/removel success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media order/removel failed";
		}
		}
		mediaGroupService.getMedia(model, mediaModel.getMgid());
		model.addAttribute("mgid", mediaModel.getMgid());
		model.addAttribute("message",message);
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
			message = "Media mapped successfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "mapping failed";
		}
		}
		mediaGroupService.listGroup(model);
		model.addAttribute("message",message);
		return "mediaGroupList";
	}
	
	
	@RequestMapping(value="/remAddOrderMediaGroup",  method=RequestMethod.POST)
	public String remAddOrderMediaGroup(@ModelAttribute("mediaGroupList") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		if(mediaGroupModel != null && mediaGroupModel.getSelectedMediaGroup() != null && mediaGroupModel.getSelectedMediaGroup().length > 0){
		try {
			mediaGroupService.remAddOrderMediaGroup(mediaGroupModel);
			message = "Media Group order/removal success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media Group order/removal failed";
		}
		}
		//mediaGroupService.getMedia(model, mediaGroupModel.getMgid());
		//model.addAttribute("mgid", mediaModel.getMgid());
		//mediaGroupService.listGroup(model);
		mediaGroupService.getGroups(model, mediaGroupModel.getParentmgId());
		model.addAttribute("mgid", mediaGroupModel.getParentmgId());
		model.addAttribute("message",message);
		return "selectMediaGroup";
	}
	@RequestMapping(value="/saveMappedMediaGroup",  method=RequestMethod.POST)
	public String saveMappedMediaGroup(@ModelAttribute("remMediaList") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		if(mediaGroupModel != null && mediaGroupModel.getSelectedMediaGroup() != null && mediaGroupModel.getSelectedMediaGroup().length > 0){
		try {
			mediaGroupService.addMediaGroup(mediaGroupModel);
			message = "Media Group mapping success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media group mapping failed";
		}
		}
		mediaGroupService.listGroup(model);
		model.addAttribute("message",message);
		return "mediaGroupList";
	}
	@RequestMapping("/welcome")
	public String welcome(ModelMap model){
		
		logger.info("upload");
		return "welcome";
		
	}
	@RequestMapping(value = "/editMediaGroup",  method=RequestMethod.GET)
	//@ResponseBody
	public String editGroup(@RequestParam("mgid") Integer mediaGroupId,ModelMap model){
		//mediaGroupService.getGroups(model, mediaGroupId);
		mediaGroupService.editGroup(model,mediaGroupId);
		return "addGroup";
	}
}
