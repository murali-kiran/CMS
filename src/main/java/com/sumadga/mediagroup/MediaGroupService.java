package com.sumadga.mediagroup;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.sumadga.dao.MediaContentDao;
import com.sumadga.dao.MediaDao;
import com.sumadga.dao.MediaGroupDao;
import com.sumadga.dao.MediaGroupMediaDao;
import com.sumadga.dao.MediaSpecificationDao;
import com.sumadga.dto.Media;
import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.dto.MediaSpecification;
import com.sumadga.utils.MediaUtils;


@Component
public class MediaGroupService {
	
	private static final Logger logger = Logger.getLogger(MediaGroupService.class);
	
	@Autowired
	MediaUtils mediaUtils;
	@Autowired
	MediaGroupDao mediaGroupDao;
	@Autowired
	MediaDao mediaDao;
	@Autowired
	MediaGroupMediaDao mediaGroupMediaDao;
	public void createGroup(ModelMap model){
		
		logger.info("upload service");
		
		model.addAttribute("mediaList",mediaUtils.getMediaList());
				
        MediaGroupModel mediaGroupModel = new MediaGroupModel();
        
		model.addAttribute("addGroup", mediaGroupModel);
		
	}


	public void validate(MediaGroupModel mediaGroupModel, BindingResult result) {
		// TODO Auto-generated method stub
		
	}


	public void saveGroup(MediaGroupModel mediaGroupModel)throws Exception {
		// TODO Auto-generated method stub
		try {
			MediaGroup mediaGroup = new MediaGroup();
			mediaGroup.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			mediaGroup.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			mediaGroup.setMediaGroupName(mediaGroupModel.getMediaGroupName());
			mediaGroup.setMediaGroupTitle(mediaGroupModel.getMediaGroupTitle());
			mediaGroup.setMediaGroupDescription(mediaGroupModel.getDescription());
			mediaGroup.setMediaGroupPreviewId(mediaGroupModel.getMediaId());
			mediaGroupDao.save(mediaGroup);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public void listGroup(ModelMap model) {
		// TODO Auto-generated method stub
		//List<MediaGroupModel> mediaGroups = new ArrayList<MediaGroupModel>();
		List<MediaGroup> mediaGroupList = mediaGroupDao.findAll();
		System.out.println("size:"+mediaGroupList.size());
		model.addAttribute("groupList",mediaGroupList);
	}
	public void getMedia(ModelMap model, Integer mediaGroupId) {
		// TODO Auto-generated method stub
		try {
			List<Media> mediaList1 = mediaDao.findAll();
			List<MediaModel> mediaList = new ArrayList<MediaModel>();
			//List<MediaGroupMedia> mediaGroupMedias = new ArrayList<MediaGroupMedia>();
			List<MediaGroupMedia> mediaGroupMedias = mediaGroupMediaDao.findByProperty("mediaGroup", mediaGroupId);
			//BeanUtils.copyProperties(mediaList, mediaList1);
			for (Media media : mediaList1) {
				MediaModel mediaModel2 = new MediaModel();
				mediaModel2.setDescription(media.getDescription());
				mediaModel2.setMediaId(media.getMediaId());
				mediaModel2.setMediaName(media.getMediaName());
				mediaModel2.setMediaTitle(media.getMediaTitle());
				mediaModel2.setMediaCycle(media.getMediaCycle());
				mediaModel2.setMgid(mediaGroupId);
				for (MediaGroupMedia mediaGroupMedia : mediaGroupMedias) {
					if(media.getMediaId() == mediaGroupMedia.getMedia().getMediaId()){
						mediaModel2.setCheckStatus(true);
					}
				}
				mediaList.add(mediaModel2);
			}
			model.addAttribute("mediaList", mediaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void mapMedia(MediaModel mediaModel)throws Exception {
		// TODO Auto-generated method stub
		try {
			Integer mediaGroupId = mediaModel.getMgid();
			String[] string = mediaModel.getSelectedMedia();
			//List<Integer> selectedMediaIds = new ArrayList<Integer>();
			List<MediaGroupMedia> mediaGroupMedias = mediaGroupMediaDao.findMappedMedia(mediaGroupId);
			int i=0;
			for (String string2 : string) {
				List<MediaGroupMedia> mediaGroupMediaList = mediaGroupMediaDao.findMappedMedia(Integer.parseInt(string2), mediaGroupId);
				MediaGroupMedia mediaGroupMedia = null; 
				if(mediaGroupMediaList != null && mediaGroupMediaList.size()>0)
					mediaGroupMedia = mediaGroupMediaList.get(0);
				if(mediaGroupMedia == null){
					mediaGroupMedia = new MediaGroupMedia();
					mediaGroupMedia.setMedia(mediaDao.findById(Integer.parseInt(string2)));
					mediaGroupMedia.setMediaGroup(mediaGroupDao.findById(mediaGroupId));
					mediaGroupMedia.setMediaOrder(i);
					mediaGroupMediaDao.save(mediaGroupMedia);
				}
				else{
					mediaGroupMedia.setModifiedTime(new Timestamp(System.currentTimeMillis()));
					mediaGroupMedia.setMediaOrder(i);
					mediaGroupMediaDao.update(mediaGroupMedia);
				}
				i++;
			}
			for (MediaGroupMedia mediaGroupMedia : mediaGroupMedias) {
				boolean b = false;
				for (String str : string) {
					if(mediaGroupMedia.getMedia().getMediaId() == Integer.parseInt(str))
						b = true;
				}
				if(!b){
					mediaGroupMediaDao.delete(mediaGroupMedia);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
}