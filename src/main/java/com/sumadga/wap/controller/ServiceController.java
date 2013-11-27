package com.sumadga.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.sumadga.wap.service.ServiceLayer;

@Controller
@Scope("request")
public class ServiceController extends BaseController{

	Logger logger=Logger.getLogger(ServiceController.class);
	@Autowired
	private ServiceLayer serviceLayer;
	@Autowired
	private DownloadFile downloadFile;
	@Autowired
	BillingUtils billingUtils;
	
	@Autowired
	ServiceUtils serviceUtils;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@RequestMapping(value="/main/ser/{serviceId}",method=RequestMethod.GET)
	public String getSecondService(Model model,@PathVariable Integer serviceId,HttpServletRequest request){
		
		String channel = request.getParameter("channel");
		if(channel==null)
			channel="smd";
		
		String msisdn = serviceUtils.getMsisdn(request);
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn == null){
			return "error";
		}

		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("msisdn", request.getParameter("msisdn"));
		httpSession.setAttribute("operator", request.getParameter("operator"));
		
		
		int previewCount = 3;

		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoriesForLandingPage(serviceId,categories,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,previewCount);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("mediaInfoMap", mediaInfoMap);
		model.addAttribute("channel",channel);
		model.addAttribute("title", "Home");
		
		return "serviceHome";
		
	}
	
	@RequestMapping(value="/main/ser/billingResponse",method=RequestMethod.GET)
	public String billingResponse(HttpServletRequest request,HttpSession session){
		
		Map<String,String> map =	RequestUtil.INSTANCE.dumpRequestScope(request);
		logger.info(map);
		String responseCode = map.get("responsecode");
		if(responseCode.equals("101")){
			logger.info("Billing Success");
			
			
			
		}else{
			String error = billingUtils.getBillingErrorMessage(Integer.parseInt(responseCode));
			logger.info("Billing Failed due to :"+error);
		}
		return "forward:/service/2?channel=smd&msisdn="+session.getAttribute("msisdn").toString()+"&operator="+session.getAttribute("operator").toString();//Please remove msisdn and operator it is for testing. Get from session
	}
	
	
	@RequestMapping(value="/main/ser/dwl/{serviceId}/{mediaId}/{serviceKeypriceKey}",method=RequestMethod.GET)
	public String downloadMedia(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable String serviceKeypriceKey){
		
		
	if(request.getParameter("responsecode") ==null ){
	BillingModel billingModel =	billingUtils.getEventBilling(request,Long.parseLong((String)session.getAttribute("msisdn")), session.getAttribute("operator").toString(), serviceKeypriceKey);
	billingModel.setServiceKeypriceKey(serviceKeypriceKey);
	billingModel.setSecretKeyOtherAPI(applicationProperties.getSecretKeyOtherAPI());
	
	model.addAttribute("billingModel", billingModel);
	return "views/sampleService/billingModel";
	}else {
		billingResponse(request, session);
		return  "forward:/main/ser/"+serviceId+"?channel="+request.getParameter("channel");
	}
}
	
	
	
	@RequestMapping(value="/main/ser/cat/{serviceId}/{catId}",method=RequestMethod.GET)
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
		
		return "serviceMorePage";
	}
	
	@RequestMapping(value="/main/ser/cat/{serviceId}/{parentCatId}/{catId}",method=RequestMethod.GET)
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
		
		return "serviceMorePage";
	}
	
	@RequestMapping(value="/main/ser/cat/pageId/pageCount/{serviceId}/{catId}/{pageId}/{pageCount}",method=RequestMethod.GET)
	public String getSecondServiceByCategorybyPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer pageId,@PathVariable Integer pageCount){
		String channel = request.getParameter("channel");
		Map<String,String> deviceMap =	getDeviceCapbilities(request);
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,100,100,(pageId-1)*pageCount,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
		
		return "serviceMorePage";
	}
}
