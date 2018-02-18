/**   
* @Description: 部门业务实现类 
* @author ygd  
* @date 2018年2月18日  
*/ 
package com.ygd.SSM2.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ygd.SSM2.entity.Department;
import com.ygd.SSM2.mapper.DepartmentMapper;
import com.ygd.SSM2.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> getAllDep() {
		return departmentMapper.selectAll();
	}
	
}
