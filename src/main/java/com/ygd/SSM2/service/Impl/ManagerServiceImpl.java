/**   
* @Description: TODO 
* @author ygd  
* @date 2018年3月16日  
*/ 
package com.ygd.SSM2.service.Impl;

import java.util.List;

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
	public void deleteManagerBatch(int[] manIds) {
		
		Example example = new Example(Manager.class);
		
		managerMapper.deleteByExample(example);
	}

	@Override
	public void updateManager(Manager manager) {
		
		manager.setManPassword(MD5.md5(manager.getManPassword()));
		
		managerMapper.updateByPrimaryKeySelective(manager);
	}

	@Override
	public List<Manager> getManagerList(Integer pageNum,Integer pageSize,String serach) {
		
		Example example = new Example(Manager.class);
		
		if(serach != null && serach != ""){
			example.createCriteria().andEqualTo("manName", serach);
		}
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<Manager> managers = managerMapper.selectByExample(example);
		
		return managers;
	}

	@Override
	public Boolean getValidateLogin(String manName, String manPassword) {
		
		String md5Pw = MD5.md5(manPassword);
		
		Example example = new Example(Manager.class);
		
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("manName", manName);
		criteria.andEqualTo("manPassword", md5Pw);
		
		int count = managerMapper.selectCountByExample(example);
		System.out.println("count="+count);
		
		if(count != 1){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void deleteManager(Integer manId) {
		managerMapper.deleteByPrimaryKey(manId);
	}

}
