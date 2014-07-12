package com.sumadga.wap.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sumadga.dao.PurchaseAndDownloadDao;
import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaType;
import com.sumadga.dto.Purchas;
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
public class VdoController {
	
	Logger logger = Logger.getLogger(VdoController.class);
	
	@Autowired
	private ServiceLayer serviceLayer;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private DownloadFile downloadFile;
	
	@Autowired
	BillingUtils billingUtils;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Autowired
	PurchaseAndDownloadDao purchaseAndDownloadDao;
	
	@Autowired
	HttpSession session;
	
	
	
	@RequestMapping(value="/service/vdo/{serviceId}",method=RequestMethod.GET)
	public String getService(Model model,@PathVariable Integer serviceId,HttpServletRequest request,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
		//String msisdn = "911234567890";
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "views/sampleService/errorPage";
		}
		
		
		
		Map<String,String> deviceMap =	commonUtils.getDeviceCapbilities(request);

		session.setAttribute("msisdn", msisdn);
		session.setAttribute("operator", request.getParameter("operator"));
		
		int previewCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_LP"));
		
		//List<Bean<categoryId,Bean<categoryName,ServiceMediaGroup>>>	
		List<Bean<Integer,Bean<MediaGroup,ServiceMediaGroup>>> categories =	serviceLayer.getCategoriesOfService(serviceId);
		
		//map<cat<id,title>,bean<isMoreMedia's,List<mediaInfo>>>
		Map<Bean<Integer,MediaGroup>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategories(serviceId,categories,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,previewCount);
		
		//Map<categoryId,Bean<MediaGroup,ServiceMediaGroup>>
		Map<Integer,Bean<MediaGroup,ServiceMediaGroup>> categoryMap = serviceLayer.getMediaGroupOfService(serviceId);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("mediaInfoMap", mediaInfoMap);
		model.addAttribute("mediaGroupMap", categoryMap);
		model.addAttribute("channel",channel);
		model.addAttribute("title", "Home");
		model.addAttribute("previewWidth",deviceMap.get("preview_width"));
		model.addAttribute("previewHeight",deviceMap.get("preview_height"));
				
		return "vdoLandingPage";
		
	}
	
	// ${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${category.id}?channel=${channel}
	
	@RequestMapping(value="/vdo/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getServiceByCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
//		String msisdn = "911234567890";
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}

		    int pageCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_non_LP"));
		    pageCount = 6;
		    Map<String,String> deviceMap =	commonUtils.getDeviceCapbilities(request);
		    
		    
		    int mediaTypeId = -1;
		    Bean<MediaGroup, Map<MediaType,Bean<Integer,List<MediaBean>>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoryOnMediaTypeBases(serviceId,catId,mediaTypeId,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,0,pageCount);
		    
			// serviceLayer.getMediaInfoOfCategoryOfMediaType(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,0,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
			model.addAttribute("previewWidth",deviceMap.get("preview_width"));
			model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		
		return "vdoCategoryPage";
	}
	
	@RequestMapping(value="/vdo/cat/pageId/pageCount/{serviceId}/{catId}/{pageId}/{pageCount}",method=RequestMethod.GET)
	public String getServiceByCategorybyPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer pageId,@PathVariable Integer pageCount,@RequestParam(value="channel", required = false,defaultValue="smd") String channel,@RequestParam(value="mediaTypeId",defaultValue="-1") Integer mediaTypeId){
		String msisdn = commonUtils.getMsisdn(request);
//		String msisdn = "911234567890";
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}
		

			Map<String,String> deviceMap =	commonUtils.getDeviceCapbilities(request);
			Bean<MediaGroup, Map<MediaType,Bean<Integer,List<MediaBean>>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoryOnMediaTypeBases(serviceId,catId,mediaTypeId,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,(pageId-1)*pageCount,pageCount);
			//Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,commonUtils.PRIVIEW_WIDTH,commonUtils.PRIVIEW_HEIGHT,(pageId-1)*pageCount,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
			model.addAttribute("previewWidth",deviceMap.get("preview_width"));
			model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		
			
		return "vdoCategoryPage";
	}
	
	@RequestMapping(value="/vdo/dwl/{serviceId}/{mediaId}/{serviceKeypriceKey}",method=RequestMethod.GET)
	public String downloadMedia(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable String serviceKeypriceKey,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel)
	{
		
		String msisdn = commonUtils.getMsisdn(request);
//		String msisdn = "911234567890";
		
		 if(channel==null)
			 channel="smd";
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}
		Boolean isTestMobileNumber = serviceLayer.isTestMobileNumber(msisdn);

		if(!isTestMobileNumber && request.getParameter("responsecode") == null ){

			BillingModel billingModel =	billingUtils.getEventBilling(request,Long.parseLong(msisdn), session.getAttribute("operator").toString(), serviceKeypriceKey);
			billingModel.setServiceKeypriceKey(serviceKeypriceKey);
			billingModel.setSecretKeyOtherAPI(applicationProperties.getSecretKeyOtherAPI());
			
			model.addAttribute("billingModel", billingModel);
			return "views/sampleService/billingModel";
			}else {
				int serviceKeyId = Integer.parseInt(request.getParameter("servicKeyId"));
				Map<String,String> map =	RequestUtil.INSTANCE.dumpRequestScope(request);
				logger.info(map);
				String responseCode = map.get("responsecode");
				if(isTestMobileNumber || responseCode.equals("101")){
					logger.info("Billing Success");
					
					Purchas purchase =	purchaseAndDownloadDao.checkPurchaseRecordExistForToday(Long.parseLong((String)session.getAttribute("msisdn")),mediaId);
					String remark="";
					if(isTestMobileNumber)
						remark="test number";
					else 
						remark="101:SUCCESS";
					// if purchase is not done for that id
						if(purchase ==null)
						purchase =	serviceLayer.savePurchaseAndPurchaseDetails(request,serviceKeyId,channel,msisdn,remark);
					    
				
					//	return "forward:/service2/dwlFile/"+serviceId+"/"+mediaId+"/"+purchase.getPurchaseId();
				
					
					model.addAttribute("isDownloadURL", true);
					model.addAttribute("downloadURL", request.getContextPath()+"/service2/dwlFile/"+serviceId+"/"+mediaId+"/"+purchase.getPurchaseId()+"?channel="+channel);
					return getService(model, serviceId, request, channel);
				}else{
					String errorCode = billingUtils.getBillingErrorMessage(Integer.parseInt(responseCode));
					String remark=responseCode.equals("101")+":"+errorCode;
					
		
					serviceLayer.saveFailPurchaseAndPFailPurchaseDetails(request,serviceKeyId,errorCode,channel,msisdn,remark);
					
					logger.info("Billing Failed due to :"+errorCode);
				}
				
				 
				return "forward:/service/"+serviceId+"?channel="+channel+"&msisdn="+msisdn+"&operator="+session.getAttribute("operator").toString()+"&mediaTypeId="+request.getParameter("mediaTypeId");
				
			}

		
	}
	
	
	

}
