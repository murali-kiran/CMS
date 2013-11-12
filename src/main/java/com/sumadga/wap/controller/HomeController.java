package com.sumadga.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumadga.wap.model.Bean;
import com.sumadga.wap.model.MediaBean;
import com.sumadga.wap.service.ServiceLayer;

@Controller
@Scope("request")
public class HomeController {

	@Autowired
	private ServiceLayer serviceLayer;

	@RequestMapping(value="/service/{serviceId}",method=RequestMethod.GET)
	public String getFirstService(Model model,@PathVariable Integer serviceId){

		List<Bean<Integer,String>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		if(!categories.isEmpty()){
			Map<Integer,Integer> mediaTypePaginationMap = serviceLayer.makePaginationMap(model,categories.get(0).getId());
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategoryOfMediaType(categories.get(0).getId(),mediaTypePaginationMap,2);
			
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",categories.get(0).getId());
			model.addAttribute("categories", categories);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
		    model.addAttribute("PaginationCount",2);
			
		    
		}

	
		return "sampleLandingPage";
	}
	
	@RequestMapping(value="/service2/{serviceId}",method=RequestMethod.GET)
	public String getSecondService(Model model,@PathVariable Integer serviceId){
		
		List<Bean<Integer,String>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoriesForLandingPage(categories);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("mediaInfoMap", mediaInfoMap);
		
		return "landingPage2";
		
	}
	
	@RequestMapping(value="/service/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getFirstServiceByCategory(Model model,@PathVariable Integer serviceId,@PathVariable Integer catId){

		List<Bean<Integer,String>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		if(!categories.isEmpty()){
			Map<Integer,Integer> mediaTypePaginationMap = serviceLayer.makePaginationMap(model,categories.get(0).getId());
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategoryOfMediaType(catId,mediaTypePaginationMap,2);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",categories.get(0).getId());
			model.addAttribute("categories", categories);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",2);
		    
		}
		
		return "sampleLandingPage";
	}
	
	@RequestMapping(value="/service2/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getSecondServiceByCategory(Model model,@PathVariable Integer serviceId,@PathVariable Integer catId){

		    int pageCount = 5;
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(catId,0,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
		    
		
		return "service2CategoryPage";
	}
	
	@RequestMapping(value="/service2/cat/pageId/pageCount/{serviceId}/{catId}/{pageId}/{pageCount}",method=RequestMethod.GET)
	public String getSecondServiceByCategorybyPagination(Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer pageId,@PathVariable Integer pageCount){

			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(catId,(pageId-1)*pageCount,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
		    
		
		return "service2CategoryPage";
	}
	
	
	
	@RequestMapping(value="/service/cat/typeId/pageId/{serviceId}/{catId}/{typeId}/{pageId}/{paginationIds}",method=RequestMethod.GET)
	public String getFirstServiceByCategoryAndPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer typeId,
			@PathVariable Integer pageId,@PathVariable String paginationIds){

		int pageCount =2;
		List<Bean<Integer,String>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		if(!categories.isEmpty()){
			Map<Integer,Integer> mediaTypePaginationMap = serviceLayer.makePaginationMap(model,categories.get(0).getId(),paginationIds,typeId+"",pageId+"");
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategoryOfMediaType(catId,mediaTypePaginationMap,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",categories.get(0).getId(	));
			model.addAttribute("categories", categories);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",2);
			
		}
		
		return "sampleLandingPage";
	}
	
	


}
