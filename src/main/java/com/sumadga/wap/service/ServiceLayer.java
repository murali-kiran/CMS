package com.sumadga.wap.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.sumadga.dao.MediaGroupDao;
import com.sumadga.dao.MediaGroupMediaDao;
import com.sumadga.dao.MediaSubGroupDao;
import com.sumadga.dao.ServiceMediaGroupDao;
import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.dto.MediaSubGroup;
import com.sumadga.dto.ServiceMediaGroup;
import com.sumadga.utils.CommonUtils;
import com.sumadga.wap.model.Bean;
import com.sumadga.wap.model.MediaBean;

@Component
public class ServiceLayer {

	@Autowired
	private ServiceMediaGroupDao serviceMediaGroupDao;

	@Autowired
	private MediaGroupMediaDao mediaGroupMediaDao;

	@Autowired
	private MediaSubGroupDao mediaSubGroupDao;

	@Autowired
	private MediaGroupDao mediaGroupDao;

	public List<Bean<Integer,Bean<String,ServiceMediaGroup>>>	getCategoryByServiceId(int serviceId){

		List<ServiceMediaGroup> categories =  serviceMediaGroupDao.findByProperty("service",serviceId);
		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> beans = getCategoryName(categories);
		return beans;

	}

	public List<Bean<Integer,Bean<String,ServiceMediaGroup>>> getCategoryName(List<ServiceMediaGroup> categories){

		//List<Bean<categoryId,Bean<categoryName,ServiceMediaGroup>>>
		
		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> beans = new ArrayList<Bean<Integer,Bean<String,ServiceMediaGroup>>>();
		
		for(ServiceMediaGroup category  : categories){
			Bean<Integer,Bean<String,ServiceMediaGroup>> outerBean = new Bean<Integer,Bean<String,ServiceMediaGroup>>();
			outerBean.setId(category.getMediaGroupId());
			
			Bean<String,ServiceMediaGroup> innerBean = new Bean<String,ServiceMediaGroup>();
			
			MediaGroup mediaGroup =	mediaGroupDao.findById(category.getMediaGroupId());
			
			innerBean.setId(mediaGroup.getMediaGroupName());
			innerBean.setName(category);
			
			outerBean.setName(innerBean);
			
			beans.add(outerBean);
		}

		return beans;
	}

	public List<MediaGroupMedia>	getMediaByCategoryId(int categoryId){

		List<MediaGroupMedia> medias =  mediaGroupMediaDao.findByProperty("mediaGroup",categoryId);
		return medias;

	}

	public List<Bean<Integer,String>>	getMediaTypesofCategoryId(int categoryId){

		List<Bean<Integer,String>> mediaTypes = mediaGroupMediaDao.getMediaTypeOfMediaGroup(categoryId);
		return mediaTypes;

	}



	public List<MediaSubGroup>	getSubCategoriesByCategoryId(int categoryId){

		List<MediaSubGroup> mediaSubGroups =  mediaSubGroupDao.findByProperty("parentMediaGroup",categoryId);
		return mediaSubGroups;
	}

	public Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> getMediaInfoOfCategoryOfMediaType(int serviceId,int categoryId,Map<Integer,Integer> paginationMap,final int pageCount ,int mediaContentPurposeId,int width,int height){

		ServiceMediaGroup serviceMediaGroup = serviceMediaGroupDao.findByServiceIdAndMediagroupId(serviceId, categoryId);
		List<MediaBean> mediaBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroup(categoryId,mediaContentPurposeId,serviceMediaGroup.getServiceKeyId(),width,height);

		Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap = new LinkedHashMap<Bean<Integer,String>,Bean<Integer,List<MediaBean>>>();

		if(!mediaBeans.isEmpty()){
			List<Bean<Integer,String>> mediaTypes = mediaGroupMediaDao.getMediaTypeOfMediaGroup(categoryId);

			for(Bean<Integer,String> mediaType : mediaTypes){

				Bean<Integer,List<MediaBean>> bean = new Bean<Integer,List<MediaBean>>();

				List<MediaBean> mediaInfoBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroupOfSpecificMediaType(categoryId, mediaType.getId(), paginationMap.get(mediaType.getId()),pageCount);
				bean.setName(mediaInfoBeans);

				int mediaCount = mediaGroupMediaDao.getCountOfMediaInMediaGroupOfSpecificMediaType(categoryId, mediaType.getId());
				bean.setId(mediaCount);

				mediaInfoMap.put(mediaType, bean);

			}

			return mediaInfoMap;
		}else{
			return null;
		}

	}

	public Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> getMediaInfoOfCategoriesForLandingPage(int serviceId,List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories,int mediaContentPurposeId,int width,int height,int previewCount){

		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = new LinkedHashMap<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>>();

		// LIke Bean<categoryId,Bean<categoryName,ServiceMediaGroup>>
		for(Bean<Integer,Bean<String,ServiceMediaGroup>> category : categories){

			int serviceKeyId = category.getName().getName().getServiceKeyId();
			List<MediaBean> mediaBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroup(category.getId(),mediaContentPurposeId,serviceKeyId,width,height);
			List<MediaSubGroup>  mediaSubGroups = mediaSubGroupDao.findByProperty("parentMediaGroup",category.getId());

			List<MediaBean> mediaList = new LinkedList<MediaBean>();

			if(!mediaSubGroups.isEmpty() || !mediaBeans.isEmpty()){
				
				for(MediaSubGroup mediaSubGroup : mediaSubGroups){
					MediaGroup childMediaGroup   =	mediaSubGroup.getChildMediaGroup();
					ServiceMediaGroup serviceMediaGroup = serviceMediaGroupDao.findByServiceIdAndMediagroupId(serviceId, childMediaGroup.getMediaGroupId());
					MediaBean mediaBean = mediaGroupMediaDao.getMediaInfoOfMediaSubGroup(childMediaGroup.getMediaGroupId(),serviceMediaGroup.getServiceKeyId(),CommonUtils.MEDIA_CONTENT_PRIVIEW);
					mediaList.add(mediaBean);
				}
				
				mediaList.addAll(mediaBeans);

				Bean<Boolean,List<MediaBean>> bean = new Bean<Boolean,List<MediaBean>>();
				if(mediaList.size() > previewCount)
					bean.setName(mediaList.subList(0,previewCount));
				else
					bean.setName(mediaList);	

				if((mediaList.size()) > previewCount)
					bean.setId(true);
				else
					bean.setId(false);
				
				Bean<Integer,String> catIdAndName= new Bean<Integer,String>();
				catIdAndName.setId(category.getId());
				catIdAndName.setName(category.getName().getId());

				mediaInfoMap.put(catIdAndName, bean);

			}

		}

		return mediaInfoMap;

	}

	public Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> getMediaInfoOfCategory(int serviceId,int catId,int mediaContentPurposeId,int width,int height,int ...pageIdxPageCount){

		//Map<Bean<mediaGroupId,mediaGroupTitle>,Bean<no. of pages count,List<MediaBean>>>
		Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap = new LinkedHashMap<Bean<Integer,String>,Bean<Integer,List<MediaBean>>>();

		MediaGroup mediaGroup = mediaGroupDao.findById(catId);
		ServiceMediaGroup serviceMediaGroup =  serviceMediaGroupDao.findByServiceIdAndMediagroupId(serviceId,catId);
		Bean<Integer,String> category = new Bean<Integer,String>();
		category.setId(mediaGroup.getMediaGroupId());
		category.setName(mediaGroup.getMediaGroupTitle());

		List<MediaBean> mediaBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroup(category.getId(),mediaContentPurposeId,serviceMediaGroup.getServiceKeyId(),width,height);
		List<MediaSubGroup>  mediaSubGroups = mediaSubGroupDao.findByProperty("parentMediaGroup",category.getId());

		List<MediaBean> mediaList = new LinkedList<MediaBean>();
		
		if(!mediaSubGroups.isEmpty() || !mediaBeans.isEmpty()){
			
			for(MediaSubGroup mediaSubGroup : mediaSubGroups){
				MediaGroup childMediaGroup   =	mediaSubGroup.getChildMediaGroup();
				ServiceMediaGroup serviceMediaGroupOfchildMediaGroup = serviceMediaGroupDao.findByServiceIdAndMediagroupId(serviceId, childMediaGroup.getMediaGroupId());
				MediaBean mediaBean = mediaGroupMediaDao.getMediaInfoOfMediaSubGroup(childMediaGroup.getMediaGroupId(),serviceMediaGroupOfchildMediaGroup.getServiceKeyId(),CommonUtils.MEDIA_CONTENT_PRIVIEW);
				mediaList.add(mediaBean);
			}
			
			mediaList.addAll(mediaBeans);

			Bean<Integer,List<MediaBean>> bean = new Bean<Integer,List<MediaBean>>();
			if(mediaList.size() > pageIdxPageCount[1]){

				if((pageIdxPageCount[0]+pageIdxPageCount[1]) > mediaList.size())	
					bean.setName(mediaList.subList(pageIdxPageCount[0],mediaList.size()));
				else
					bean.setName(mediaList.subList(pageIdxPageCount[0],pageIdxPageCount[1]+pageIdxPageCount[0]));

			}else{
				bean.setName(mediaList);
			}

			bean.setId((int)Math.ceil((mediaList.size())/(float)pageIdxPageCount[1]));

			mediaInfoMap.put(category, bean);

		}

		return mediaInfoMap;

	}

	public Map<Integer, Integer>	makePaginationMap(Model model,Integer categoryId,String... pageIds){
		List<Bean<Integer,String>> mediaTypes = getMediaTypesofCategoryId(categoryId);

		Map<Integer, Integer> mediaTypePaginationMap = new HashMap<Integer, Integer>();
		StringBuilder stringBuilder = new StringBuilder("");

		if (pageIds == null || pageIds.length == 0){
			for(Bean<Integer,String> mediaType : mediaTypes) {
				stringBuilder.append(mediaType.getId()+":0,");
				mediaTypePaginationMap.put(mediaType.getId(),0);
			}
		}else{
			mediaTypePaginationMap = CommonUtils.paginationStringToMap(pageIds[0], Integer.parseInt(pageIds[1]), Integer.parseInt(pageIds[2]));
			for(Map.Entry<Integer, Integer> entry : mediaTypePaginationMap.entrySet()) {
				stringBuilder.append(entry.getKey()+":"+entry.getValue()+",");
			}
		}

		String str = stringBuilder.toString();
		str=str.substring(0,str.lastIndexOf(","));
		model.addAttribute("paginationMap",str);

		return mediaTypePaginationMap;
	}
	
	public MediaBean getMediaInfoOfMedia(int mediaId,int mediaContentPurposeId,int width,int height){
		return mediaGroupMediaDao.getMediaInfoOfMedia(mediaId, mediaContentPurposeId, width, height);
	}


}
