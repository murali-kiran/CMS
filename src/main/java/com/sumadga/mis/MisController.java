
package com.sumadga.mis;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sumadga.dto.UserPermissions;
import com.sumadga.utils.MediaUtils;



@Controller
public class MisController {

	private static final Logger logger = Logger.getLogger(MisController.class);
	
	
	@Autowired
	MisService misService;
	
	@Autowired
	MediaUtils mediaUtils;
	
	@RequestMapping("/mis/show")
	public String showSearch(ModelMap model, HttpServletRequest request){
		
		logger.info("show MIS");
		
		UserPermissions userPermissions=mediaUtils.getUserPermit(request);
		if(( userPermissions!=null && !userPermissions.isMis() ) || userPermissions==null){
			return "unAuthorise";
		}

		misService.showMis(model);
		
		return "showMis";
		
	}
	
	@RequestMapping("/mis/sample")
	@ResponseBody
	public UserPermissions showSearchSample(ModelMap model, HttpServletRequest request){
		
		logger.info("show MIS");
		
		return mediaUtils.getUserPermit(request);
		/*if(( userPermissions!=null && !userPermissions.isMis() ) || userPermissions==null){
			return "unAuthorise";
		}

		misService.showMis(model);
		
		return "showMis";*/
		
	}
}


