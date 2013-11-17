package com.sumadga.mis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.sumadga.dao.MediaDao;
import com.sumadga.dto.Media;
import com.sumadga.utils.MediaUtils;

@Component
public class MisService {
	
	@Autowired
	MediaUtils mediaUtils;
	 
	@Autowired
	MediaDao mediaDao;
	
	public void showMis(ModelMap model){
		
		model.addAttribute("RevenueList", mediaDao.getMisModel(mediaUtils.getMediaProviderList()));
	}

}
