package com.sumadga.wap.billing;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.sumadga.dao.RequestDao;
import com.sumadga.dao.ResponsDao;
import com.sumadga.dto.Request;
import com.sumadga.dto.Respons;


@Controller
public class BillingController {
	
	private static Logger logger = Logger.getLogger(BillingController.class);
	
	@Autowired
	ResponsDao responsDao;
	
	@Autowired
	RequestDao requestDao;
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/service/detectMsisdn",method=RequestMethod.GET)
	public  String detectMsisdn(Model model,HttpServletRequest request){
		String requestid = request.getParameter("requestid");
		String responsecode = request.getParameter("responsecode");
		String message = request.getParameter("message");
		String operator = request.getParameter("operator");
		String mdn=request.getParameter("msisdn");
		String txnid = request.getParameter("txnid");
		String key = request.getParameter("key");
		
		String str= " Request Params are -- msisdn : "+mdn+ ",requestid : "+requestid+ ", responsecode : "+responsecode
				    + ", message : "+message+", operator :"+operator +",txnid : "+txnid
				     +", key :"+key;
		
		logger.info(str);
		
		Respons respons= new Respons();
		
		respons.setRequestId(Long.parseLong(requestid));
		respons.setQueryString(request.getQueryString());
		

		if(mdn!=null && !mdn.trim().isEmpty())
		respons.setMsisdn(Long.parseLong(mdn));
		
		
		responsDao.save(respons);
		
		Request req=requestDao.findById(Long.parseLong(requestid));
		
		String redirectUrl=req.getRedirectURL();
		if(mdn!=null && !mdn.equalsIgnoreCase("null")){
		if(redirectUrl.contains("?"))
			redirectUrl=redirectUrl+"&msisdn="+mdn+"&operator="+operator+"&detect";
		else
			redirectUrl=redirectUrl+"?msisdn="+mdn+"&operator="+operator+"&detect";
		}else{
			if(redirectUrl.contains("?"))
				redirectUrl=redirectUrl+"&detect";
			else
				redirectUrl=redirectUrl+"?detect";
		}
		return "redirect:"+redirectUrl;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/service/billing",method=RequestMethod.GET)
	public  String billingURL(Model model,HttpServletRequest request){
		String requestid = request.getParameter("requestid");
		String responsecode = request.getParameter("responsecode");
		String message = request.getParameter("message");
		String operator = request.getParameter("operator");
		String mdn=request.getParameter("msisdn");
		String txnid = request.getParameter("txnid");
		String key = request.getParameter("key");
		String productid = request.getParameter("productid");
		String amount = request.getParameter("amount");
				
		String str= " Request Params are -- msisdn : "+mdn+ ",requestid : "+requestid+ ", responsecode : "+responsecode
				    + ", message : "+message+", operator :"+operator +",txnid : "+txnid
				     +", key :"+key+", productid : "+productid+", amount : "+amount;
		
		logger.info(str);
		
		Respons respons= new Respons();
		
		respons.setRequestId(Long.parseLong(requestid));
		
		Enumeration<String> enumeration= request.getParameterNames();
		StringBuffer buffer=new StringBuffer("t1=t");
		while (enumeration.hasMoreElements()) {
			String param = (String) enumeration.nextElement();
			buffer.append("&"+param+"="+request.getParameter(param));
			
		}
		respons.setQueryString(buffer.toString());
		
		if(mdn!=null)
		{
			if(!mdn.trim().isEmpty())
		respons.setMsisdn(Long.parseLong(mdn));
		}
		
		responsDao.save(respons);
		
		Request req=requestDao.findById(Long.parseLong(requestid));
		
		String redirectUrl=req.getRedirectURL();
		if(redirectUrl.contains("?"))
			redirectUrl=redirectUrl+"&responsecode="+responsecode;
		else
			redirectUrl=redirectUrl+"?responsecode="+responsecode;
		return "redirect:"+redirectUrl;
	}
	
	@RequestMapping(value="/service/billing",method=RequestMethod.POST)
	public  String billingInPost(Model model,HttpServletRequest request){
		return billingURL(model, request);
	}
	
}
