package com.ygd.SSM2.service;

import java.util.List;

import com.ygd.SSM2.dto.EmployeeWithDep;
import com.ygd.SSM2.entity.Employee;

public interface EmployeeService {
	
   /**  
	* @Title: getByPage  
	* @Description: 获取员工列表分页查询
	* @param page
	* @param rows
	* @return List<EmployeeWithDep> 返回类型    
	* @throws  
	*/  
	List<EmployeeWithDep> getByPage(Integer page, Integer rows, String search);
   
   /**  
	* @Title: insertEmp  
	* @Description: 插入员工
	* @param employee void 返回类型    
	* @throws  
	*/  
	void insertEmp(Employee employee);
   
   /**  
	* @Title: getEmployeeById  
	* @Description: 获取单个员工信息
	* @param empId
	* @return Employee 返回类型    
	* @throws  
	*/  
	Employee getEmployeeById(Integer empId);
   
	/**  
	* @Title: updateEmployeeById  
	* @Description: 更新员工
	* @param employee void 返回类型    
	* @throws  
	*/  
	void updateEmployeeById(Employee employee);

	/**  
	* @Title: deleteByBatch  
	* @Description: 批量删除
	* @param del_ids void 返回类型    
	* @throws  
	*/  
	void deleteByBatch(List<Integer> del_ids);

	/**  
	* @Title: deleteItem  
	* @Description: 单个删除
	* @param empId void 返回类型    
	* @throws  
	*/  
	void deleteItem(Integer empId);

	/**  
	* @Title: getEmpNameCount  
	* @Description: TODO
	* @param empName void 返回类型    
	* @throws  
	*/  
	Integer getEmpNameCount(String empName,Integer empId);
   
}
