/**   
0* @Description: 管理员控制器 
* @author ygd  
* @date 2018年3月16日  
*/ 
package com.ygd.SSM2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ygd.SSM2.service.ManagerService;
import com.ygd.SSM2.util.Msg;

@Controller
@RequestMapping(value = "/Manager")
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@ResponseBody
	@RequestMapping(value = "/login")
	public Msg login(@RequestParam(value = "manName")String manName,@RequestParam(value = "manName")String manPassword){
		
		Boolean login = managerService.getValidateLogin(manName, manPassword);
		
		if(login){
			return Msg.success();
		}else{
			return Msg.fail();
		}
		
	}
	
}
