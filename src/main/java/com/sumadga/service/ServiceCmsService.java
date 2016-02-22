package com.sumadga.service;

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
import com.sumadga.dao.ServiceDao;
import com.sumadga.dao.ServiceMediaGroupDao;
import com.sumadga.dto.Media;
import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.dto.MediaSubGroup;
import com.sumadga.dto.Service;
import com.sumadga.dto.ServiceMediaGroup;
import com.sumadga.mediagroup.MediaGroupModel;
import com.sumadga.model.JqGridInfo;
import com.sumadga.model.MediaGroupBean;
import com.sumadga.upload.MediaUploadModel;
import com.sumadga.utils.MediaUtils;


@Component
public class ServiceCmsService {
	
	private static final Logger logger = Logger.getLogger(ServiceCmsService.class);
	
	@Autowired
	MediaUtils mediaUtils;
	@Autowired
	MediaGroupDao mediaGroupDao;
	/*@Autowired
	MediaSubGroupDao mediaSubGroupDao;*/
	@Autowired
	MediaDao mediaDao;
	@Autowired
	ServiceDao serviceDao;
	@Autowired
	ServiceMediaGroupDao serviceMediaGroupDao;



	public void getServiceList(ModelMap model) {
		// TODO Auto-generated method stub
		List<Service> services = serviceDao.findAll();
		
		model.addAttribute("serviceList",services);
	}



	public void getGroups(ModelMap model, Integer serviceId) {
		// TODO Auto-generated method stub
		try {
			List<MediaGroupModel> mediaGroupList = new ArrayList<MediaGroupModel>();
			List<ServiceMediaGroup> serviceMediaGroups = serviceMediaGroupDao.findByServiceId(serviceId);
			for (ServiceMediaGroup serviceMediaGroup : serviceMediaGroups) {
				MediaGroupModel mediaGroup = new MediaGroupModel();
				mediaGroup.setMediaGroupName(serviceMediaGroup.getMediaGroup().getMediaGroupName());
				mediaGroup.setMediaGroupTitle(serviceMediaGroup.getMediaGroup().getMediaGroupTitle());
				mediaGroup.setMediaGroupId(serviceMediaGroup.getMediaGroup().getMediaGroupId());
				mediaGroup.setServiceMediaGroupId(serviceMediaGroup.getServiceMediaGroupId());
				mediaGroup.setServiceId(serviceMediaGroup.getService().getServiceId());
				mediaGroup.setCheckStatus(true);
				mediaGroupList.add(mediaGroup);
			}
			model.addAttribute("mediaGroupList", mediaGroupList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void getRemainingMediaGroup(MediaGroupModel mediaGroupModel,
			ModelMap model) {
		try {
			List<MediaGroup> mediaList1 = serviceMediaGroupDao.findRemainingMediaGroup(mediaGroupModel);
			List<MediaGroupModel> mediaGroupList = new ArrayList<MediaGroupModel>();
			for (MediaGroup mediaGroup : mediaList1) {
				MediaGroupModel mediaGroupModel2 = new MediaGroupModel();
				mediaGroupModel2.setMediaGroupId(mediaGroup.getMediaGroupId());
				mediaGroupModel2.setMediaGroupName(mediaGroup.getMediaGroupName());
				mediaGroupModel2.setMediaGroupTitle(mediaGroup.getMediaGroupTitle());
				mediaGroupModel2.setServiceId(mediaGroupModel.getServiceId());
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
			Service service = serviceDao.findById(mediaGroupModel.getServiceId());
			if(service != null){
				for (String string : selectedGroup) {
					MediaGroup mediaGroup = mediaGroupDao.findById(Integer.parseInt(string));
					if(mediaGroup != null){
						ServiceMediaGroup serviceMediaGroup = new ServiceMediaGroup();
						serviceMediaGroup.setModifiedTime(new Timestamp(System.currentTimeMillis()));
						serviceMediaGroup.setCreatedTime(new Timestamp(System.currentTimeMillis()));
						serviceMediaGroup.setService(service);
						serviceMediaGroup.setMediaGroup(mediaGroup);
						//Values hard coded check once
						serviceMediaGroup.setServiceKeyId(1);
						serviceMediaGroup.setIsOneTimeCharge((byte)1);
						serviceMediaGroup.setGroupOrder(1);
						serviceMediaGroupDao.save(serviceMediaGroup);
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
			List<ServiceMediaGroup> serviceMediaGroups = serviceMediaGroupDao.findByProperty("service", mediaGroupModel.getServiceId());
			String[] selectedGroup = mediaGroupModel.getSelectedMediaGroup();
			Service service = serviceDao.findById(mediaGroupModel.getServiceId());
			if(service != null){
				int i=1;
				for (String string : selectedGroup) {
					ServiceMediaGroup serviceMediaGroup = serviceMediaGroupDao.findById(Integer.parseInt(string));
					if(serviceMediaGroup != null){
						serviceMediaGroup.setGroupOrder(i);
						serviceMediaGroupDao.update(serviceMediaGroup);
					}
					i++;
				}
			}
			
			for (ServiceMediaGroup serviceMediaGroup : serviceMediaGroups) {
				boolean b = false;
				for (String str : selectedGroup) {
					if(serviceMediaGroup.getServiceMediaGroupId() == Integer.parseInt(str))
						b = true;
				}
				if(!b){
					serviceMediaGroupDao.delete(serviceMediaGroup);
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw e;
		}
	}
}