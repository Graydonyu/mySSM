package com.ygd.SSM2.mapper;

import java.util.List;

import com.ygd.SSM2.dto.EmployeeWithDep;

import tk.mybatis.mapper.common.Mapper;

public interface EmployeeMapper extends Mapper<EmployeeWithDep> {
	public List<EmployeeWithDep> getEmpWithDep();
}