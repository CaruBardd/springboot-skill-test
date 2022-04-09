package com.springtest.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.springtest.security.enums.SecRoleNameEnum;

@Entity
public class SecRoleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SecRoleNameEnum roleName;
	
	
	public SecRoleModel() {
		super();
	}

	public SecRoleModel(SecRoleNameEnum roleName) {
		super();
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SecRoleNameEnum getRoleName() {
		return roleName;
	}

	public void setRoleName(SecRoleNameEnum roleName) {
		this.roleName = roleName;
	}
	
	
	
}
	