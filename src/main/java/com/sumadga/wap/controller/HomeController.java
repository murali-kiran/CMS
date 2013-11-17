package com.sumadga.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumadga.dto.ServiceMediaGroup;
import com.sumadga.utils.CommonUtils;
import com.sumadga.utils.DownloadFile;
import com.sumadga.wap.model.Bean;
import com.sumadga.wap.model.MediaBean;
import com.sumadga.wap.model.PurchaseBean;
import com.sumadga.wap.service.ServiceLayer;

@Controller
@Scope("request")
public class HomeController extends BaseController{

	@Autowired
	private ServiceLayer serviceLayer;
	@Autowired
	private DownloadFile downloadFile;

	@RequestMapping(value="/service/{serviceId}",method=RequestMethod.GET)
	public String getFirstService(Model model,@PathVariable Integer serviceId){

		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		if(!categories.isEmpty()){
			Map<Integer,Integer> mediaTypePaginationMap = serviceLayer.makePaginationMap(model,categories.get(0).getId());
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategoryOfMediaType(serviceId,categories.get(0).getId(),mediaTypePaginationMap,2,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100);
			
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",categories.get(0).getId());
			model.addAttribute("categories", categories);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
		    model.addAttribute("PaginationCount",2);
					    
		}
	
		return "sampleLandingPage";
	}
	
	@RequestMapping(value="/service2/{serviceId}",method=RequestMethod.GET)
	public String getSecondService(Model model,@PathVariable Integer serviceId,HttpServletRequest request){
		String channel = request.getParameter("channel");
		if(channel==null)
			return "redirect:/service2/"+serviceId+"?channel=smd";
		
		int previewCount = 3;
		//List<Bean<categoryId,Bean<categoryName,ServiceMediaGroup>>>	
		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		//map<cat<id,title>,bean<isMoreMedia's,List<mediaInfo>>>
		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoriesForLandingPage(serviceId,categories,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,previewCount);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("mediaInfoMap", mediaInfoMap);
		model.addAttribute("channel",channel);
		model.addAttribute("title", "Home");
		
		return "landingPage2";
		
	}
	
	
	
	@RequestMapping(value="/service2/dwl/{serviceId}/{mediaId}",method=RequestMethod.GET)
	public void downloadMedia(HttpServletRequest request,HttpServletResponse response,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId){
	String channel = request.getParameter("channel");
	Map<String, String> deviceCapility = getDeviceCapbilities(request);
	MediaBean mediaBean = serviceLayer.getMediaInfoOfMedia(mediaId,CommonUtils.MEDIA_CONTENT_NON_PRIVIEW, 100, 100);
	mediaBean.setServiceId(serviceId);
	
	PurchaseBean purchaseBean = new PurchaseBean();	
	purchaseBean.setPurchase_id((byte)1);
	purchaseBean.setMsisdn("9030575622");
	purchaseBean.setChannel(channel);
	downloadFile.downLoadMedia(request, response,mediaBean,purchaseBean,deviceCapility);
		
//		getMediaInfoOfMedia.
//		return "landingPage2";
		
	}
	
	@RequestMapping(value="/service/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getFirstServiceByCategory(Model model,@PathVariable Integer serviceId,@PathVariable Integer catId){

		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		if(!categories.isEmpty()){
			Map<Integer,Integer> mediaTypePaginationMap = serviceLayer.makePaginationMap(model,categories.get(0).getId());
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategoryOfMediaType(serviceId,catId,mediaTypePaginationMap,2,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",categories.get(0).getId());
			model.addAttribute("categories", categories);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",2);
		    
		}
		
		return "sampleLandingPage";
	}
	
	@RequestMapping(value="/service2/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getSecondServiceByCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId){

		    int pageCount = 9;
		    
		    //
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,0,pageCount);
			String channel = request.getParameter("channel");
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
		
		return "service2CategoryPage";
	}
	
	@RequestMapping(value="/service2/cat/pageId/pageCount/{serviceId}/{catId}/{pageId}/{pageCount}",method=RequestMethod.GET)
	public String getSecondServiceByCategorybyPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer pageId,@PathVariable Integer pageCount){
		String channel = request.getParameter("channel");
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,(pageId-1)*pageCount,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
		
		return "service2CategoryPage";
	}
	
	
	
	@RequestMapping(value="/service/cat/typeId/pageId/{serviceId}/{catId}/{typeId}/{pageId}/{paginationIds}",method=RequestMethod.GET)
	public String getFirstServiceByCategoryAndPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer typeId,
			@PathVariable Integer pageId,@PathVariable String paginationIds){

		int pageCount =2;
		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		if(!categories.isEmpty()){
			Map<Integer,Integer> mediaTypePaginationMap = serviceLayer.makePaginationMap(model,categories.get(0).getId(),paginationIds,typeId+"",pageId+"");
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategoryOfMediaType(serviceId,catId,mediaTypePaginationMap,pageCount,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",categories.get(0).getId(	));
			model.addAttribute("categories", categories);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",2);
			
		}
		
		return "sampleLandingPage";
	}
	
	


}
