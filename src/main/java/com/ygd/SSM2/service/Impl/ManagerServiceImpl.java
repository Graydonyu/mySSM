/**   
* @Description: TODO 
* @author ygd  
* @date 2018年3月16日  
*/ 
package com.ygd.SSM2.service.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ygd.SSM2.entity.Manager;
import com.ygd.SSM2.mapper.ManagerMapper;
import com.ygd.SSM2.service.ManagerService;
import com.ygd.SSM2.util.MD5;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	ManagerMapper managerMapper;

	@Override
	public void insertManager(Manager manager) {
		
		manager.setManPassword(MD5.md5(manager.getManPassword()));
		
		managerMapper.insertSelective(manager);
	}

	@Override
	public void deleteManagerBatch(List<Integer> manIds) {
		
		Example example = new Example(Manager.class);
		
		example.createCriteria().andIn("manId", manIds);
		
		managerMapper.deleteByExample(example);
	}

	@Override
	public void updateManager(Manager manager) {
		
		manager.setManPassword(MD5.md5(manager.getManPassword()));
		
		managerMapper.updateByPrimaryKeySelective(manager);
	}

	@Override
	public List<Manager> getManagerList(Integer pageNum,Integer pageSize,String search) {
		
		Example example = new Example(Manager.class);
		
		if(!StringUtils.isBlank(search)){
			search = "%"+search+"%";
			example.createCriteria().andLike("manName", search);
		}
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<Manager> managers = managerMapper.selectByExample(example);
		
		return managers;
	}

	@Override
	public Boolean getValidateLogin(HttpServletRequest request,String manName, String manPassword) {
		
		String md5Pw = MD5.md5(manPassword);
		
		Example example = new Example(Manager.class);
		
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("manName", manName);
		criteria.andEqualTo("manPassword", md5Pw);
		
		List<Manager> mans = managerMapper.selectByExample(example);
		
		if(mans.size() == 1){
			//将用户存入session，并设置保存最大时长
			Manager man = mans.get(0);
			request.getSession().setAttribute("man", man);
			request.getSession().setMaxInactiveInterval(30*60); 
			return true;		
		}else{
			return false;
		}
	}

	@Override
	public void deleteManager(Integer manId) {
		managerMapper.deleteByPrimaryKey(manId);
	}

	/**  
	* @Title: getValidateName  
	* @Description: 远程验证管理员名称是否重复
	* @param manName
	* @param manId
	* @return
	* @see com.ygd.SSM2.service.ManagerService#getValidateName(java.lang.String, java.lang.Integer)  
	*/
	@Override
	public Integer getValidateName(String manName, Integer manId) {
		
		Example example = new Example(Manager.class);
		
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("manName", manName);
		
		//如果是更新则需要判断除本条外
		if(manId != null){
			criteria.andNotEqualTo("manId", manId);
		}
		
		int count = managerMapper.selectCountByExample(example);
		
		return count;
	}

	/**  
	* @Title: getManager  
	* @Description: 获取单个管理员信息
	* @param manId
	* @return
	* @see com.ygd.SSM2.service.ManagerService#getManager(java.lang.Integer)  
	*/
	@Override
	public Manager getManager(Integer manId) {

		Manager mana = managerMapper.selectByPrimaryKey(manId);
		
		return mana;
	}

}
