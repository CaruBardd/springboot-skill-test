package com.springtest.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtest.models.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Integer>{

}
