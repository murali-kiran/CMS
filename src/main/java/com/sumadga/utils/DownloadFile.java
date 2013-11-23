package com.sumadga.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumadga.dao.MediaDownloadDao;
import com.sumadga.dao.PurchasesDao;
import com.sumadga.dto.MediaDownload;
import com.sumadga.dto.Purchas;
import com.sumadga.wap.model.MediaBean;
import com.sumadga.wap.model.PurchaseBean;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;


@SuppressWarnings("restriction")
@Component
public class DownloadFile {
	
	@Autowired
	private MediaDownloadDao mediaDownloadDao;
	@Autowired
	private PurchasesDao purchasesDao;
	
	@SuppressWarnings("restriction")
	@Autowired
	private MimetypesFileTypeMap mimeTypesMap;
	
	@Autowired
	private HttpSession session;
	

	
	Logger logger=Logger.getLogger(DownloadFile.class);
	
	public void downLoadMedia(HttpServletRequest request,HttpServletResponse response,MediaBean mediaBean,Purchas purchas,Map<String, String> deviceCapility) {
		
		String downloadId = request.getParameter("downloadId");
	    
	    MediaDownload download = new MediaDownload();
	    
	    download.setPurchas(purchas);
	    download.setMsisdn(new BigInteger((String)session.getAttribute("msisdn")));
	    
	    download.setMediaId(mediaBean.getMediaId());
	    download.setMediaContentId(mediaBean.getMediaContentId());
	    download.setServiceId(mediaBean.getServiceId());
	    download.setDownloadTime(CommonUtils.convertDateToTimeStamp(new Date()));
	    download.setModifiedTime(CommonUtils.convertDateToTimeStamp(new Date()));
	    String channel=request.getParameter("channel");
	    if(channel==null)
	    	channel="smd";
	    download.setChannel(channel);

	    download.setUserAgent(deviceCapility.get("userAgent"));
	    download.setSessionId(request.getSession().getId());
	    download.setRemarks("remarks");
	   
	    
	    
			int downloadedFileLength=0;
			Long contentLength=0l;
			
			File file = new File(mediaBean.getStoragePath());
			InputStream inputStream = null;
			ServletOutputStream servletOutputStream = null;
			try {
				
			/*	if(downloadId != null && downloadId.trim().length() != 0){
					download = mediaDownloadDao.findById(Long.parseLong(downloadId));
				}*/
				
				
				inputStream = new FileInputStream(file);
					
				if(downloadId != null && downloadId.trim().length() != 0){
					download = mediaDownloadDao.findById(Long.parseLong(downloadId));
				}
				String fileName = file.getName();
				contentLength = file.length();
				
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				
				response.addHeader("Content-Length", Long.toString(contentLength));
				response.setContentType(mimeTypesMap.getContentType(file));         
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				
				int read = 0 ;
				byte[] bytes = new byte[1024];
				servletOutputStream = response.getOutputStream();
				logger.info("Content lenght----------"+contentLength);
				while ((read = bufferedInputStream.read(bytes))!= -1) {
					servletOutputStream.write(bytes, 0, read);
					downloadedFileLength=downloadedFileLength+read;
				}
						
				servletOutputStream.flush();
				
				
				download.setIsDownloadSuccess((byte)1);
				//This will be execute if download is completed successfully....
				logger.info("--------Download Successfully-------  "+downloadId+" and  ip is "+request.getRemoteAddr());
		
		
				
			} catch (IOException e) {
				  download.setIsDownloadSuccess((byte)0);
				e.printStackTrace();
			}catch (Exception e) {
				  download.setIsDownloadSuccess((byte)1);
				e.printStackTrace();
			}finally{
				try {
				if(servletOutputStream!=null)
					servletOutputStream.close();
				
				if(inputStream!=null)
					inputStream.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			mediaDownloadDao.save(download);
			
			
		}
		
}
