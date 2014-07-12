package com.sumadga.wap.controller;

import in.verse.ipayy.crypto.CryptoException;
import in.verse.ipayy.crypto.CryptoUtils;

import java.util.Enumeration;
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

import com.sumadga.dao.MediaDao;
import com.sumadga.dao.PurchaseAndDownloadDao;
import com.sumadga.dao.RequestDao;
import com.sumadga.dao.ResponsDao;
import com.sumadga.dao.ServiceKeyPriceDao;
import com.sumadga.dto.Media;
import com.sumadga.dto.Purchas;
import com.sumadga.dto.Request;
import com.sumadga.dto.Respons;
import com.sumadga.dto.ServiceKeyPrice;
import com.sumadga.dto.ServiceMediaGroup;
import com.sumadga.utils.ApplicationProperties;
import com.sumadga.utils.CommonUtils;
import com.sumadga.utils.DownloadFile;
import com.sumadga.utils.RequestUtil;
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
	ResponsDao responsDao;
	
	@Autowired
	RequestDao requestDao;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MediaDao mediaDao;
	
	@Autowired
	ServiceKeyPriceDao serviceKeyPriceDao;

	@RequestMapping(value="/service/{serviceId}",method=RequestMethod.GET)
	public String getService(Model model,@PathVariable Integer serviceId,HttpServletRequest request,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		
	//	String msisdn = commonUtils.getMsisdn(request);
		
		if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
			 }
		/*if(serviceId != 2){
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "views/sampleService/errorPage";
		}
		}*/
		HttpSession session = request.getSession();
		String msisdn = (String)session.getAttribute("msisdn");
		String respCode = request.getParameter("respCode");
		if(msisdn == null && respCode != null){
			setMesisdnOperator(respCode, request);
		}
		else{
			logger.info("Unable to detect msisdn");
			//return "forward:/service/"+serviceId+"?channel="+channel;
		}
		
		
		Map<String,String> deviceMap =	getDeviceCapbilities(request);

		String ms = (String) session.getAttribute("msisdn");
		String op = (String) session.getAttribute("operator");
		
		int previewCount = Integer.parseInt(serviceLayer.getServiceProprety(serviceId, "pageCount_LP"));
		
		//List<Bean<categoryId,Bean<categoryName,ServiceMediaGroup>>>	
		List<Bean<Integer,Bean<String,ServiceMediaGroup>>> categories =	serviceLayer.getCategoryByServiceId(serviceId);
		
		//map<cat<id,title>,bean<isMoreMedia's,List<mediaInfo>>>
		Map<Bean<Integer,String>,Bean<Boolean,List<MediaBean>>> mediaInfoMap = serviceLayer.getMediaInfoOfCategoriesForLandingPage(serviceId,categories,CommonUtils.MEDIA_CONTENT_PRIVIEW,CommonUtils.PRIVIEW_WIDTH,CommonUtils.PRIVIEW_HEIGHT,previewCount);
		String msisdnDetectionUrl = null;
		if(ms == null || op==null){
			
			Request request2 = new Request();
			request2.setRequestedURL(request.getRequestURL().toString());
			request2.setRedirectURL("msisdn_det");
			requestDao.save(request2);
			msisdnDetectionUrl = billingUtils.getIpayMsisdnDetectionUrl(request2.getRequestId());
		}
		System.out.println("ms"+msisdnDetectionUrl);
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("mediaInfoMap", mediaInfoMap);
		model.addAttribute("channel",channel);
		model.addAttribute("title", "Home");
		model.addAttribute("previewWidth",deviceMap.get("preview_width"));
		model.addAttribute("previewHeight",deviceMap.get("preview_height"));
		model.addAttribute("ipaymsisdnUrl",msisdnDetectionUrl);
		
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
	
	
	/*@RequestMapping(value="/service2/dwl/{serviceId}/{mediaId}/{serviceKeypriceKey}",method=RequestMethod.GET)
	public String downloadMedia(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable String serviceKeypriceKey,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel){
		
		String msisdn = commonUtils.getMsisdn(request);
		
		 if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
			 }
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

		
	}*/
@RequestMapping(value="/service2/dwl/{serviceId}/{mediaId}/{serviceKeypriceKey}",method=RequestMethod.GET)	
public String downloadMedia(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@PathVariable Integer mediaId,@PathVariable String serviceKeypriceKey,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel){
		
		/*String msisdn = commonUtils.getMsisdn(request);
		
		 if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
			 }
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}
		Boolean isTestMobileNumber = serviceLayer.isTestMobileNumber(msisdn);*/

		/*if(!isTestMobileNumber && request.getParameter("responsecode") == null ){

			BillingModel billingModel =	billingUtils.getEventBilling(request,Long.parseLong(msisdn), session.getAttribute("operator").toString(), serviceKeypriceKey);
			billingModel.setServiceKeypriceKey(serviceKeypriceKey);
			billingModel.setSecretKeyOtherAPI(applicationProperties.getSecretKeyOtherAPI());
			
			model.addAttribute("billingModel", billingModel);
			return "views/sampleService/billingModel";
			}else {*/
			String msisdn = null;
			String identifier = null;
			
			if(session.getAttribute("msisdn") == null && session.getAttribute("operator") == null && session.getAttribute("identifier") == null){
				String msisdnRespCode = request.getParameter("respCode");
				msisdn = setMesisdnOperator(msisdnRespCode, request);
			}else if(session.getAttribute("msisdn") != null){
				msisdn = (String) session.getAttribute("msisdn");
			}else if(session.getAttribute("identifier") != null){
				identifier = (String) session.getAttribute("identifier");
			} else if (session.getAttribute("identifier") == null){
				identifier = request.getParameter("respCode");
			}
				//String msisdn = request.getSession().
			logger.info("msisdn:"+msisdn+" identifier:"+identifier+ " operator :");
				Boolean isTestMobileNumber = false;
				if(msisdn != null)
					isTestMobileNumber = serviceLayer.isTestMobileNumber(msisdn);
				int serviceKeyId = Integer.parseInt(request.getParameter("serviceKeyId"));
				Map<String,String> map = RequestUtil.INSTANCE.dumpRequestScope(request);
				String responseCode = map.get("responsecode");
				/*Map<String,String> map =	RequestUtil.INSTANCE.dumpRequestScope(request);
				logger.info(map);
				String responseCode = map.get("responsecode");*/
				Long msisdnLong = 0L;
				if(msisdn != null)
					msisdnLong = Long.parseLong(msisdn);
				if(msisdn != null || identifier != null){
				Purchas purchase =	purchaseAndDownloadDao.checkPurchaseRecordExistForToday(msisdnLong,mediaId, identifier);
				if(!isTestMobileNumber && request.getParameter("responsecode") == null && purchase == null){
					
					Media media=mediaDao.findById(mediaId);
					 List<ServiceKeyPrice> keyPrices=serviceKeyPriceDao.findByServiceIdAndServiceKey(serviceId, serviceKeypriceKey);
					Integer price=1;
					if(!keyPrices.isEmpty())
					price=(int)keyPrices.get(0).getPrice();
					String billingURL = billingUtils.getPaymentURLIpayy(request, msisdnLong,price+"",mediaId+"",media.getMediaTitle());
					logger.info("ipay billing url:"+billingURL);
					return "redirect:"+billingURL;
				}
				if(isTestMobileNumber || ( responseCode!=null && responseCode.equals("101") ) || purchase != null){
					logger.info("Billing Success");
					String remark="";
					if(isTestMobileNumber)
						remark="test number";
					else 
						remark="101:SUCCESS";
					// if purchase is not done for that id
						if(purchase ==null)
						purchase =	serviceLayer.savePurchaseAndPurchaseDetails(request,serviceKeyId,channel,msisdn,remark,identifier);
					    
				
					//	return "forward:/service2/dwlFile/"+serviceId+"/"+mediaId+"/"+purchase.getPurchaseId();
				
					
					model.addAttribute("isDownloadURL", true);
					model.addAttribute("downloadURL", request.getContextPath()+"/service2/dwlFile/"+serviceId+"/"+mediaId+"/"+purchase.getPurchaseId()+"?channel="+channel);
					return getService(model, serviceId, request, channel);
				}else{
					String errorCode = billingUtils.getipayErrorMessage(responseCode);
					String remark=responseCode.equals("101")+":"+errorCode;
					if(msisdn != null || identifier != null)
						serviceLayer.saveFailPurchaseAndPFailPurchaseDetails(request,serviceKeyId,errorCode,channel,msisdn,remark, identifier);
					logger.info("Billing Failed due to :"+errorCode);
					model.addAttribute("message", errorCode);
				}
				return "forward:/service/"+serviceId+"?channel="+channel;
				}
				else{
					logger.info("unable to detect MSISDN");
					model.addAttribute("message", "Unable to detect MSISDN");
					return "forward:/service/"+serviceId+"?channel="+channel.toString();
				}
				
	}

	
	@RequestMapping(value="/service2/cat/{serviceId}/{catId}",method=RequestMethod.GET)
	public String getServiceByCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@RequestParam(value = "channel", required = false,defaultValue="smd") String channel){
		
		//String msisdn = commonUtils.getMsisdn(request);
		String serviceKeyId = request.getParameter("serviceKeyId");
		HttpSession session = request.getSession();
		String msisdn = (String)session.getAttribute("msisdn");
		String respCode = request.getParameter("respCode");
		if(msisdn == null && respCode != null){
			setMesisdnOperator(respCode, request);
		}
		else{
			logger.info("Unable to detect msisdn");
			return "forward:/service/"+serviceId+"?channel="+channel;
		}
		if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
			 }
		
		/*if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}*/
		

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
			model.addAttribute("serviceKeyId",serviceKeyId);
		return "service2CategoryPage";
	}
	
	@RequestMapping(value="/service2/cat/{serviceId}/{parentCatId}/{catId}",method=RequestMethod.GET)
	public String getServiceBySubCategory(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer parentCatId,@PathVariable Integer catId,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		
		/*String msisdn = commonUtils.getMsisdn(request);
		
		if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}*/
		String serviceKeyId = request.getParameter("serviceKeyId");
		HttpSession session = request.getSession();
		String msisdn = (String)session.getAttribute("msisdn");
		String respCode = request.getParameter("respCode");
		if(msisdn == null && respCode != null){
			setMesisdnOperator(respCode, request);
		}
		else{
			logger.info("Unable to detect msisdn");
			return "forward:/service/"+serviceId+"?channel="+channel;
		}//
		if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
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
			model.addAttribute("serviceKeyId",serviceKeyId);
		
		return "service2CategoryPage";
	}
	
	@RequestMapping(value="/service2/cat/pageId/pageCount/{serviceId}/{catId}/{pageId}/{pageCount}",method=RequestMethod.GET)
	public String getServiceByCategorybyPagination(HttpServletRequest request,Model model,@PathVariable Integer serviceId,@PathVariable Integer catId,@PathVariable Integer pageId,@PathVariable Integer pageCount,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		HttpSession session = request.getSession();
		String serviceKeyId = request.getParameter("serviceKeyId");
		String msisdn = (String)session.getAttribute("msisdn");
		String respCode = request.getParameter("respCode");
		if(msisdn == null && respCode != null){
			setMesisdnOperator(respCode, request);
		}
		else{
			logger.info("Unable to detect msisdn");
			return "forward:/service/"+serviceId+"?channel="+channel;
		}
			
		
		/*if(request.getParameter("detect")==null && msisdn==null ){
			String msisdnDetectionUrl = billingUtils.getMsisdnDetectionURL(request);
			return "redirect:"+msisdnDetectionUrl;
		}else if(msisdn==null){
			model.addAttribute("errorMsg", "Unable to detect");
			return "errorPage";
		}*/
		if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
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
			model.addAttribute("serviceKeyId",serviceKeyId);
		return "service2CategoryPage";
	}
	
	// searchByTag
	
	@RequestMapping(value="/service/showSearchPage/{serviceId}",method=RequestMethod.GET)
	public String showSearchPage(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model,@PathVariable Integer serviceId,@RequestParam(value="channel", required = false,defaultValue="smd") String channel){
		Map<String, String> deviceMap = getDeviceCapbilities(request);
		
		model.addAttribute("serviceId",serviceId);
		model.addAttribute("title", "Search");
		model.addAttribute("channel", channel);
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
		
		if(channel==null)
			 channel="smd";
		 else if(channel.contains(","))
			 {
			 	String[] s=channel.split(",");
			 	channel=s[0];
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
	
	@RequestMapping(value="main/service/ipayBillingResponse",method=RequestMethod.GET)
	public String ipayBillingResponse(HttpServletRequest request,HttpSession session, Model model){
		String encryptedString = request.getParameter("gh");
		String errorMessage = null;
		try
		{
		  Map<String, String> paramaterMap = CryptoUtils.getDecryptedString(encryptedString);
		logger.info("Response Parameters from Ipay : "+paramaterMap );
		
		String msisdn = paramaterMap.get("mn");
		String operator = paramaterMap.get("op");
		HttpSession httpSession = request.getSession();
		if(session.getAttribute("msisdn") == null)
			httpSession.setAttribute("msisdn", paramaterMap.get("mn"));
		if(session.getAttribute("msisdn") == null)
			httpSession.setAttribute("operator", request.getParameter("op"));
		
		String billingStatus = paramaterMap.get("ts");
		String failureReason = paramaterMap.get("tf");
		String requestId = paramaterMap.get("r");
		String identifier = paramaterMap.get("cc");
		
		httpSession.setAttribute("msisdn", msisdn);
		httpSession.setAttribute("identifier", identifier);
		httpSession.setAttribute("operator", operator);
		String responseCode = null;
		if(billingStatus != null && billingStatus.equals("S")){
			responseCode = "101";
		}else{
			errorMessage = billingUtils.getipayErrorMessage(paramaterMap.get("ec"));
		}
	//	int previewCount = 3;

		Respons respons= new Respons();
		
		respons.setRequestId(Long.parseLong(requestId));
		
		Enumeration<String> enumeration= request.getParameterNames();
		StringBuffer buffer=new StringBuffer("t1=t");
		while (enumeration.hasMoreElements()) {
			String param = (String) enumeration.nextElement();
			buffer.append("&"+param+"="+request.getParameter(param));
			
		}
		respons.setQueryString(buffer.toString());
		
		if(msisdn!=null)
		{
			if(!msisdn.trim().isEmpty())
		respons.setMsisdn(Long.parseLong(msisdn));
		}
		
		responsDao.save(respons);
		
		Request req=requestDao.findById(Long.parseLong(requestId)); 
		
		String redirectUrl=req.getRedirectURL();
		if(redirectUrl.contains("?"))
			redirectUrl=redirectUrl+"&responsecode="+responseCode;
		else
			redirectUrl=redirectUrl+"?responsecode="+responseCode;
		
		/*redirectUrl=redirectUrl.replaceAll("&msisdn=", "&msisdn1=");
		if(request.getParameter("msisdn")!=null && !paramaterMap.get("mn").trim().isEmpty() && !paramaterMap.get("mn").trim().equalsIgnoreCase("null"))
			redirectUrl=redirectUrl+"&msisdn="+paramaterMap.get("mn");
		else if(req.getMsisdn()!=null)
			redirectUrl=redirectUrl+"&msisdn="+req.getMsisdn();
		if(request.getParameter("operator")!=null)
			redirectUrl=redirectUrl+"&operator="+paramaterMap.get("op");*/
		
		return "redirect:"+redirectUrl;
		}
		/*catch (CryptoException e)
		{
		  e.printStackTrace();
		}*/catch (Exception e)
		{
			  e.printStackTrace();
		}
		return "";//return to home page
	}
	private String setMesisdnOperator(String respCode,HttpServletRequest request){
	//	String x = (String)request.getAttribute("name");
		logger.info("response data:"+respCode);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("identifier", respCode);
		try {
			if(respCode != null){
				
				Map<String, String> paramaterMap = CryptoUtils.getDecryptedString(respCode);
				String msisdn = paramaterMap.get("mn");
				logger.info("msisdn:"+msisdn+" operator:"+paramaterMap.get("op"));
				httpSession.setAttribute("msisdn", msisdn);
				httpSession.setAttribute("operator", paramaterMap.get("op"));
				
				return msisdn;
			}
		} catch (CryptoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}
