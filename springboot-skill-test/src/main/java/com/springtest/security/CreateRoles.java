package com.springtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springtest.security.enums.SecRoleNameEnum;
import com.springtest.security.models.SecRoleModel;
import com.springtest.security.services.SecRoleService;

@Component
public class CreateRoles implements CommandLineRunner{
	
	@Autowired
	SecRoleService rolSv;
	
	
	@Override
	public void run(String... args) throws Exception {
		SecRoleModel rolUser = new SecRoleModel(SecRoleNameEnum.ROLE_USER);
		SecRoleModel rolAdmin = new SecRoleModel(SecRoleNameEnum.ROLE_ADMIN);
		if(!rolSv.existsRole(1))
			rolSv.createRole(rolUser);
		if(!rolSv.existsRole(2))
			rolSv.createRole(rolAdmin);		
	}
	
}
