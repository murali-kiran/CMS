package com.sumadga.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	
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

}
