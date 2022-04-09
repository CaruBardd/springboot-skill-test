package com.springtest.security.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springtest.security.models.SecUserModel;

@Repository
public interface ISecUserRepo  extends JpaRepository<SecUserModel, Integer>{
	
	Optional<SecUserModel> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
}
