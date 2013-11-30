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
import org.springframework.web.bind.annotation.RequestParam;

import com.sumadga.dao.PurchaseAndDownloadDao;
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
public class HomeController extends BaseController{
	
	Logger logger=Logger.getLogger(HomeController.class);

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

	@RequestMapping(value="/service/{serviceId}",method=RequestMethod.GET)
	public String getService(Model model,@PathVariable Integer serviceId,HttpServletRequest request,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
		

		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "views/sampleService/errorPage";
		}
		
		
		
		Map<String,String> deviceMap =	getDeviceCapbilities(request);

		session.setAttribute("msisdn", msisdn);
		session.setAttribute("operator", request.getParameter("operator"));
		
		int previewCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_LP"));
		
		//List<Bean<categoryId,Bean<categoryName,ServiceMediaGroup>>>	
		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		//map<cat<id,title>,bean<isMoreMedia's,List<mediaInfo>>>
		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoriesForLandingPage(serviceId,categories,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,previewCount);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("mediaInfoMap", mediaInfoMap);
		model.addAttribute("channel",channel);
		model.addAttribute("title", "Home");
		model.addAttribute("previewWidth",deviceMap.get("preview_width"));
		model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		
		
		return "landingPage2";
		
	}
	
	
	@RequestMapping(value="/service2/dwlFile/{serviceId}/{mediaId}/{purchaseId}",method=RequestMethod.GET)
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable Integer purchaseId){
		Map<String, String> deviceMap = getDeviceCapbilities(request);
		

		Purchas purchas = serviceLayer.getPurchas(purchaseId);
		MediaBean mediaBean = serviceLayer.getMediaInfoOfMedia(mediaId,CommonUtils.MEDIA_CONTENT_NON_PRIVIEW,Integer.parseInt(deviceMap.get("width")),Integer.parseInt(deviceMap.get("height")));
		mediaBean.setServiceId(serviceId);
		
		downloadFile.downLoadMedia(request, response,mediaBean,purchas,deviceMap);

	}
	
	
	@RequestMapping(value="/service2/dwl/{serviceId}/{mediaId}/{serviceKeypriceKey}",method=RequestMethod.GET)
	public String downloadMedia(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable String serviceKeypriceKey,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
		
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
				return "forward:/service/"+serviceId+"?channel="+channel+"&msisdn="+msisdn+"&operator="+session.getAttribute("operator").toString();
				
			}

		
	}
	
	@RequestMapping(value="/service2/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getServiceByCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}

		    int pageCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_non_LP"));
		    Map<String,String> deviceMap =	getDeviceCapbilities(request);
		    
		    //Map<Bean<mediaGroupId,mediaGroupTitle>,Bean<no. of pages count,List<MediaBean>>>
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,0,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
			model.addAttribute("previewWidth",deviceMap.get("preview_width"));
			model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		
		return "service2CategoryPage";
	}
	
	@RequestMapping(value="/service2/cat/{serviceId}/{parentCatId}/{catId}",method=RequestMethod.GET)
	public String getServiceBySubCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer parentCatId,@PathVariable Integer catId,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}

		    int pageCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_non_LP"));
		    Map<String,String> deviceMap =	getDeviceCapbilities(request);
		    //
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfSubCategory(serviceId,parentCatId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,0,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
			model.addAttribute("previewWidth",deviceMap.get("preview_width"));
			model.addAttribute("previewHeight",deviceMap.get("preview_height"));
			
		
		return "service2CategoryPage";
	}
	
	@RequestMapping(value="/service2/cat/pageId/pageCount/{serviceId}/{catId}/{pageId}/{pageCount}",method=RequestMethod.GET)
	public String getServiceByCategorybyPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer pageId,@PathVariable Integer pageCount,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		String msisdn = commonUtils.getMsisdn(request);
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}
		

		Map<String,String> deviceMap =	getDeviceCapbilities(request);
			Map<Bean<Integer,String>,Bean<Integer,List<MediaBean>>> mediaInfoMap =	serviceLayer.getMediaInfoOfCategory(serviceId,catId,CommonUtils.MEDIA_CONTENT_PRIVIEW,commonUtils.PRIVIEW_WIDTH,commonUtils.PRIVIEW_HEIGHT,(pageId-1)*pageCount,pageCount);
			
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("categoryId",catId);
			model.addAttribute("mediaInfoMap", mediaInfoMap);
			model.addAttribute("PaginationCount",pageCount);
			model.addAttribute("channel",channel);
			model.addAttribute("previewWidth",deviceMap.get("preview_width"));
			model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		
		return "service2CategoryPage";
	}
	
	// searchByTag
	
	@RequestMapping(value="/service/showSearchPage/{serviceId}",method=RequestMethod.GET)
	public String showSearchPage(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		Map<String, String> deviceMap = getDeviceCapbilities(request);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("title", "Search");
		
		return "searchByTag";
	}
	
	@RequestMapping(value="/service/searchMediaByTag/{serviceId}/{pageId}",method=RequestMethod.GET)
	public String searchMediaByTag(HttpServletRequest request,HttpServletResponse response,Model model,@PathVariable Integer serviceId,@PathVariable Integer pageId,@RequestParam(value="channel", required = false,defaultValue="smd") String channel,@RequestParam(value="tag", required = false,defaultValue="") String tag){
		
		String msisdn = commonUtils.getMsisdn(request);
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}
		
		List<MediaBean> beans = serviceLayer.getMediaInfoUsingTag(tag, serviceId, CommonUtils.MEDIA_CONTENT_PRIVIEW, CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT);
		model.addAttribute("title", "Search");
		
		if(StringUtils.isBlank(tag)){
			model.addAttribute("error","Enter Something");
			return "searchByTag";
		}else if(beans.isEmpty()){
			model.addAttribute("error","No Match Found");
			return "searchByTag";
		}else{
			Map<String, String> deviceMap = getDeviceCapbilities(request);
			int pageCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_non_LP"));
		
			List<MediaBean> mediaBeans = serviceLayer.getMediaInfoUsingTag(tag, serviceId, CommonUtils.MEDIA_CONTENT_PRIVIEW, CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,(pageId-1)*pageCount,pageCount);
		
			model.addAttribute("serviceId",serviceId);
			model.addAttribute("tagName",tag);
			model.addAttribute("mediaBeans", mediaBeans);
			model.addAttribute("totalMediaCount",beans.size());
			model.addAttribute("paginationCount",Math.ceil((beans.size())/(float)pageCount));
			model.addAttribute("channel",channel);
			model.addAttribute("previewWidth",deviceMap.get("preview_width"));
			model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		
			return "tagMediaPage";
		}
	}
	
	
	
}
