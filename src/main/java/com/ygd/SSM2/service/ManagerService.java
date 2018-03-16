/**   
* @Description: 管理员Service 
* @author ygd  
* @date 2018年3月11日  
*/ 
package com.ygd.SSM2.service;

import java.util.List;

import com.ygd.SSM2.entity.Manager;

public interface ManagerService {
	public void insertManager(Manager manager);
	
	public void deleteManagerBatch(int[] manIds);
	
	public void deleteManager(Integer manId);
	
	public void updateManager(Manager manager);
	
	public List<Manager> getManagerList(Integer pageNum,Integer pageSize,String serach);
	
	public Boolean getValidateLogin(String manName,String manPassword);
}
