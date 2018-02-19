package com.ygd.SSM2.mapper;

import java.util.List;

import com.ygd.SSM2.dto.EmployeeWithDep;
import com.ygd.SSM2.entity.Employee;

import tk.mybatis.mapper.common.Mapper;

public interface EmployeeMapper extends Mapper<Employee> {
	public List<EmployeeWithDep> getEmpWithDep();
}