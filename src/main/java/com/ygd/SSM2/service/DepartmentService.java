/**   
* @Description: 部门Service 
* @author ygd  
* @date 2018年2月18日  
*/ 
package com.ygd.SSM2.service;

import java.util.List;

import com.ygd.SSM2.entity.Department;

public interface DepartmentService {
	
	/**  
	* @Title: getAllDep  
	* @Description: 获取全部部门以构建下拉列表
	* @return List<Department> 返回类型    
	* @throws  
	*/  
	public List<Department> getAllDep();
 
	/**  
	* @Title: getDepByPage  
	* @Description: 显示部门列表分页
	* @param page
	* @param rows
	* @return List<Department> 返回类型    
	* @throws  
	*/  
	public List<Department> getDepByPage(Integer page, Integer rows);

	/**  
	* @Title: insertDep  
	* @Description: 保存部门信息
	* @param dep void 返回类型    
	* @throws  
	*/  
	public void insertDep(Department dep);

	/**  
	* @Title: updateDep  
	* @Description: 更新部门信息
	* @param dep void 返回类型    
	* @throws  
	*/  
	public void updateDep(Department dep);

	/**  
	* @Title: deleteDeps  
	* @Description: 批量删除部门
	* @param list void 返回类型    
	* @throws  
	*/  
	public void deleteDeps(List<Integer> list);

	/**  
	* @Title: deleteDep  
	* @Description: 删除部门
	* @param depIds void 返回类型    
	* @throws  
	*/  
	public void deleteDep(String depIds);

	/**  
	* @Title: getDep  
	* @Description: 根据id查找部门
	* @param depId
	* @return Department 返回类型    
	* @throws  
	*/  
	public Department getDep(Integer depId);

	/**  
	* @Title: validDep  
	* @Description: 验证部门名称是否重复
	* @param depName
	* @param depId
	* @return Integer 返回类型    
	* @throws  
	*/  
	public Integer validDep(String depName, Integer depId); 
	
}
