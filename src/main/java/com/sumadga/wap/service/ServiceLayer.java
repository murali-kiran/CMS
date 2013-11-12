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

	public List<Bean<Integer,String>>	getCategoryByServiceId(int serviceId){

		List<ServiceMediaGroup> categories =  serviceMediaGroupDao.findByProperty("service",serviceId);
		List<Bean<Integer,String>> beans = getCategoryName(categories);
		return beans;

	}

	public List<Bean<Integer,String>> getCategoryName(List<ServiceMediaGroup> categories){

		List<Bean<Integer,String>> beans = new ArrayList<Bean<Integer,String>>();
		for(ServiceMediaGroup category  : categories){
			Bean<Integer,String> bean = new Bean<Integer,String>();
			bean.setId(category.getMediaGroupId());
			MediaGroup mediaGroup =	mediaGroupDao.findById(category.getMediaGroupId());

			bean.setName(mediaGroup.getMediaGroupName());
			beans.add(bean);
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

	public Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> getMediaInfoOfCategoryOfMediaType(int categoryId,Map<Integer,Integer> paginationMap,final int pageCount){

		List<MediaBean> mediaBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroup(categoryId);

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

	public Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> getMediaInfoOfCategoriesForLandingPage(List<Bean<Integer,String>> categories){

		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = new LinkedHashMap<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>>();

		for(Bean<Integer,String> category : categories){

			List<MediaBean> mediaBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroup(category.getId());
			List<MediaSubGroup>  mediaSubGroups = mediaSubGroupDao.findByProperty("parentMediaGroup",category.getId());

			List<MediaBean> mediaList = new LinkedList<MediaBean>();

			if(!mediaSubGroups.isEmpty() || !mediaBeans.isEmpty()){
				
				for(MediaSubGroup mediaSubGroup : mediaSubGroups){
					MediaGroup childMediaGroup   =	mediaSubGroup.getChildMediaGroup();
					MediaBean mediaBean = mediaGroupMediaDao.getMediaInfoOfMediaSubGroup(childMediaGroup.getMediaGroupId());
					mediaList.add(mediaBean);
				}
				
				mediaList.addAll(mediaBeans);

				Bean<Boolean,List<MediaBean>> bean = new Bean<Boolean,List<MediaBean>>();
				if(mediaList.size() > 2)
					bean.setName(mediaList.subList(0,2));
				else
					bean.setName(mediaList);	

				if((mediaList.size()) > 2)
					bean.setId(true);
				else
					bean.setId(false);

				mediaInfoMap.put(category, bean);

			}

		}

		return mediaInfoMap;

	}

	public Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> getMediaInfoOfCategory(int catId,int ...pageIdxPageCount){

		Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap = new LinkedHashMap<Bean<Integer,String>,Bean<Integer,List<MediaBean>>>();

		MediaGroup mediaGroup = mediaGroupDao.findById(catId);
		Bean<Integer,String> category = new Bean<Integer,String>();
		category.setId(mediaGroup.getMediaGroupId());
		category.setName(mediaGroup.getMediaGroupTitle());

		List<MediaBean> mediaBeans = mediaGroupMediaDao.getMediaInfoOfMediaGroup(category.getId());
		List<MediaSubGroup>  mediaSubGroups = mediaSubGroupDao.findByProperty("parentMediaGroup",category.getId());

		if(!mediaSubGroups.isEmpty() || !mediaBeans.isEmpty()){

			Bean<Integer,List<MediaBean>> bean = new Bean<Integer,List<MediaBean>>();
			if(mediaBeans.size() > pageIdxPageCount[1]){

				if((pageIdxPageCount[0]+pageIdxPageCount[1]) > mediaBeans.size())	
					bean.setName(mediaBeans.subList(pageIdxPageCount[0],mediaBeans.size()));
				else
					bean.setName(mediaBeans.subList(pageIdxPageCount[0],pageIdxPageCount[1]+pageIdxPageCount[0]));

			}else{
				bean.setName(mediaBeans);
			}

			bean.setId((int)Math.ceil((mediaBeans.size()+mediaSubGroups.size())/(float)pageIdxPageCount[1]));

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


}
