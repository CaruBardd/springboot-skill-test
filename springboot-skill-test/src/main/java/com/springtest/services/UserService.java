package com.springtest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtest.models.UserModel;
import com.springtest.repositorys.IUserRepo;

@Service
public class UserService {
	
	@Autowired
	private IUserRepo repo;
	
	public void createUserFromJson(UserModel json) {
		repo.save(json);
	}
	
	public void createUser(long id,String name, int age, String email) {
		UserModel user = new UserModel();
		user.setId(id);
		user.setName(name);
		user.setAge(age);
		user.setEmail(email);
		repo.save(user);
	}
	
	public List<UserModel> listUsers(){
		return repo.findAll();
	}
	
	public UserModel findUserId(long id) {
		Optional<UserModel> user = repo.findById(id);
		UserModel res = user.get();
		return res;
	}
	
}
