/**   
* @Description: 部门业务实现类 
* @author ygd  
* @date 2018年2月18日  
*/ 
package com.ygd.SSM2.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ygd.SSM2.entity.Department;
import com.ygd.SSM2.mapper.DepartmentMapper;
import com.ygd.SSM2.service.DepartmentService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> getAllDep() {
		return departmentMapper.selectAll();
	}

	@Override
	public List<Department> getDepByPage( Integer page, Integer rows) {
		
		PageHelper.startPage(page,rows);
		
		List<Department> list = departmentMapper.selectAll();
		
		return list;
		
	}

	@Override
	public void insertDep(Department dep) {
		departmentMapper.insertSelective(dep);
	}

	@Override
	public void updateDep(Department dep) {
		departmentMapper.updateByPrimaryKeySelective(dep);
	}

	@Override
	public void deleteDeps(List<Integer> list) {
		
		Example example = new Example(Department.class);
		
		example.createCriteria().andIn("depId", list);
		
		departmentMapper.deleteByExample(example);
	}

	@Override
	public void deleteDep(String depIds) {
		Integer depId = Integer.parseInt(depIds);
		departmentMapper.deleteByPrimaryKey(depId);
	}

	@Override
	public Department getDep(Integer depId) {
		return departmentMapper.selectByPrimaryKey(depId);
	}

	@Override
	public Integer validDep(String depName, Integer depId) {
		
		Example example = new Example(Department.class);
		
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("depName",depName);
		
		if(depId!=null){
			criteria.andNotEqualTo("depId", depId);
		}

		Integer count = departmentMapper.selectCountByExample(example);
		
		return count;
	}
	
}
