package com.sumadga.utils;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Autowired
    ApplicationProperties applicationProperties;
    
    public List<Language> getLanguageList(){
    	return languageDao.findAll();
    }
    
    public List<MediaCycle> getMediaCycleList(){
    	return mediaCycleDao.findAll();
    }
    
    public List<MediaType> getMediaTypeList(){
    	return mediaTypeDao.findAll();
    }
    public java.util.Date parseDate(String dateString){ 
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date=null;
		try{
			date=dateFormat.parse(dateString);
		}catch (Exception e) {
			logger.error("Error :"+e.toString());
		}
		return date;
	}	
	public String getMd5(MultipartFile checkSumFIle){
		String MD5="";
		InputStream is=null;
		try{
			logger.info("in getCheckSum method ");
			MessageDigest md = MessageDigest.getInstance("MD5");
			is = checkSumFIle.getInputStream();

			int length=512;
			byte bt[] = new byte[length];
			int read=0;

			while((read=is.read(bt, 0, length)) != -1) {
				md.update(bt, 0, read);
			}
			MD5 = new BigInteger(1,md.digest()).toString(16);
			if(MD5.length() < 32)
			{
				int l=32-MD5.length();
				for (int i = 0; i < l; i++) {
					MD5="0"+MD5;
				}
			}
			logger.info("check sum from file is "+MD5);
		}catch (Exception e) {
			logger.info("Error "+e.toString());
		}finally{
			try{
				if(is!=null)
					is.close();
			}catch (Exception e) {
				logger.info("Error "+e.toString());
			}
		}
		return MD5;
	}
	private Integer getFolderNumber(Integer mediaId) {
		return (int) (mediaId / applicationProperties.getMaxSubFolders());
	}
	
	public String getRelativePath(Integer mediaId, String fileName) {
		return  getFolderNumber(mediaId)
				+ File.separator + mediaId + File.separator + fileName;
	}

	public String getCompleteFilePath(Integer mediaId,
			String fileName) {
		return applicationProperties.getMediaCompletePath() + File.separator
				+ getRelativePath(mediaId, fileName);
	}

	public String renameMediaContentFile(String fileName,Integer width,Integer height,Integer bitrate,String purpose){

		StringBuffer newName=new StringBuffer();

		if(purpose!=null)
			newName.append("_"+purpose);
		if(width!=null)
			newName.append("_"+width);
		if(height!=null)
			newName.append("_"+height);
		if(bitrate!=null)
			newName.append("_"+bitrate+"k");

		int dotIndex = fileName.lastIndexOf(".");


		if (dotIndex == -1)
		{
			return fileName + newName.toString();
		}
		else
			return fileName.substring(0, dotIndex )+ newName.toString()+ fileName.substring(dotIndex );	

	} 
}
