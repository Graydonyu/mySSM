/**   
0* @Description: 管理员控制器 
* @author ygd  
* @date 2018年3月16日  
*/ 
package com.ygd.SSM2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	* @param request
	* @param response
	* @param manName
	* @param manPassword
	* @param rememberme
	* @return Msg 返回类型   
	*/  
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Msg login(HttpServletRequest request,
			@RequestParam(value = "manName")String manName,
				@RequestParam(value = "manPassword")String manPassword){
		
		Boolean login = managerService.getValidateLogin(request, manName, manPassword);
		
		if(login){          			
			return Msg.success();
		}else{
			return Msg.fail();
		}
		
	}
	
	@RequestMapping(value = "/loginOut",method = RequestMethod.GET)
	public String loginOut(HttpSession session){
		
		if(session != null && session.getAttribute("man") != null){
			session.removeAttribute("manName");
		    session.invalidate();
		}
		
		return "redirect:/login.jsp";	
	}
	
	/**  
	* @Title: managerListByPage  
	* @Description: 显示所有管理员列表
	* @param pageNum
	* @param pageSize
	* @param search
	* @return Msg 返回类型    
	* @throws  
	*/  
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Msg managerListByPage(HttpServletRequest request,
			@RequestParam(value = "pageNum",defaultValue ="1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue ="10")Integer pageSize,
			@RequestParam(value = "search",required = false)String search){
		
		List<Manager> list = managerService.getManagerList(pageNum, pageSize, search);
		
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
	@RequestMapping(value = "/upMana/{manId}",method = RequestMethod.PUT)
	public Msg updateManager(HttpServletRequest request, Manager manager,@PathVariable Integer manId){
		
		//被修改者
		Manager manager1 = managerService.getManager(manId);
		//当前登录用户
		Manager manager2 = (Manager) request.getSession().getAttribute("man");
		
		if(Integer.parseInt(manager1.getManLevel()) >= Integer.parseInt(manager2.getManLevel())){
			return Msg.fail();
		}
		
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
	
	/**  
	* @Title: validaMana  
	* @Description: 查询管理员名是否重复
	* @param manName
	* @param manId
	* @return Boolean 返回类型   
	*/  
	@ResponseBody
	@RequestMapping(value = "/validaMana",method=RequestMethod.GET)
	public Boolean validaMana(@RequestParam(value = "manName")String manName,
			@RequestParam(value = "manId",required = false)Integer manId){
		
		//查询重复记录数
		int count = managerService.getValidateName(manName, manId);
		
		if(count<1){
			return true;
		}else{
			return false;
		}
					
	}
	
	/**  
	* @Title: getManager  
	* @Description: 获取单个管理员信息
	* @param manId
	* @return Msg 返回类型   
	*/  
	@ResponseBody
	@RequestMapping(value = "/getManager/{manId}",method = RequestMethod.GET)
	public Msg getManager(@PathVariable(value = "manId")Integer manId){
		
		Manager man = managerService.getManager(manId);
		
		return Msg.success().add("man", man);	
	} 
}
