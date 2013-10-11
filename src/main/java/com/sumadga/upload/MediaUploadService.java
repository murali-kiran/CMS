package com.sumadga.upload;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.sumadga.dao.MediaSpecificationDao;
import com.sumadga.dto.MediaSpecification;
import com.sumadga.utils.MediaUtils;


@Component
public class MediaUploadService {
	
	private static final Logger logger = Logger.getLogger(MediaUploadService.class);
	
	@Autowired
	MediaUtils mediaUtils;
	
	@Autowired
	MediaSpecificationDao mediaSpecificationDao;
	
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
				mediaContentModelList.add(mediaContentModel);
				    i=i+1;	
			} 
				
			
			MediaUploadModel mediaUploadModel = new MediaUploadModel();
			mediaUploadModel.setMediaContentModelList(mediaContentModelList);
		
			model.addAttribute("uploadFile",mediaUploadModel);
		//model.addAttribute("MediaContentModelList",mediaContentModelList);
		//Map<String, Object> model
			
		}catch (Exception e) {
			logger.error("Exception while finding ContentItemFileSpecs ");
		}
		
		return mediaContentModelList;

	}
}