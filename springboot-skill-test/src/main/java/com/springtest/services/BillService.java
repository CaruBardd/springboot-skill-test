package com.springtest.services;

import java.util.List;
import java.util.Optional;

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
		bill.setTotalAmount(totalAmount);
		bill.setUser(user);
		bill.setId(id);
		bill.setDescription(desc);
		repo.save(bill);
	}
	
	public List<BillModel> listBills() {
		return repo.findAll();
	}
	
	public List<BillModel> findBills(UserModel user){
		return repo.findByUser(user);
	}
	
	public BillModel findBillId(long id) {
		Optional<BillModel> bill = repo.findById(id);
		BillModel res = bill.get();
		return res;
	}
	
	public void updateBill(long id, UserModel user, double totalAmount, String desc) {
		BillModel bill = repo.findById(id).get();
		bill.setTotalAmount(totalAmount);
		bill.setUser(user);
		bill.setDescription(desc);
		repo.save(bill);
	}
	
	public void deleteBill(long id) {
		repo.deleteById(id);
	}
}
