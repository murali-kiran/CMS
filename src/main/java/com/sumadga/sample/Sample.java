package com.sumadga.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
    	SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date1 = originalFormat.parse("2013-03-10 23:10:01");
    	System.out.println(dateFormat.format(date1));
		
	}

}
