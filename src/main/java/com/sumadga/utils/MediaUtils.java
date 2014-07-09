package com.sumadga.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sumadga.dao.LanguageDao;
import com.sumadga.dao.MediaCycleDao;
import com.sumadga.dao.MediaDao;
import com.sumadga.dao.MediaProviderDao;
import com.sumadga.dao.MediaSpecificationDao;
import com.sumadga.dao.MediaTypeDao;
import com.sumadga.dao.MimeTypeDao;
import com.sumadga.dao.UserPermissionsDao;
import com.sumadga.dto.Language;
import com.sumadga.dto.Media;
import com.sumadga.dto.MediaCycle;
import com.sumadga.dto.MediaProvider;
import com.sumadga.dto.MediaType;
import com.sumadga.dto.MimeType;
import com.sumadga.dto.Os;
import com.sumadga.dto.UserPermissions;
import com.sumadga.mediagroup.MediaModel;

@Component
public class MediaUtils {
	private static final Logger logger = Logger.getLogger(MediaUtils.class);
	
    @Autowired
    LanguageDao languageDao;
    
    @Autowired
    MediaTypeDao mediaTypeDao;
    
    @Autowired
    MediaDao mediaDao;
    
    @Autowired
    MediaCycleDao mediaCycleDao;
    
    @Autowired
    MediaSpecificationDao mediaSpecificationDao;
    
    @Autowired
    MimeTypeDao mimeTypeDao;
    @Autowired
    MediaProviderDao mediaProviderDao;
    
    
    @Autowired
    ApplicationProperties applicationProperties;
    
    @Autowired
    UserPermissionsDao userPermissionsDao;
    
    
    public List<Language> getLanguageList(){
    	return languageDao.findAll();
    }
    
    public List<MediaCycle> getMediaCycleList(){
    	return mediaCycleDao.findAll();
    }
    
    public List<MediaType> getMediaTypeList(){
    	return mediaTypeDao.findAll();
    }
    
    public List<MimeType> getMimeTypeList(){
    	return mimeTypeDao.findAll();
    }
    public List<Os> getOsList(){
    	return mimeTypeDao.findAllOs();
    }
    public List<MediaProvider> getMediaProviderList(){
    	
    	try{
	    	SecurityContext context=SecurityContextHolder.getContext();
	    	Authentication authentication=context.getAuthentication();
	    	if(authentication !=null){
	    		String userName=authentication.getName();
	    		logger.info("User name "+userName);
	    		return mediaProviderDao.findByUserName(userName);
	    	}
    	}catch(Exception e){
    		logger.error("Exception caught : ",e);
    	}
    	
    	return mediaProviderDao.findAll();
    }
    
public void getUserPermission(HttpServletRequest request){
    	
    	try{
	    	SecurityContext context=SecurityContextHolder.getContext();
	    	Authentication authentication=context.getAuthentication();
	    	if(authentication !=null){
	    		String userName=authentication.getName();
	    		logger.info("User name "+userName);
	    		if(request.getSession().getAttribute("userPermissions")==null)
	    		{
	    			List<UserPermissions> permissionsList=userPermissionsDao.findByProperty("user", userName);
	    			if(!permissionsList.isEmpty())
	    				request.getSession().setAttribute("userPermissions", permissionsList.get(0));
	    		}
	    	}
    	}catch(Exception e){
    		logger.error("Exception caught : ",e);
    	}
    	
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
	public String getMd5(File file){
		String md5="";
		InputStream is=null;
		try{
			logger.info("in getCheckSum method ");
			MessageDigest md = MessageDigest.getInstance("MD5");
			is = new FileInputStream(file);

			int length=512;
			byte bt[] = new byte[length];
			int read=0;

			while((read=is.read(bt, 0, length)) != -1) {
				md.update(bt, 0, read);
			}
			md5 = new BigInteger(1,md.digest()).toString(16);
			if(md5.length() < 32)
			{
				int l=32-md5.length();
				for (int i = 0; i < l; i++) {
					md5="0"+md5;
				}
			}
			logger.info("check sum from file is "+md5);
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
		return md5;
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
	 public List<Media> getMediaList(){
	    	return mediaDao.findAll();
	    }
	 
	 public List<MediaModel> getMediaModelList(){
		 
	    	List<Media> medias = mediaDao.findAll();
	    	List<MediaModel> mediaModels = new ArrayList<MediaModel>();
	    	for (Media media : medias) {
	    		MediaModel mediaModel = new MediaModel();
	    		mediaModel.setMediaId(media.getMediaId());
	    		mediaModel.setMediaName(media.getMediaName());
	    		mediaModel.setMediaTitle(media.getMediaTitle());
				mediaModels.add(mediaModel);
			}
	    	return mediaModels;
	    }
}
