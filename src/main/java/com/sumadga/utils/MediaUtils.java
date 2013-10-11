package com.sumadga.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumadga.dao.LanguageDao;
import com.sumadga.dao.MediaCycleDao;
import com.sumadga.dao.MediaSpecificationDao;
import com.sumadga.dao.MediaTypeDao;
import com.sumadga.dto.Language;
import com.sumadga.dto.MediaCycle;
import com.sumadga.dto.MediaType;

@Component
public class MediaUtils {
	private static final Logger logger = Logger.getLogger(MediaUtils.class);
	
    @Autowired
    LanguageDao languageDao;
    
    @Autowired
    MediaTypeDao mediaTypeDao;
    
    @Autowired
    MediaCycleDao mediaCycleDao;
    
    @Autowired
    MediaSpecificationDao mediaSpecificationDao;
    
    public List<Language> getLanguageList(){
    	return languageDao.findAll();
    }
    
    public List<MediaCycle> getMediaCycleList(){
    	return mediaCycleDao.findAll();
    }
    
    public List<MediaType> getMediaTypeList(){
    	return mediaTypeDao.findAll();
    }
		
}
