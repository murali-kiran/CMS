package com.sumadga.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;

import com.sumadga.dao.LanguageDao;
import com.sumadga.dao.MediaAppContentDao;
import com.sumadga.dao.MediaContentDao;
import com.sumadga.dao.MediaCycleDao;
import com.sumadga.dao.MediaDao;
import com.sumadga.dao.MediaProcessStateDao;
import com.sumadga.dao.MediaProviderDao;
import com.sumadga.dao.MediaSpecificationDao;
import com.sumadga.dao.MediaTagDao;
import com.sumadga.dao.MediaTypeDao;
import com.sumadga.dao.TagDao;
import com.sumadga.dto.Media;
import com.sumadga.dto.MediaAppContent;
import com.sumadga.dto.MediaContent;
import com.sumadga.dto.MediaSpecification;
import com.sumadga.dto.MediaTag;
import com.sumadga.dto.MediaType;
import com.sumadga.dto.MimeType;
import com.sumadga.dto.Os;
import com.sumadga.dto.Tag;
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
	MediaProviderDao mediaProviderDao;
	
	@Autowired
	MediaProcessStateDao mediaProcessStateDao;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	MediaTagDao mediaTagDao;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Autowired
	MediaTranscoding mediaTranscoding;
	
	@Autowired
	MediaAppContentDao mediaAppContentDao;
	
	public void upload(ModelMap model){
		
		logger.info("upload service");
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		model.addAttribute("mediaProviderList",mediaUtils.getMediaProviderList());
		
		
        MediaUploadModel mediaUploadModel = new MediaUploadModel();
        
		model.addAttribute("uploadFile", mediaUploadModel);
	}
	public void uploadApp(ModelMap model){
		
		logger.info("upload service");
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		model.addAttribute("mimeTypeList",mediaUtils.getMimeTypeList());
		model.addAttribute("osList",mediaUtils.getOsList());
		
        MediaUploadModel mediaUploadModel = new MediaUploadModel();
        
        MediaContentModel m = new MediaContentModel();
       // m.setHeight(10);
        List<MediaContentModel> mediaContentModelList = new ArrayList<MediaContentModel>();
        mediaContentModelList.add(m);
       // model.addAttribute("mlist",mediaContentModelList);
        
        mediaUploadModel.setMediaContentModelList(mediaContentModelList);
       // mediaUploadModel.setDescription("DEsc");
        model.addAttribute("uploadFile", mediaUploadModel);
        
		/*model.addAttribute("uploadFile", mediaUploadModel);
		List<MediaAppContent> mediaAppContents = new ArrayList<MediaAppContent>();
		mediaAppContents.add(new MediaAppContent());
		model.addAttribute("appList",mediaAppContents);*/
		
	}
	public void showMediaContent(ModelMap model,Integer mediaId){

		
		logger.info("show media content service");
		
		List<MediaContent> mediaContentList = mediaContentDao.findByProperty("media", mediaId);
		model.addAttribute("mediaContentList",mediaContentList);
		model.addAttribute("relativePath",applicationProperties.getMediaAbsolutePath());
        
	
	}
public void edit(ModelMap model,Integer mediaId){
		
		logger.info("upload service");
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		model.addAttribute("languageList",mediaUtils.getLanguageList());
		model.addAttribute("mediaProviderList",mediaUtils.getMediaProviderList());
		
        MediaUploadModel mediaUploadModel = new MediaUploadModel();
        
        Media media=mediaDao.findById(mediaId);
        
        if(media!=null){
        	mediaUploadModel.setMediaId(media.getMediaId());
        	mediaUploadModel.setMediaName(media.getMediaName());
        	mediaUploadModel.setMediaTitle(media.getMediaTitle());
        	mediaUploadModel.setMediaCycleId(media.getMediaCycle().getMediaCycleId());
        	mediaUploadModel.setMediaTypeId(media.getMediaType().getMediaTypeId());
        	mediaUploadModel.setLanguageId(media.getLanguage().getLanguageId());
        	try{
        	SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
        	/*SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");*/
        	/*Date date1 = originalFormat.parse(media.getMediaStartTime().toString());
        	Date date2 = originalFormat.parse(media.getMediaEndTime().toString());*/
        	 mediaUploadModel.setMediaStartTime(dateFormat.format(media.getMediaStartTime()));
        	mediaUploadModel.setMediaEndTime(dateFormat.format(media.getMediaStartTime()));
        	}catch(Exception e){logger.error("error", e);}
        	mediaUploadModel.setDescription(media.getDescription());
        	
        	String tags="";
        	List<MediaTag> mediaTagList=mediaTagDao.findByProperty("media", media.getMediaId());
        	for (MediaTag mediaTag : mediaTagList) {
				tags=tags+mediaTag.getTag().getTagName();
				if(mediaTagList.indexOf(mediaTag) != (mediaTagList.size()-1))
					tags=tags+",";
			}
        	if(mediaTagList!=null && !mediaTagList.isEmpty())
        		mediaUploadModel.setTags(tags);
        }
        
        List<MediaSpecification> mediaSpecificationList = null;
		List<MediaContentModel> mediaContentModelList = new ArrayList<MediaContentModel>();

			mediaSpecificationList=mediaSpecificationDao.
				findByProperty("mediaType", media.getMediaId());

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
			mediaUploadModel.setMediaContentModelList(mediaContentModelList);
        
        model.addAttribute("uploadFile", mediaUploadModel);
        
        
	}
	
	public List<MediaContentModel> getFiles(ModelMap model,Integer mediaTypeId){

		logger.info("upload service");
		logger.info("contentTypeId "+mediaTypeId);
		List<MediaSpecification> mediaSpecificationList = null;
		List<MediaContentModel> mediaContentModelList = new ArrayList<MediaContentModel>();

		try{
			/*mediaSpecificationList=mediaSpecificationDao.
				findByProperty("mediaType", mediaTypeId);*/
			mediaSpecificationList=mediaSpecificationDao.
					findByMediaTypeIdAndIsSource(mediaTypeId, true); // isSource true files

            int i=0;
			for (MediaSpecification mediaSpecification : mediaSpecificationList) {
				MediaContentModel mediaContentModel = new MediaContentModel();

				mediaContentModel.setBitRate(mediaSpecification.getBitrate());
				mediaContentModel.setWidth(mediaSpecification.getWidth());
				mediaContentModel.setHeight(mediaSpecification.getHeight());
				mediaContentModel.setMimeType(mediaSpecification.getMimeType().getMimeType());
				mediaContentModel.setPurpose(mediaSpecification.getMediaContentPurpos().getMediaContentPurpose());
				/*if(mediaSpecification.getMimeType().getMimeType().contains("image"))
				mediaContentModel.setLabel(mediaContentModel.getWidth()+
						"x"+mediaContentModel.getHeight()+" size "+
						mediaSpecification.getMimeType().getMediaExtension()+ " image");
				else
					mediaContentModel.setLabel(mediaContentModel.getWidth()+
							"x"+mediaContentModel.getHeight()+" size "+
							mediaSpecification.getBitrate()+ " bitrate Video");*/
				mediaContentModel.setLabel(
						mediaSpecification.getMediaSpecificationsName());
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
		logger.info(" MediaProviderId "+mediaUploadModel.getMediaProviderId());
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
			media.setMediaCycle(mediaCycleDao.findById(1));
		else
			media.setMediaCycle(mediaCycleDao.findById(mediaUploadModel.getMediaCycleId()));
		
		media.setMediaType(mediaTypeDao.findById(mediaUploadModel.getMediaTypeId()));
		media.setDescription(mediaUploadModel.getDescription());
		media.setLanguage(languageDao.findById(mediaUploadModel.getLanguageId()));
		media.setMediaProvider(mediaProviderDao.findById(mediaUploadModel.getMediaProviderId()));
		media.setMediaTitle(mediaUploadModel.getMediaTitle());
		media.setMediaName(mediaUploadModel.getMediaName());
		
		Date fromPublishDate=null,toPublishDate=null;
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		fromPublishDate = sdf.parse(mediaUploadModel.getMediaStartTime());
		toPublishDate = sdf.parse(mediaUploadModel.getMediaEndTime());
		
		if(fromPublishDate == null)
			fromPublishDate = mediaUtils.parseDate("01-01-1970");
		
		if(toPublishDate == null)
			toPublishDate = mediaUtils.parseDate("31-12-9999");
			
		media.setMediaStartTime(fromPublishDate);
		media.setMediaEndTime(toPublishDate);
		
		
		
		if(mediaUploadModel.getMediaId() == null)
		{
			media.setMediaProcessState(mediaProcessStateDao.findById(1));//not started
		   mediaDao.save(media);
		}
		else{
			 mediaDao.update(media);
		}
		
	
		  insertMediaTags(media, mediaUploadModel.getTags());

		  List<MediaContentModel> mediaContentModelList = mediaUploadModel.getMediaContentModelList();
	  
	  if(mediaContentModelList!=null){
	 
	  for (MediaContentModel mediaContentModel : mediaContentModelList) {
		    
		  /*getting uploaded files to save and convert*/
		  try{
		  saveMediaFiles(media, mediaContentModel);
		  }catch (Exception e) {
			logger.error("Exception while saving contentItemFiles",e);  
			throw new Exception("Upload Failed");
		}
		}//for	
	  
	  transcoding(media);
	  }// mediaContentModelList	
	 
	}
public void saveUploadApp(MediaUploadModel mediaUploadModel) throws Exception{
	
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
		media.setMediaCycle(mediaCycleDao.findById(1));
	else
		media.setMediaCycle(mediaCycleDao.findById(mediaUploadModel.getMediaCycleId()));
	
	media.setMediaType(mediaTypeDao.findById(mediaUploadModel.getMediaTypeId()));
	media.setDescription(mediaUploadModel.getDescription());
	media.setLanguage(languageDao.findById(mediaUploadModel.getLanguageId()));
	media.setMediaTitle(mediaUploadModel.getMediaTitle());
	media.setMediaName(mediaUploadModel.getMediaName());
	
	Date fromPublishDate=null,toPublishDate=null;
	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	fromPublishDate = sdf.parse(mediaUploadModel.getMediaStartTime());
	toPublishDate = sdf.parse(mediaUploadModel.getMediaEndTime());
	
	if(fromPublishDate == null)
		fromPublishDate = mediaUtils.parseDate("01-01-1970");
	
	if(toPublishDate == null)
		toPublishDate = mediaUtils.parseDate("31-12-9999");
		
	media.setMediaStartTime(fromPublishDate);
	media.setMediaEndTime(toPublishDate);
	
	
	
	if(mediaUploadModel.getMediaId() == null)
	{
		media.setMediaProcessState(mediaProcessStateDao.findById(1));//not started
	   mediaDao.save(media);
	}
	else{
		 mediaDao.update(media);
	}
	

//	  insertMediaTags(media, mediaUploadModel.getTags());

	  List<MediaContentModel> mediaContentModelList = mediaUploadModel.getMediaContentModelList();
  
  if(mediaContentModelList!=null){
 
  for (MediaContentModel mediaContentModel : mediaContentModelList) {
	    
	  /*getting uploaded files to save and convert*/
	  try{
		  saveMediaAppFiles(media, mediaContentModel);
	  }catch (Exception e) {
		logger.error("Exception while saving app contentItemFiles",e);  
		throw new Exception("Upload Failed");
	}
	}//for	
  
  transcoding(media);
  }// mediaContentModelList	
 
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
  	public void saveMediaAppFiles(Media media,MediaContentModel mediaContentModel){
  		MediaAppContent mediaAppContent=new MediaAppContent();
  		
  		try{
  		
  			
  		//mediaContent.setMediaSpecification(mediaSpecificationDao.findById(mediaContentModel.getMediaSpecificationId()));
  		
  		//mediaContent.setMd5(mediaUtils.getMd5(mediaContentModel.getFile()));
  		
  		
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
		MimeType mimeType = new MimeType();
		mimeType.setMimeTypeId(Integer.parseInt(mediaContentModel.getMimeType()));
		
		Os os = new Os();
		os.setOsId(mediaContentModel.getOsid());
		
		mediaAppContent.setMimeType(mimeType);
		mediaAppContent.setOs(os);
		mediaAppContent.setMedia(media);
  		mediaAppContent.setStoragePath(relativePath);
  		mediaAppContent.setHeight(mediaContentModel.getHeight());
  		mediaAppContent.setWidth(mediaContentModel.getWidth());
  		mediaAppContent.setPurpose(mediaContentModel.getPurpose());
  		mediaAppContent.setDescription(mediaContentModel.getDuration());
  		
  		mediaAppContentDao.save(mediaAppContent);
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
	
	public void transcoding(Media media){
		try{
		MediaType mediaType=media.getMediaType();
		
		media.setMediaProcessState(mediaProcessStateDao.findById(2));//not started
		
		mediaDao.update(media);
		/*List<MediaSpecification> mediaSpecificationList=new ArrayList<MediaSpecification>();*/
		
		
			
			
			List<MediaContent> mediaContentList=mediaContentDao.findByProperty("media", media.getMediaId());
			for (MediaContent mediaContent : mediaContentList) {
				List<MediaSpecification> autoGeneratedList=mediaSpecificationDao.findByMediaTypeIdAndIsSourceAndParentSpecId(
						mediaType.getMediaTypeId(), false, mediaContent.getMediaSpecification().getMediaSpecificationId());
				
				for (MediaSpecification mediaSpecification : autoGeneratedList) {
					try{
					String command=mediaSpecification.getTranscodingCommand();
					command=command.replaceAll("@SOURCE@", applicationProperties.getMediaCompletePath()+mediaContent.getStoragePath());
					
					File f=new File(applicationProperties.getMediaCompletePath()+mediaContent.getStoragePath());
					
					if(f.exists())
						logger.info("Source file exist ");
					else
						logger.info("Source file not exist ");
					String newFileName=mediaUtils.renameMediaContentFile(media.getMediaId()+"."+mediaSpecification.getMimeType().getMediaExtension(),
							mediaSpecification.getWidth(), mediaSpecification.getHeight(),
							mediaSpecification.getBitrate(), mediaSpecification.getMediaContentPurpos().getMediaContentPurpose());
					
					String relativePath=mediaUtils.getRelativePath(media.getMediaId(), newFileName);
			  		
			  		String completePath=mediaUtils.getCompleteFilePath(media.getMediaId(), newFileName);
					
					command=command.replaceAll("@DESTINATION@", completePath);
					
					Boolean b=mediaTranscoding.transCoding(command.split("##"));
					if(b)
					{
						MediaContent mediaContentAuto=new MediaContent();
						mediaContentAuto.setMedia(media);
						mediaContentAuto.setMediaSpecification(mediaSpecification);
				  		
				  		/*File */f=new File(completePath);
				  		mediaContentAuto.setMd5(mediaUtils.getMd5(f));
				  		mediaContentAuto.setStoragePath(relativePath);
				  		mediaContentDao.save(mediaContentAuto);
					}
					}catch(Exception e){logger.error("error while conversion",e);}
					
				}
			}
			
			media.setMediaProcessState(mediaProcessStateDao.findById(3));
			media.setMediaCycle(mediaCycleDao.findById(2));
			mediaDao.update(media);
		
		}catch(Exception e){logger.error("error while conversion",e);}
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
		List<Media> mediaModels = mediaDao.search(mediaUploadModel);
		model.addAttribute("mediaList",mediaModels);
		
		model.addAttribute("mediaTypeList",mediaUtils.getMediaTypeList());
		model.addAttribute("mediaCycleList",mediaUtils.getMediaCycleList());
		//model.addAttribute("languageList",mediaUtils.getLanguageList());
		model.addAttribute("searchMedia", mediaUploadModel);
	}
	
}
