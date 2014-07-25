package com.sumadga.service;

import javax.servlet.http.HttpServletRequest;

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

import com.sumadga.dto.UserPermissions;
import com.sumadga.mediagroup.MediaGroupModel;
import com.sumadga.model.JqGridInfo;
import com.sumadga.model.MediaGroupBean;
import com.sumadga.upload.MediaUploadModel;
import com.sumadga.utils.MediaUtils;



@Controller
public class ServiceController {

	private static final Logger logger = Logger.getLogger(ServiceController.class);
	
	
	@Autowired
	ServiceCmsService serviceCmsService;
	
	/*@RequestMapping("/show")
	public void show(){
		
		logger.info("log4j");
	}*/
	
	@Autowired
	MediaUtils mediaUtils;
	
	
	@RequestMapping("/serviceList")
	public String serviceList(ModelMap model, HttpServletRequest request){
		
		logger.info("serviceList");
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaService() ) || userPermissions==null){
			return "unAuthorise";
		}

		serviceCmsService.getServiceList(model);
		
		return "serviceList";
		
	}
	@RequestMapping(value = "/mapMediaCategories",  method=RequestMethod.GET)
	//@ResponseBody
	public String getGroups(@RequestParam("serviceId") Integer serviceId,ModelMap model, HttpServletRequest request){
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaGroup() ) || userPermissions==null){
			return "unAuthorise";
		}
		
		serviceCmsService.getGroups(model, serviceId);
		model.addAttribute("srid", serviceId);
		return "selectServiceMediaGroup";
	}
	@RequestMapping(value = "/showServiceGroupSearch",  method=RequestMethod.GET)
	public String showGroupSearch(@RequestParam("srid") Integer serviceId,ModelMap model, HttpServletRequest request){
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaGroup() ) || userPermissions==null){
			return "unAuthorise";
		}
		MediaGroupModel mediaGroupModel = new MediaGroupModel();
		model.addAttribute("searchMediaGroup", mediaGroupModel);
		model.addAttribute("srid", serviceId);
		return "showServiceGroupSearch";
	}
	
	@RequestMapping(value="/showServiceRemMediaGroup",  method=RequestMethod.POST)
	public String searchRemMediaGroup(@ModelAttribute("searchMediaGroup") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request ){
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaGroup() ) || userPermissions==null){
			return "unAuthorise";
		}
		
		serviceCmsService.getGroups(model, mediaGroupModel.getServiceId());
		serviceCmsService.getRemainingMediaGroup(mediaGroupModel, model);
		model.addAttribute("srid", mediaGroupModel.getServiceId());
		return "selectServiceMediaGroup";//MediaContentModelList;
		
	}
	@RequestMapping(value="/saveServiceMappedMediaGroup",  method=RequestMethod.POST)
	public String saveMappedMediaGroup(@ModelAttribute("remMediaList") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaGroup() ) || userPermissions==null){
			return "unAuthorise";
		}
		
		if(mediaGroupModel != null && mediaGroupModel.getSelectedMediaGroup() != null && mediaGroupModel.getSelectedMediaGroup().length > 0){
		try {
			serviceCmsService.addMediaGroup(mediaGroupModel);
			message = "Media Group mapping success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media group mapping failed";
		}
		}
		serviceCmsService.getServiceList(model);
		model.addAttribute("message",message);
		return "serviceList";
	}
	
	@RequestMapping(value="/remAddOrderServiceMediaGroup",  method=RequestMethod.POST)
	public String remAddOrderMediaGroup(@ModelAttribute("mediaGroupList") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaService() ) || userPermissions==null){
			return "unAuthorise";
		}
		
		if(mediaGroupModel != null && mediaGroupModel.getSelectedMediaGroup() != null && mediaGroupModel.getSelectedMediaGroup().length > 0){
		try {
			serviceCmsService.remAddOrderMediaGroup(mediaGroupModel);
			message = "Media Group order/removal success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media Group order/removal failed";
		}
		}
		serviceCmsService.getGroups(model, mediaGroupModel.getServiceId());
		model.addAttribute("srid", mediaGroupModel.getServiceId());
		model.addAttribute("message",message);
		return "selectServiceMediaGroup";
	}
	/*@RequestMapping(value="/remAddOrderMediaGroup",  method=RequestMethod.POST)
	public String remAddOrderMediaGroup(@ModelAttribute("mediaGroupList") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model){
		String message = null;
			//System.out.println("mm"+mediaModel.getSelectedMedia().length);
		if(mediaGroupModel != null && mediaGroupModel.getSelectedMediaGroup() != null && mediaGroupModel.getSelectedMediaGroup().length > 0){
		try {
			serviceCmsService.remAddOrderMediaGroup(mediaGroupModel);
			message = "Media Group order/removal success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media Group order/removal failed";
		}
		}
		serviceCmsService.getGroups(model, mediaGroupModel.getParentmgId());
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
			serviceCmsService.addMediaGroup(mediaGroupModel);
			message = "Media Group mapping success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "Media group mapping failed";
		}
		}
		serviceCmsService.listGroup(model);
		model.addAttribute("message",message);
		return "mediaGroupList";
	}
	showServiceRemMediaGroup
	@RequestMapping(value="/showRemMediaGroup",  method=RequestMethod.POST)
	public String searchRemMediaGroup(@ModelAttribute("searchMediaGroup") MediaGroupModel mediaGroupModel,
			BindingResult result, SessionStatus status,ModelMap model){
		serviceCmsService.getGroups(model, mediaGroupModel.getParentmgId());
		serviceCmsService.getRemainingMediaGroup(mediaGroupModel, model);
		
		return "selectMediaGroup";//MediaContentModelList;
		
	}
	
	public String showGroupSearch(@RequestParam("mgid") Integer mediaGroupId,ModelMap model){
		MediaGroupModel mediaGroupModel = new MediaGroupModel();
		model.addAttribute("searchMediaGroup", mediaGroupModel);
		model.addAttribute("mgid", mediaGroupId);
		return "showGroupSearch";
	}*/
	
}
