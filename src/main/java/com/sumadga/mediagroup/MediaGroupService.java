package com.sumadga.mediagroup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.sumadga.dao.MediaDao;
import com.sumadga.dao.MediaGroupDao;
import com.sumadga.dao.MediaGroupMediaDao;
import com.sumadga.dao.MediaSubGroupDao;
import com.sumadga.dto.Media;
import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.dto.MediaSubGroup;
import com.sumadga.model.JqGridInfo;
import com.sumadga.model.MediaGroupBean;
import com.sumadga.upload.MediaUploadModel;
import com.sumadga.utils.MediaUtils;


@Component
public class MediaGroupService {
	
	private static final Logger logger = Logger.getLogger(MediaGroupService.class);
	
	@Autowired
	MediaUtils mediaUtils;
	@Autowired
	MediaGroupDao mediaGroupDao;
	@Autowired
	MediaSubGroupDao mediaSubGroupDao;
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
public void editGroup(ModelMap model,int mgid){
		
		logger.info("upload service");
		
		model.addAttribute("mediaList",mediaUtils.getMediaList());
				
        MediaGroup mediaGroup = mediaGroupDao.findById(mgid);
        MediaGroupModel mediaGroupModel = new MediaGroupModel();
        mediaGroupModel.setMediaGroupId(mediaGroup.getMediaGroupId());
        mediaGroupModel.setMediaGroupName(mediaGroup.getMediaGroupName());
        mediaGroupModel.setMediaGroupTitle(mediaGroup.getMediaGroupTitle());
        mediaGroupModel.setMediaId(mediaGroup.getMediaGroupPreviewId());
        mediaGroupModel.setDescription(mediaGroup.getMediaGroupDescription());
        
		model.addAttribute("addGroup", mediaGroupModel);
		
	}

	public void validate(MediaGroupModel mediaGroupModel, BindingResult result) {
		// TODO Auto-generated method stub
		
	}


	public void saveGroup(MediaGroupModel mediaGroupModel)throws Exception {
		// TODO Auto-generated method stub
		try {
			MediaGroup mediaGroup = null;
			if(mediaGroupModel.getMediaGroupId() == null){
				mediaGroup = new MediaGroup();
				mediaGroup.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			}
			else
				mediaGroup = mediaGroupDao.findById(mediaGroupModel.getMediaGroupId());
			
			mediaGroup.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			mediaGroup.setMediaGroupName(mediaGroupModel.getMediaGroupName());
			mediaGroup.setMediaGroupTitle(mediaGroupModel.getMediaGroupTitle());
			mediaGroup.setMediaGroupDescription(mediaGroupModel.getDescription());
			mediaGroup.setMediaGroupPreviewId(mediaGroupModel.getMediaId());
			if(mediaGroupModel.getMediaGroupId() == null)
				mediaGroupDao.save(mediaGroup);
			else
				mediaGroupDao.update(mediaGroup);
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
	
	public JqGridInfo<MediaGroupBean> listAjaxGroup() {
		List<MediaGroup> mediaGroupList = mediaGroupDao.findAll();
		int totalNumberOfPages = 1;
	    int currentPageNumber = 1;
	    int totalNumberOfRecords = mediaGroupList.size();
		
		List<MediaGroupBean> rows = new ArrayList<MediaGroupBean>();
		for(MediaGroup mediaGroup : mediaGroupList){
			MediaGroupBean groupBean = new MediaGroupBean();
			groupBean.setMediaGroupName(mediaGroup.getMediaGroupName());
			groupBean.setMediaGroupId(mediaGroup.getMediaGroupId());
			groupBean.setMediaGroupTitle(mediaGroup.getMediaGroupTitle());
			groupBean.setMediaGroupPreviewId(mediaGroup.getMediaGroupPreviewId());
			groupBean.setMediaGroupDescription(mediaGroup.getMediaGroupDescription());
			groupBean.setMapGroup(mediaGroup.getMediaGroupId());
			groupBean.setMapMedia(mediaGroup.getMediaGroupId());
			
			rows.add(groupBean);
		}
		
        JqGridInfo<MediaGroupBean> jqGridInfo = new JqGridInfo<MediaGroupBean>(totalNumberOfPages, currentPageNumber, totalNumberOfRecords, rows);
		
		System.out.println(jqGridInfo.getJsonString());
		
		return jqGridInfo;
		
	}
	
	public void getMedia(ModelMap model, Integer mediaGroupId) {
		// TODO Auto-generated method stub
		try {
			//List<Media> mediaList1 = mediaDao.findAll();
			List<MediaModel> mediaList = new ArrayList<MediaModel>();
			//List<MediaGroupMedia> mediaGroupMedias = new ArrayList<MediaGroupMedia>();
			List<MediaGroupMedia> mediaGroupMedias = mediaGroupMediaDao.findMediaByOrder("mediaGroup", mediaGroupId);
			//BeanUtils.copyProperties(mediaList, mediaList1);
			for (MediaGroupMedia mediaGroupMedia : mediaGroupMedias) {
				Media media = mediaGroupMedia.getMedia();
				MediaModel mediaModel2 = new MediaModel();
				mediaModel2.setDescription(media.getDescription());
				mediaModel2.setMediaId(media.getMediaId());
				mediaModel2.setMediaName(media.getMediaName());
				mediaModel2.setMediaTitle(media.getMediaTitle());
				mediaModel2.setMediaCycle(media.getMediaCycle());
				mediaModel2.setMgid(mediaGroupId);
				if(media.getMediaId() == mediaGroupMedia.getMedia().getMediaId()){
					mediaModel2.setCheckStatus(true);
				}
				mediaList.add(mediaModel2);
			}
			model.addAttribute("mediaList", mediaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getGroups(ModelMap model, Integer parentMediaGroupId) {
		// TODO Auto-generated method stub
		try {
			List<MediaGroupModel> mediaGroupList = new ArrayList<MediaGroupModel>();
			List<MediaSubGroup> mediaSubGroups = mediaSubGroupDao.findMediaGroupByOrder(parentMediaGroupId);
			for (MediaSubGroup mediaSubGroup : mediaSubGroups) {
				MediaGroupModel mediaGroup = new MediaGroupModel();
				mediaGroup.setMediaGroupName(mediaSubGroup.getChildMediaGroup().getMediaGroupName());
				mediaGroup.setMediaGroupTitle(mediaSubGroup.getChildMediaGroup().getMediaGroupTitle());
				mediaGroup.setMediaGroupId(mediaSubGroup.getChildMediaGroup().getMediaGroupId());
			//	mediaGroup.setMediaGroupPreviewId(mediaSubGroup.getChildMediaGroup().getMediaGroupPreviewId());
			//	mediaGroup.setMediaGroupDescription(mediaSubGroup.getChildMediaGroup().getMediaGroupDescription());
				mediaGroup.setMediaSubGroupId(mediaSubGroup.getSubGroupId());
				mediaGroup.setCheckStatus(true);
				mediaGroup.setParentmgId(parentMediaGroupId);
				mediaGroupList.add(mediaGroup);
			}
			model.addAttribute("mediaGroupList", mediaGroupList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getRemainingMedia(MediaUploadModel mediaUploadModel,ModelMap model ) {
		// TODO Auto-generated method stub
		try {
			List<Media> mediaList1 = mediaDao.findRemainingMedia(mediaUploadModel);
			List<MediaModel> mediaList = new ArrayList<MediaModel>();
			//List<MediaGroupMedia> mediaGroupMedias = new ArrayList<MediaGroupMedia>();
			//List<MediaGroupMedia> mediaGroupMedias = mediaGroupMediaDao.findByProperty("mediaGroup", mediaGroupId);
			//BeanUtils.copyProperties(mediaList, mediaList1);
			for (Media media : mediaList1) {
				//Media media = mediaGroupMedia.getMedia();
				MediaModel mediaModel2 = new MediaModel();
				mediaModel2.setDescription(media.getDescription());
				mediaModel2.setMediaId(media.getMediaId());
				mediaModel2.setMediaName(media.getMediaName());
				mediaModel2.setMediaTitle(media.getMediaTitle());
				mediaModel2.setMediaCycle(media.getMediaCycle());
				mediaModel2.setMgid(mediaUploadModel.getMgid());
				mediaList.add(mediaModel2);
			}
			model.addAttribute("remMediaList", mediaList);
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
			int i=1;
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
	public void addMedia(MediaModel mediaModel)throws Exception {
		// TODO Auto-generated method stub
		try {
			Integer mediaGroupId = mediaModel.getMgid();
			String[] string = mediaModel.getSelectedMedia();
			//List<Integer> selectedMediaIds = new ArrayList<Integer>();
			List<MediaGroupMedia> mediaGroupMedias = mediaGroupMediaDao.findMappedMedia(mediaGroupId);
			int i=0;
			for (String string2 : string) {
			//	List<MediaGroupMedia> mediaGroupMediaList = mediaGroupMediaDao.findMappedMedia(Integer.parseInt(string2), mediaGroupId);
				MediaGroupMedia mediaGroupMedia = new MediaGroupMedia();
				mediaGroupMedia.setMedia(mediaDao.findById(Integer.parseInt(string2)));
				mediaGroupMedia.setMediaGroup(mediaGroupDao.findById(mediaGroupId));
				mediaGroupMedia.setMediaOrder(i);
				mediaGroupMediaDao.save(mediaGroupMedia);
				i++;
			}
			/*for (MediaGroupMedia mediaGroupMedia : mediaGroupMedias) {
				boolean b = false;
				for (String str : string) {
					if(mediaGroupMedia.getMedia().getMediaId() == Integer.parseInt(str))
						b = true;
				}
				if(!b){
					mediaGroupMediaDao.delete(mediaGroupMedia);
				}
			}*/
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}


	public void getRemainingMediaGroup(MediaGroupModel mediaGroupModel,
			ModelMap model) {
		// TODO Auto-generated method stub
		try {
			List<MediaGroup> mediaList1 = mediaSubGroupDao.findRemainingMediaGroup(mediaGroupModel);
			List<MediaGroupModel> mediaGroupList = new ArrayList<MediaGroupModel>();
			for (MediaGroup mediaGroup : mediaList1) {
				MediaGroupModel mediaGroupModel2 = new MediaGroupModel();
				mediaGroupModel2.setMediaGroupId(mediaGroup.getMediaGroupId());
				mediaGroupModel2.setMediaGroupName(mediaGroup.getMediaGroupName());
				mediaGroupModel2.setMediaGroupTitle(mediaGroup.getMediaGroupTitle());
				mediaGroupModel2.setParentmgId(mediaGroupModel.getParentmgId());
				mediaGroupList.add(mediaGroupModel2);
			}
			model.addAttribute("remMediaGroupList", mediaGroupList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void addMediaGroup(MediaGroupModel mediaGroupModel) {
		// TODO Auto-generated method stub
		try {
			String[] selectedGroup = mediaGroupModel.getSelectedMediaGroup();
			MediaGroup mediaGroup1 = mediaGroupDao.findById(mediaGroupModel.getParentmgId());
			if(mediaGroup1 != null){
				for (String string : selectedGroup) {
					MediaGroup mediaGroup = mediaGroupDao.findById(Integer.parseInt(string));
					if(mediaGroup != null){
						MediaSubGroup mediaSubGroup = new MediaSubGroup();
						mediaSubGroup.setParentMediaGroup(mediaGroup1);
						mediaSubGroup.setChildMediaGroup(mediaGroup);
						mediaSubGroup.setGroupOrder(0);
						mediaSubGroupDao.save(mediaSubGroup);
					}
						
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
	public void remAddOrderMediaGroup(MediaGroupModel mediaGroupModel) {
		// TODO Auto-generated method stub
		try {
			//reused SelectedMediaGroup for media sub group id
			List<MediaSubGroup> mediaSubGroupMedias = mediaSubGroupDao.findByProperty("parentMediaGroup", mediaGroupModel.getParentmgId());
			String[] selectedGroup = mediaGroupModel.getSelectedMediaGroup();
			MediaGroup mediaGroup1 = mediaGroupDao.findById(mediaGroupModel.getParentmgId());
			if(mediaGroup1 != null){
				int i=1;
				for (String string : selectedGroup) {
					MediaSubGroup mediaSubGroup = mediaSubGroupDao.findById(Integer.parseInt(string));
					if(mediaSubGroup != null){
						mediaSubGroup.setGroupOrder(i);
						mediaSubGroupDao.update(mediaSubGroup);
					}
					i++;
				}
			}
			
			for (MediaSubGroup mediaSubGroupMedia : mediaSubGroupMedias) {
				boolean b = false;
				for (String str : selectedGroup) {
					if(mediaSubGroupMedia.getSubGroupId() == Integer.parseInt(str))
						b = true;
				}
				if(!b){
					mediaSubGroupDao.delete(mediaSubGroupMedia);
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
}