package com.ygd.SSM2.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ygd.SSM2.dto.EmployeeWithDep;
import com.ygd.SSM2.entity.Employee;
import com.ygd.SSM2.mapper.EmployeeMapper;
import com.ygd.SSM2.service.EmployeeService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeMapper employeeDao;

	@Override
	public List<EmployeeWithDep> getByPage(Integer page, Integer rows, String search) {
		
		PageHelper.startPage(page, rows);
		
		List<EmployeeWithDep> listemp = employeeDao.getEmpWithDep(search);
		
		return listemp;
	}

	@Override
	public void insertEmp(Employee employee) {
		employeeDao.insertSelective(employee);
	}

	@Override
	public Employee getEmployeeById(Integer empId) {
		return employeeDao.selectByPrimaryKey(empId);
	}

	@Override
	public void updateEmployeeById(Employee employee) {
		employeeDao.updateByPrimaryKeySelective(employee);
	}

	@Override
	public void deleteByBatch(List<Integer> del_ids) {
		
		Example example = new Example(Employee.class);
		
		example.createCriteria().andIn("empId", del_ids);
		
		employeeDao.deleteByExample(example);
	}

	@Override
	public void deleteItem(Integer empId) {
		employeeDao.deleteByPrimaryKey(empId);
	}

	@Override
	public Integer getEmpNameCount(String empName,Integer empId) {
		Example example = new Example(Employee.class);
		
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("empName",empName);
		
		if(empId != null){
			criteria.andNotEqualTo("empId", empId);
		}
		
		return employeeDao.selectCountByExample(example);
	}

}
