package com.sumadga.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;

import com.sumadga.dao.LanguageDao;
import com.sumadga.dao.MediaContentDao;
import com.sumadga.dao.MediaCycleDao;
import com.sumadga.dao.MediaDao;
import com.sumadga.dao.MediaProcessStateDao;
import com.sumadga.dao.MediaSpecificationDao;
import com.sumadga.dao.MediaTagDao;
import com.sumadga.dao.MediaTypeDao;
import com.sumadga.dao.TagDao;
import com.sumadga.dto.Media;
import com.sumadga.dto.MediaContent;
import com.sumadga.dto.MediaSpecification;
import com.sumadga.dto.MediaTag;
import com.sumadga.dto.Tag;
import com.sumadga.mediagroup.MediaModel;
import com.sumadga.utils.ApplicationProperties;
import com.sumadga.utils.MediaUtils;


@Component
public class MediaUploadService {
	
	private static final Logger logger = Logger.getLogger(MediaUploadService.class);
	
	@Autowired
	MediaUtils mediaUtils;
	
	@Autowired
	MediaSpecificationDao mediaSpecificationDao;
	
	@Autowired
	MediaDao  mediaDao;
	
	@Autowired
	MediaTypeDao mediaTypeDao;
	
	@Autowired
	MediaCycleDao mediaCycleDao;
	
	@Autowired
	MediaContentDao mediaContentDao;
	
	@Autowired
	LanguageDao languageDao;
	
	@Autowired
	MediaProcessStateDao mediaProcessStateDao;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	MediaTagDao mediaTagDao;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public void upload(ModelMap model){
		
		logger.info("upload service");
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		
		
        MediaUploadModel mediaUploadModel = new MediaUploadModel();
        
		model.addAttribute("uploadFile", mediaUploadModel);
		
	}
	
	public List<MediaContentModel> getFiles(ModelMap model,Integer mediaTypeId){

		logger.info("upload service");
		logger.info("contentTypeId "+mediaTypeId);
		List<MediaSpecification> mediaSpecificationList = null;
		List<MediaContentModel> mediaContentModelList = new ArrayList<MediaContentModel>();

		try{
			mediaSpecificationList=mediaSpecificationDao.
				findByProperty("mediaType", mediaTypeId);

            int i=0;
			for (MediaSpecification mediaSpecification : mediaSpecificationList) {
				MediaContentModel mediaContentModel = new MediaContentModel();

				mediaContentModel.setBitRate(mediaSpecification.getBitrate());
				mediaContentModel.setWidth(mediaSpecification.getWidth());
				mediaContentModel.setHeight(mediaSpecification.getHeight());
				mediaContentModel.setMimeType(mediaSpecification.getMimeType().getMimeType());
				mediaContentModel.setPurpose(mediaSpecification.getMediaContentPurpos().getMediaContentPurpose());
				if(mediaSpecification.getMimeType().getMimeType().contains("image"))
				mediaContentModel.setLabel(mediaContentModel.getWidth()+
						"x"+mediaContentModel.getHeight()+" size "+
						mediaSpecification.getMimeType().getMediaExtension()+ " image");
				else
					mediaContentModel.setLabel(mediaContentModel.getWidth()+
							"x"+mediaContentModel.getHeight()+" size "+
							mediaSpecification.getBitrate()+ " bitrate Video");
				/*mediaContentModel.setIsLocal(mediaSpecification.get);*/
				mediaContentModel.setMediaSpecificationId(mediaSpecification.getMediaSpecificationId());
				mediaContentModel.setId(i);
				mediaContentModelList.add(mediaContentModel);
				    i=i+1;	
			} 
				
			
			MediaUploadModel mediaUploadModel = new MediaUploadModel();
			mediaUploadModel.setMediaContentModelList(mediaContentModelList);
		
			model.addAttribute("uploadFile",mediaUploadModel);
		//model.addAttribute("MediaContentModelList",mediaContentModelList);
		//Map<String, Object> model
			
		}catch (Exception e) {
			logger.error("Exception while finding MediaSpec ",e);
		}
		
		return mediaContentModelList;

	}
	
	public void validate(Object object, Errors errors) {
	
		
		
		
	}
	
public void saveUpload(MediaUploadModel mediaUploadModel) throws Exception{
		
		boolean offlineConversion = true;
		logger.info(" MediaTypeId "+mediaUploadModel.getMediaTypeId());
		logger.info(" MediaCycleId "+mediaUploadModel.getMediaCycleId());
		logger.info(" LanguageId "+mediaUploadModel.getLanguageId());
		logger.info(" MediaName "+mediaUploadModel.getMediaName());
		logger.info(" MediaTitle "+mediaUploadModel.getMediaTitle());
		logger.info(" description "+mediaUploadModel.getDescription());
		logger.info(" Start Time "+mediaUploadModel.getMediaStartTime());
		logger.info(" End time "+mediaUploadModel.getMediaEndTime());
		
		Media media = null;
		if(mediaUploadModel.getMediaId() == null)
			media=new Media();
		else
			media=mediaDao.findById(mediaUploadModel.getMediaId());
		
		
		if(offlineConversion && mediaUploadModel.getMediaId()==null)
			media.setMediaCycle(mediaCycleDao.findById(2));
		else
			media.setMediaCycle(mediaCycleDao.findById(mediaUploadModel.getMediaCycleId()));
		
		media.setMediaType(mediaTypeDao.findById(mediaUploadModel.getMediaTypeId()));
		media.setDescription(mediaUploadModel.getDescription());
		media.setLanguage(languageDao.findById(mediaUploadModel.getLanguageId()));
		media.setMediaTitle(mediaUploadModel.getMediaTitle());
		media.setMediaName(mediaUploadModel.getMediaName());
		
		Date fromPublishDate=null,toPublishDate=null;
		fromPublishDate = mediaUploadModel.getMediaStartTime();
		toPublishDate = mediaUploadModel.getMediaEndTime();
		
		if(fromPublishDate == null)
			fromPublishDate = mediaUtils.parseDate("01-01-1970");
		
		if(toPublishDate == null)
			toPublishDate = mediaUtils.parseDate("31-12-9999");
			
		media.setMediaStartTime(fromPublishDate);
		media.setMediaEndTime(toPublishDate);
		
		
		media.setMediaProcessState(mediaProcessStateDao.findById(1));//not started
		if(mediaUploadModel.getMediaId() == null)
		{
			media.setMediaProcessState(mediaProcessStateDao.findById(2));//not started
		   mediaDao.save(media);
		}
		else
			 mediaDao.update(media);
		
	
		  insertMediaTags(media, mediaUploadModel.getTags());
		  
		  /*String captureDesc="Inserted New contentItem id is "+contentItem.getContentItemId();
			if(contentItemId!=null)
				captureDesc="Updated contentItem id is "+contentItem.getContentItemId();
			
			cmsUtil.insertCaptureUserActions(contentItem.getContentProvider(), request, captureDesc);*/
			
		
	   /*getting content builds*/	
	  List<MediaContentModel> mediaContentModelList = mediaUploadModel.getMediaContentModelList();
	  
	  for (MediaContentModel mediaContentModel : mediaContentModelList) {
		    
		  /*getting uploaded files to save and convert*/
		  try{
		  saveMediaFiles(media, mediaContentModel);
		  }catch (Exception e) {
			logger.error("Exception while saving contentItemFiles",e);  
			throw new Exception("Upload Failed");
		}
		}//for	
		
	 
	}


  	public void saveMediaFiles(Media media,MediaContentModel mediaContentModel){
  		MediaContent mediaContent=new MediaContent();
  		
  		try{
  		
  		mediaContent.setMedia(media);
  		mediaContent.setMediaSpecification(mediaSpecificationDao.findById(mediaContentModel.getMediaSpecificationId()));
  		
  		mediaContent.setMd5(mediaUtils.getMd5(mediaContentModel.getFile()));
  		
  		
  		String fileName=mediaUtils.renameMediaContentFile(mediaContentModel.getFile().getOriginalFilename(),
  				mediaContentModel.getWidth(), mediaContentModel.getHeight(), mediaContentModel.getBitRate(),
  				mediaContentModel.getPurpose());
  		
  		String relativePath=mediaUtils.getRelativePath(media.getMediaId(), fileName);
  		
  		String completePath=mediaUtils.getCompleteFilePath(media.getMediaId(), fileName);
  		
  		File fileToCreate = new File(completePath);
		File fileToCreateDir=new File(fileToCreate.getParent());
		
		if(!fileToCreateDir.exists())
			fileToCreateDir.mkdirs();
		
		//FileUtils.copyFile((File)contentFileBean.getFile(), fileToCreate);
		mediaContentModel.getFile().transferTo(fileToCreate);
  		
  		mediaContent.setStoragePath(relativePath);
  		
  		mediaContentDao.save(mediaContent);
  		}catch(Throwable e)
  		{
  			logger.error("Exception catch while saving media content", e);
  		}
  	}
	
	public void insertMediaTags(Media media,String tags){
		List<Tag> tagList=new ArrayList<Tag>();
		String[] tagArray=tags.split(",");
		for (int i = 0; i < tagArray.length; i++) {
			List<Tag> availabletags=tagDao.findByProperty("tagName", tagArray[0]);
			if(availabletags.isEmpty())
			{
				Tag tag=new Tag();
				tag.setTagName(tagArray[i]);
				
				tagDao.save(tag);
				tagList.add(tag);
			}else{
				tagList.addAll(availabletags);
			}
		}
		
		for (Tag tag : tagList) {
			MediaTag mediaTag=new MediaTag();
			mediaTag.setMedia(media);
			mediaTag.setTag(tag);
			mediaTagDao.save(mediaTag);
		}
		
	}
	public void search(ModelMap model){
		
		logger.info("upload service");
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		
		
        MediaUploadModel mediaUploadModel = new MediaUploadModel();
        
		model.addAttribute("searchMedia", mediaUploadModel);
		
	}
	public void searchMedia(ModelMap model,MediaUploadModel mediaUploadModel) {
		// TODO Auto-generated method stub
		/*model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		model.addAttribute("searchMedia", mediaUploadModel);*/
		List<Media> mediaModels = mediaDao.search(mediaUploadModel);
		/*List<MediaModel> mediaModels2 = new ArrayList<MediaModel>();
		for (Media media : mediaModels) {
			MediaModel mediaModel = new MediaModel();
			mediaModel.setMediaName(media.getMediaName());
			mediaModel.setMediaTitle(media.getMediaTitle());
			mediaModel.setDescription(media.getDescription());
			mediaModel.setMediaId(media.getMediaId());
			mediaModels2.add(mediaModel);
		}*/
		model.addAttribute("mediaList",mediaModels);
	}
	
}