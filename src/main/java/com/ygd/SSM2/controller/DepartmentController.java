/**   
* @Description: 部门controller 
* @author ygd  
* @date 2018年2月18日  
*/
package com.ygd.SSM2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ygd.SSM2.entity.Department;
import com.ygd.SSM2.service.DepartmentService;
import com.ygd.SSM2.service.EmployeeService;
import com.ygd.SSM2.util.Msg;

@Controller
@RequestMapping(value = "/Dep")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;

	/**  
	* @Title: getAllDep  
	* @Description: 获取全部部门构建下拉选项
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/deps", method = RequestMethod.GET)
	public Msg getAllDep() {

		List<Department> deps = departmentService.getAllDep();

		return Msg.success().add("deps", deps);
	}

	/**  
	* @Title: getDepByPage  
	* @Description: 显示部门列表
	* @param page
	* @param rows
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/depList", method = RequestMethod.GET)
	public Msg getDepByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "search",required = false)String search) {
		
		List<Department> depList = new ArrayList<Department>();

		List<Department> deps = departmentService.getDepByPage(page,rows,search);
		
		//查询每个部门的人数
		for(Department dep:deps){
			Integer empSize = employeeService.getEmpCount(dep.getDepId());
			dep.setEmpSize(empSize);
			depList.add(dep);
		}
		
		PageInfo<Department> pageInfo = new PageInfo<>(depList);

		return Msg.success().add("pageInfo", pageInfo);
	}
	
	/**  
	* @Title: insertDep  
	* @Description: 保存部门信息
	* @param dep
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value="/saveDep",method=RequestMethod.POST)
	public Msg insertDep(Department dep){
		
		departmentService.insertDep(dep);
		
		return Msg.success();
	}
	
	/**  
	* @Title: updateDep  
	* @Description: 更新部门信息
	* @param dep
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value="/updateDep/{depId}",method=RequestMethod.PUT)
	public Msg updateDep(Department dep){
		
		departmentService.updateDep(dep);
		
		return Msg.success();
	}
	
	/**  
	* @Title: getDep  
	* @Description: 根据id获取部门信息
	* @param depId
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value="/getDep/{depId}",method=RequestMethod.GET)
	public Msg getDep(@PathVariable("depId")Integer depId){
		
		Department dep = departmentService.getDep(depId);
		
		return Msg.success().add("dep", dep);	
	}
	
	/**  
	* @Title: deleteDep  
	* @Description: 删除/批量删除
	* @param depIds
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value="/deleteDep/{depIds}",method=RequestMethod.DELETE)
	public Msg deleteDep(@PathVariable("depIds")String depIds){
		
		if(depIds.contains("-")){
			List<Integer> list = new ArrayList<Integer>();
			String[] id= depIds.split("-");
			
			for(String depId:id){
				list.add(Integer.parseInt(depId));
			}
			
			departmentService.deleteDeps(list);
		}else{
			departmentService.deleteDep(depIds);
		}
		
		return Msg.success();	
	}
	
	@ResponseBody
	@RequestMapping(value="/validDep",method=RequestMethod.GET)
	public Boolean validDep(@RequestParam("depName")String depName,@RequestParam(value="depId",required=false)Integer depId){
		
		Integer count = departmentService.validDep(depName,depId);
		
		if(count<1){
			return true;
		}else{
			return false;
		}
			
	}

}
