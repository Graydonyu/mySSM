package com.ygd.SSM2.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
public class Department implements Serializable{
    @Transient
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "dep_id")
    private Integer depId;

    /**
     * 部门名称
     */
    @Column(name = "dep_name")
    private String depName;
    
    @Transient
    private Integer empSize;
}