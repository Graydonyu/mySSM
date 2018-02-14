package com.ygd.SSM2.entity;

import javax.persistence.*;

public class Department {
    @Id
    @Column(name = "dep_id")
    private Integer depId;

    /**
     * 部门名称
     */
    @Column(name = "dep_name")
    private String depName;

    /**
     * @return dep_id
     */
    public Integer getDepId() {
        return depId;
    }

    /**
     * @param depId
     */
    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    /**
     * 获取部门名称
     *
     * @return dep_name - 部门名称
     */
    public String getDepName() {
        return depName;
    }

    /**
     * 设置部门名称
     *
     * @param depName 部门名称
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }
}