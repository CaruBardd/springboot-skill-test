package com.springtest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtest.models.BillModel;
import com.springtest.models.UserModel;
import com.springtest.repositorys.IBillRepo;

@Service
public class BillService {
	
	@Autowired
	private IBillRepo repo;
	
	public void createBill(long id, UserModel user, double totalAmount, String desc) {
		BillModel bill = new BillModel();
		bill.setDesc(desc);
		bill.setTotalAmount(totalAmount);
		bill.setId_user(user);
		bill.setId(id);
		repo.save(bill);
	}
	
	public List<BillModel> showBills() {
		return repo.findAll();
	}
}
