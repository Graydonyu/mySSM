package com.ygd.SSM2.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ygd.SSM2.dto.EmployeeWithDep;
import com.ygd.SSM2.entity.Employee;
import com.ygd.SSM2.mapper.EmployeeMapper;
import com.ygd.SSM2.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeMapper employeeDao;

	@Override
	public List<EmployeeWithDep> getByPage(Integer page, Integer rows) {
		
		PageHelper.startPage(page, rows);
		
		List<EmployeeWithDep> listemp = employeeDao.getEmpWithDep();
		
		return listemp;
	}

	@Override
	public void insertEmp(Employee employee) {
		employeeDao.insertSelective(employee);
	}

}
