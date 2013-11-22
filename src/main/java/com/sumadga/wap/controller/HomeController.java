package com.sumadga.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sumadga.dto.ServiceMediaGroup;
import com.sumadga.utils.ApplicationProperties;
import com.sumadga.utils.CommonUtils;
import com.sumadga.utils.DownloadFile;
import com.sumadga.utils.RequestUtil;
import com.sumadga.wap.billing.BillingModel;
import com.sumadga.wap.billing.BillingUtils;
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
	@Autowired
	BillingUtils billingUtils;
	@Autowired
	ApplicationProperties applicationProperties;

/*	@RequestMapping(value="/service/{serviceId}",method=RequestMethod.GET)
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
	}*/
	
	@RequestMapping(value="/service/{serviceId}",method=RequestMethod.GET)
	public String getSecondService(Model model,@PathVariable Integer serviceId,HttpServletRequest request){
		
		String channel = request.getParameter("channel");
		if(channel==null)
			return "redirect:/service/"+serviceId+"?channel=smd";
		
		if(StringUtils.isBlank(request.getParameter("msisdn"))){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}
		
		
		Map<String,String> deviceMap =	getDeviceCapbilities(request);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("msisdn", request.getParameter("msisdn"));
		httpSession.setAttribute("operator", request.getParameter("operator"));
		
		
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
	
	@RequestMapping(value="/service2/billingResponse",method=RequestMethod.GET)
	public String billingResponse(HttpServletRequest request){
		
	Map<String,String> map =	RequestUtil.INSTANCE.dumpRequestScope(request);
	System.out.println(map);
		
		return "forward:/service/2";
	}
	
	
	@RequestMapping(value="/service2/dwl/{serviceId}/{mediaId}/{serviceKeypriceKey}",method=RequestMethod.GET)
	public String downloadMedia(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable String serviceKeypriceKey){
		
		
	/*BillingModel billingModel =	billingUtils.getEventBilling(request,Long.parseLong((String)session.getAttribute("msisdn")), session.getAttribute("operator").toString(), serviceKeypriceKey);
	billingModel.setServiceKeypriceKey(serviceKeypriceKey);
	billingModel.setSecretKeyOtherAPI(applicationProperties.getSecretKeyOtherAPI());
	
	model.addAttribute("billingModel", billingModel);
	return "views/sampleService/billingModel";*/
		//String url = billingUtils.getEventBillingNew(request,Long.parseLong((String)session.getAttribute("msisdn")), session.getAttribute("operator").toString(), serviceKeypriceKey);
		String url = billingUtils.getEventBillingNew(request,9966L, "vodafone", serviceKeypriceKey);
		System.out.println("Framed url:"+url);
		return "redirect:"+url;
		
	/*String channel = request.getParameter("channel");
	Map<String, String> deviceMap = getDeviceCapbilities(request);
	MediaBean mediaBean = serviceLayer.getMediaInfoOfMedia(mediaId,CommonUtils.MEDIA_CONTENT_NON_PRIVIEW,Integer.parseInt(deviceMap.get("width")),Integer.parseInt(deviceMap.get("height")));
	mediaBean.setServiceId(serviceId);
	
	PurchaseBean purchaseBean = new PurchaseBean();	
	purchaseBean.setPurchase_id((byte)1);
	purchaseBean.setMsisdn("9030335622");
	purchaseBean.setChannel(channel);
	downloadFile.downLoadMedia(request, response,mediaBean,purchaseBean,deviceMap);*/
		
//		getMediaInfoOfMedia.
//		return "landingPage2";
		
	}
	
	
	
	@RequestMapping(value="/service2/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getSecondServiceByCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId){

		    int pageCount = 9;
		    Map<String,String> deviceMap =	getDeviceCapbilities(request);
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
	
	@RequestMapping(value="/service2/cat/{serviceId}/{parentCatId}/{catId}",method=RequestMethod.GET)
	public String getSecondServiceBySubCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer parentCatId,@PathVariable Integer catId){

		    int pageCount = 9;
		    Map<String,String> deviceMap =	getDeviceCapbilities(request);
		    //
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfSubCategory(serviceId,parentCatId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,0,pageCount);
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
		Map<String,String> deviceMap =	getDeviceCapbilities(request);
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,(pageId-1)*pageCount,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
		
		return "service2CategoryPage";
	}
	
	
	
	/*@RequestMapping(value="/service/cat/typeId/pageId/{serviceId}/{catId}/{typeId}/{pageId}/{paginationIds}",method=RequestMethod.GET)
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
	*
	*
	*
	*/
	
	


}
