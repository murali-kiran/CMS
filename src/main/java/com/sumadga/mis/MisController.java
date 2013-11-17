
package com.sumadga.mis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MisController {

	private static final Logger logger = Logger.getLogger(MisController.class);
	
	
	@Autowired
	MisService misService;
	
	@RequestMapping("/mis/show")
	public String showSearch(ModelMap model){
		
		logger.info("show MIS");
		
		misService.showMis(model);
		
		return "showMis";
		
	}
	
	
}


