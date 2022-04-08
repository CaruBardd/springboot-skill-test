package com.springtest.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.security.enums.SecRoleNameEnum;
import com.springtest.security.models.SecRoleModel;
import com.springtest.security.repositorys.ISecRoleRepo;

@Service
@Transactional
public class SecRoleService {

	@Autowired
	ISecRoleRepo rolRepo;
	
	public Optional<SecRoleModel> getByRoleName(SecRoleNameEnum roleName) {
		return rolRepo.findByRoleName(roleName);
	}
	
	
	
}
