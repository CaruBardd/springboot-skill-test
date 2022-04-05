package com.springtest.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtest.models.BillModel;

public interface BillRepo extends JpaRepository<BillModel, Integer>{

}
