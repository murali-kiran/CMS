package com.sumadga.upload;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.sumadga.utils.MediaUtils;


@Component
public class MediaUploadService {
	
	private static final Logger logger = Logger.getLogger(MediaUploadService.class);
	
	@Autowired
	MediaUtils mediaUtils;
	
	public void upload(ModelMap model){
		
		logger.info("upload service");
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		
		
        MediaUploadModel mediaUploadModel = new MediaUploadModel();
        
		model.addAttribute("uploadFile", mediaUploadModel);
		
	}

}