/**   
* @Description: 部门controller 
* @author ygd  
* @date 2018年2月18日  
*/ 
package com.ygd.SSM2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ygd.SSM2.entity.Department;
import com.ygd.SSM2.service.DepartmentService;
import com.ygd.SSM2.util.Msg;

@Controller
@RequestMapping(value = "/Dep")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping(value = "/deps",method = RequestMethod.GET)
	public Msg getAllDep(){
		
		List<Department> deps = departmentService.getAllDep();
		
		return Msg.success().add("deps", deps);		
	}
	
}
