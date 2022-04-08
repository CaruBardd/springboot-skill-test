package com.springtest.security.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springtest.security.enums.SecRoleNameEnum;
import com.springtest.security.models.SecRoleModel;

@Repository
public interface ISecRoleRepo extends JpaRepository<SecRoleModel, Integer>{

	Optional<SecRoleModel> findByRoleName(SecRoleNameEnum rolName);
	
}
