
package com.sumadga.upload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.sumadga.dto.Media;
import com.sumadga.dto.UserPermissions;
import com.sumadga.utils.MediaUtils;



@Controller
public class MediaUploadController {

	private static final Logger logger = Logger.getLogger(MediaUploadController.class);
	//UserDetails userDetails = currentUserDetails();
	
	@Autowired
	MediaUploadService mediaUploadService;
	
	@Autowired
	MediaUtils mediaUtils;
	
	@RequestMapping("/show")
	public void show(){
		
		logger.info("log4j");
	}
	
	@RequestMapping("/welcome")
	public String home(ModelMap model,HttpServletRequest request){
		
		logger.info("welcome");
		
		mediaUtils.getUserPermission(request);
		
		return "welcome";
		
	}
	
	@RequestMapping("/upload")
	public String upload(ModelMap model,HttpServletRequest request){
		
		logger.info("upload");
		
		mediaUtils.getUserPermission(request);
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
		
		mediaUploadService.upload(model);
		
		return "uploadFile";
		
	}
	@RequestMapping("/appUpload")
	public String appUpload(ModelMap model,HttpServletRequest request){
		
		
		logger.info("upload");
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isGameUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
		mediaUploadService.uploadApp(model);
		
		return "uploadApp";
		
	}
	@RequestMapping(value="/saveapp",  method=RequestMethod.POST)
	public String saveapp(@ModelAttribute("uploadFile") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request){
		
		String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isGameUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
		
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
			UserDetails userDetails = currentUserDetails();
			mediaUploadService.searchMedia(model,null,userDetails.getUsername());
			mediaUploadService.search(model);
			
			return "showSearch";
			//return showSearch(model);
		}
		
	}
	@RequestMapping("/editMedia")
	public String upload(@RequestParam("mediaId") Integer mediaId,ModelMap model, HttpServletRequest request){
		
		logger.info("Edit Media");
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
		mediaUploadService.edit( model,mediaId);
		
		return "uploadFile";
		
	}
	
	@RequestMapping("/showMediaContent")
	public String showMediaFile(@RequestParam("mediaId") Integer mediaId,ModelMap model, HttpServletRequest request){
		
		logger.info("show Media Content ");
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
		mediaUploadService.showMediaContent( model,mediaId);
		
		return "mediaContent";
		
	}
		
	@RequestMapping(value = "/getFiles",  method=RequestMethod.GET)
	//@ResponseBody
	public String getFiles(@RequestParam("mediaTypeId") Integer mediaTypeId,ModelMap model, HttpServletRequest request){
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
		mediaUploadService.getFiles(model, mediaTypeId);
		
		return "showFile";//MediaContentModelList;
		
	}
	
	@RequestMapping(value="/save",  method=RequestMethod.POST)
	public String save(@ModelAttribute("uploadFile") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request){
	
		{
			
		}
        String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaUpload() ) || userPermissions==null){
			return "unAuthorise";
		}
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
			UserDetails userDetails = currentUserDetails();
			mediaUploadService.searchMedia(model,null,userDetails.getUsername());
			mediaUploadService.search(model);
			
			return "showSearch";
			//return showSearch(model);
		}
		
	}
	
	@RequestMapping("/showSearch")
	public String showSearch(ModelMap model, HttpServletRequest request){
		
		logger.info("upload");
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaSearch() ) || userPermissions==null){
			return "unAuthorise";
		}
		
		UserDetails userDetails = currentUserDetails();
		mediaUploadService.searchMedia(model,null,userDetails.getUsername());
		mediaUploadService.search(model);
		
		return "showSearch";
		
	}
	@RequestMapping(value="/searchMedia",  method=RequestMethod.POST)
	public String searchMedia(@ModelAttribute("searchMedia") MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request){
	
        String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaSearch() ) || userPermissions==null){
			return "unAuthorise";
		}
		//mediaUploadService.search(model);
		UserDetails userDetails = currentUserDetails();
		mediaUploadService.searchMedia(model,mediaUploadModel, userDetails.getUsername());
		
		return "showSearch";
		//return "searchList";
	}
	@RequestMapping(value="/searchMediaPaging",  method=RequestMethod.GET)
	public String searchMediaPaging(MediaUploadModel mediaUploadModel,
			BindingResult result, SessionStatus status,ModelMap model, HttpServletRequest request){
	
        String message = null;		
		logger.info("uploadfile "+mediaUploadModel.toString()+""+mediaUploadModel.getMediaId());
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMediaSearch() ) || userPermissions==null){
			return "unAuthorise";
		}
		//mediaUploadService.search(model);
		UserDetails userDetails = currentUserDetails();
		mediaUploadService.searchMedia(model,mediaUploadModel,userDetails.getUsername());
		
		return "showSearch";
		//return "searchList";
	}
	@RequestMapping(value="/getMedia")
	@ResponseBody
	public Media getMedia(@RequestParam("mediaId") Integer mediaId,ModelMap model){
		
		logger.info("show Media Content ");
		
		
		return mediaUploadService.getMedia(mediaId, model);
		
	}
	public static UserDetails currentUserDetails(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return (UserDetails) (principal instanceof UserDetails ? principal : null);
	    }
	    return null;
	}
	
	
}



