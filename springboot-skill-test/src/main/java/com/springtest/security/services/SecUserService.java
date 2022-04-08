package com.springtest.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.security.models.SecUserModel;
import com.springtest.security.repositorys.ISecUserRepo;

@Service
@Transactional
public class SecUserService {

	@Autowired
	ISecUserRepo userRepo;
	
	public Optional<SecUserModel> getByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}
	
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	public void createUser(SecUserModel user) {
		userRepo.save(user);
	}
	
}
