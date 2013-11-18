package com.sumadga.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

public class CommonUtils {
	
	public static final int MEDIA_CONTENT_PRIVIEW = 1;
	public static final int MEDIA_CONTENT_NON_PRIVIEW = 2;
	
	public static Map<Integer,Integer> paginationStringToMap(String paginationStr,int typeId,int pageId){
		
		String [] ss = paginationStr.split(",");
		
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(String s1 : ss){
			String [] s2 = s1.split(":");
			
			if(Integer.parseInt(s2[0]) == typeId)
				map.put(typeId, pageId);
			else
				map.put(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
		}
		
		return map;
	}
	
	public static void AppendInfoToCsv(String filePath,String catureRequestStr){
		try
		{
			File file = new File(filePath);
		    FileWriter writer = new FileWriter(file,true);
	 
		    writer.append(catureRequestStr);
		   
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	}
	
	public static Timestamp convertDateToTimeStamp(Date date) {
		
	      return new Timestamp((new Date()).getTime());
		
	}

}
