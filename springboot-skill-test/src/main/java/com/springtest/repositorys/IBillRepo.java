package com.springtest.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springtest.models.BillModel;
import com.springtest.models.UserModel;

@Repository
public interface IBillRepo extends JpaRepository<BillModel, Integer>{
	
	List<BillModel> findByUser(UserModel user);
	
	Optional<BillModel> findById(long id);
	
	void deleteById(long id);
	
	
	
}
