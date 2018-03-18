/**   
0* @Description: 管理员控制器 
* @author ygd  
* @date 2018年3月16日  
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
import com.ygd.SSM2.entity.Manager;
import com.ygd.SSM2.service.ManagerService;
import com.ygd.SSM2.util.Msg;

@Controller
@RequestMapping(value = "/Manager")
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	/**  
	* @Title: login  
	* @Description: 登陆验证
	* @param manName
	* @param manPassword
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Msg login(@RequestParam(value = "manName")String manName,@RequestParam(value = "manName")String manPassword){
		
		Boolean login = managerService.getValidateLogin(manName, manPassword);
		
		if(login){
			return Msg.success();
		}else{
			return Msg.fail();
		}
		
	}
	
	/**  
	* @Title: managerListByPage  
	* @Description: 显示所有管理员列表
	* @param pageNum
	* @param pageSize
	* @param serach
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Msg managerListByPage(@RequestParam(value = "pageNum",defaultValue ="1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue ="10")Integer pageSize,
			@RequestParam(value = "serach",required = false)String serach){
		
		List<Manager> list = managerService.getManagerList(pageNum, pageSize, serach);
		
		PageInfo<Manager> pageInfo = new PageInfo<>(list);
		
		return Msg.success().add("pageInfo", pageInfo);		
	}
	
	/**  
	* @Title: updateManager  
	* @Description: 更新管理员信息
	* @param manager
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/upMana",method = RequestMethod.PUT)
	public Msg updateManager(Manager manager){
		
		managerService.updateManager(manager);
		
		return Msg.success();	
	}
	
	/**  
	* @Title: insertManager  
	* @Description: 插入管理员
	* @param manager
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/addMana",method = RequestMethod.POST)
	public Msg insertManager(Manager manager){
		
		managerService.insertManager(manager);
		
		return Msg.success();
	}
	
	/**  
	* @Title: deleteManager  
	* @Description: 批量删除/删除
	* @param ids
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/deleteManas/{ids}",method = RequestMethod.DELETE)
	public Msg deleteManager(@PathVariable(value = "ids")String ids){
		
		if(ids.contains("-")){
			String[] mana_ids = ids.split("-"); 
			List<Integer> list = new ArrayList<Integer>();
			
			for(String mana_id:mana_ids){
				int manaId =  Integer.parseInt(mana_id);
				list.add(manaId);
			}
			
			managerService.deleteManagerBatch(list);
			
		}else{
			int manaId = Integer.parseInt(ids);
			
			managerService.deleteManager(manaId);
		}
		
		return Msg.success();
	}
}
