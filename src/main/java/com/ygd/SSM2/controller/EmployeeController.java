package com.ygd.SSM2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ygd.SSM2.dto.EmployeeWithDep;
import com.ygd.SSM2.entity.Employee;
import com.ygd.SSM2.service.EmployeeService;
import com.ygd.SSM2.util.Msg;

@Controller
@RequestMapping(value = "/Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@InitBinder("employee")
	private void initBinder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("emp.");
	}

	
	/**  
	* @Title: getAllList  
	* @Description: 在页面使用jstl从请求域中获取到pageInfo，但是不利于移动设备的解析
	* @param page
	* @param rows
	* @param employee
	* @param model
	* @return String 返回类型    
	* @throws  
	*/  
	@RequestMapping(value = "/allList")
	public String getAllList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows, @ModelAttribute("employee")EmployeeWithDep employee,Model model) {

		List<EmployeeWithDep> emps = employeeService.getByPage(page, rows);
		
		PageInfo<EmployeeWithDep> pageInfo = new PageInfo<>(emps);
		
		model.addAttribute("pageInfo", pageInfo);
		
		return "employee";
	}
	
	/**  
	* @Title: getListWithJson  
	* @Description: 根据条件分页查询员工信息
	* @param page
	* @param rows
	* @param employee
	* @return Msg 返回类型    
	* @throws  
	*/ 
	@ResponseBody
	@RequestMapping(value = "/emps",method = RequestMethod.GET)
	public Msg getListWithJson(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows, @ModelAttribute("employee")EmployeeWithDep employee) {

		List<EmployeeWithDep> emps = employeeService.getByPage(page, rows);
		
		PageInfo<EmployeeWithDep> pageInfo = new PageInfo<>(emps);
		
		return Msg.success().add("pageInfo",pageInfo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/addEmp",method = RequestMethod.POST)
	public Msg addEmp(Employee employee){
		
		employeeService.insertEmp(employee);
		
		return Msg.success();	
	}

	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> test() {
		Map<String, Object> map = new HashMap<>();
		map.put("姓名", "张三");
		map.put("性别", "男");
		return map;
	}
}
