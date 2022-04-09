package com.springtest.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springtest.models.UserModel;

@Repository
public interface IUserRepo extends JpaRepository<UserModel, Integer>{
	List<UserModel> findByName(String name);
	List<UserModel> findByEmail(String email);
	
	Optional<UserModel> findById(long id);
}
