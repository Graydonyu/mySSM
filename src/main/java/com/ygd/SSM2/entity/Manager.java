package com.ygd.SSM2.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
public class Manager implements Serializable{
    @Transient
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "man_id")
    private Integer manId;

    @Column(name = "man_name")
    private String manName;

    @Column(name = "man_password")
    private String manPassword;

    @Column(name = "man_level")
    private String manLevel;
}