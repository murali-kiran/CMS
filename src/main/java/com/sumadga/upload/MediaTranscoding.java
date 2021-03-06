package com.sumadga.upload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sumadga.utils.ApplicationProperties;

@Component
public class MediaTranscoding {

Logger logger = Logger.getLogger(MediaTranscoding.class);
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public Boolean transCoding(String[] cmdArray) throws Exception{
		Boolean result=true;
		logger.info("Starting   "); 
		String command="";
		for (String s : cmdArray) 
			command = command + s + " ";
		
		logger.info("Command "+command);
		
		
		Process process = null;
		
		try {
			process = Runtime.getRuntime().exec(cmdArray);
		} catch(IOException e) {
			result = false;
			throw new IOException();
		}
		
		if(logger.isDebugEnabled()){
			BufferedReader outReader = null;
			BufferedReader errReader = null;
			try{
				outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				
				String outLine = null;
				String errLine = null;
				
				while ((outLine = outReader.readLine()) != null | (errLine = errReader.readLine()) != null) {
					if (outLine != null)
						logger.debug("Command : out: " + outLine);

					if (errLine != null)
						logger.debug("Command : err: " + errLine);
				}
			}catch (Exception e) {
				logger.warn("Exception ignored : "+e.getMessage());
			}finally{
				try{
				if(outReader!=null)
					outReader.close();
				if(errReader!=null){errReader.close();}
				}catch(Exception e){}
			}
			}
		try {
			process.waitFor();
			logger.info("Done. ");
			
			if (process.exitValue() != 0)
			{
				result = false;
				logger.error(" Transcoding failed, exit code is "+process.exitValue());
				throw new Exception();
			}
		} catch (InterruptedException e) {
			result = false;
			logger.error(" Transcoding failed due to interrupted ");
			throw new InterruptedException();		
			}
		
		return result;
	}
	
	public Boolean videoTransCoding(String sourceVideo,  
			String destinationVideo, String size ,Integer bitrate,String mimeType) throws Exception{
		
		if(sourceVideo.equals(destinationVideo))
			return true;
		
		if(!applicationProperties.getIsVideoTranscodingEnabled())
		   return false;
		
		Boolean result=true;
				
		String[] cmdArray = new String[] {
				applicationProperties.getVideoTranscodingScriptPath(),
			sourceVideo,
			destinationVideo,
			size, 
			bitrate + "k",
			mimeType 
		};
				
		logger.info("Starting   "); 
		String command="";
		for (String s : cmdArray) 
			command = command + s + " ";
		
		logger.info("Command "+command);
		
		
		Process process = null;
		
		try {
			process = Runtime.getRuntime().exec(cmdArray);
		} catch(IOException e) {
			result = false;
			throw new IOException();
		}
		
		try {
			process.waitFor();
			logger.info("Done. ");
			
			if (process.exitValue() != 0)
			{
				result = false;
				logger.error("Video Transcoding failed, exit code is "+process.exitValue());
				throw new Exception();
			}
		} catch (InterruptedException e) {
			result = false;
			logger.error("Video Transcoding failed due to interrupted ");
			throw new InterruptedException();		
			}
		
		return result;
	}

	public File imageTransCoding(String sourceImage,String destinationImage,
			String imageType,Integer width,Integer height)
	{ 
		if(sourceImage.equals(destinationImage))
			return null;
		File file=null;
		try{ 
			File imgfile=new File(sourceImage);
			Image img=javax.imageio.ImageIO.read(imgfile).getScaledInstance(width,height, Image.SCALE_SMOOTH); 
			BufferedImage bufferedImage=new java.awt.image.BufferedImage(width, width, java.awt.image.BufferedImage.TYPE_INT_RGB);
			bufferedImage.createGraphics().drawImage(img, 0, 0,null); 
			FileOutputStream fos=new FileOutputStream(destinationImage); 
			// javax.imageio.ImageIO.write(bufferedImage, "png", fos);
			javax.imageio.ImageIO.write(bufferedImage, imageType, fos);
			fos.close(); 
			file=new File(destinationImage);
		}
		catch(IOException e){ 
			logger.error("error while image conversion "+e.toString()); 
			
		} 
		return file; 

	}
}
