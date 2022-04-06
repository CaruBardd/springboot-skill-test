package com.springtest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.models.BillModel;
import com.springtest.models.UserModel;
import com.springtest.services.BillService;
import com.springtest.services.UserService;

@RestController
@RequestMapping("/api")
public class RestfulController {
	
	@Autowired
	private BillService billSv;
	
	@Autowired
	private UserService userSv;
	
	
	@PostMapping (value = "/create/user/{id}/{name}/{age}/{email}")
	public ResponseEntity<UserModel> createUser(
			@PathVariable("id") Integer id,
			@PathVariable("name") String name,
			@PathVariable("age") Integer age,
			@PathVariable("email") String email
			) {
		userSv.createUser(Long.valueOf(id), name, age, email);
		return new ResponseEntity<UserModel>(userSv.findUserId(Long.valueOf(id)), HttpStatus.CREATED);
	}
	
	@PostMapping (value = "/create/bill/{id}/{totalAmount}/{description}/{id_user}")
	public ResponseEntity<BillModel> createBill(
			@PathVariable("id") Integer id,
			@PathVariable("totalAmount") double totalAmount,
			@PathVariable("description") String description,
			@PathVariable("id_user") Integer id_user
			) {
		billSv.createBill(Long.valueOf(id), userSv.findUserId(Long.valueOf(id_user)), totalAmount, description);
		return new ResponseEntity<BillModel>(billSv.findBillId(Long.valueOf(id)), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/show/{id}")
	public ResponseEntity<List<BillModel>> showData(@PathVariable("id") Integer id) {
		return new ResponseEntity<List<BillModel>>(billSv.findBills(userSv.findUserId(Long.valueOf(id))), HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/bill/{id}/{totalAmount}/{description}/{id_user}")
	public ResponseEntity<BillModel> updateBill(
			@PathVariable("id") Integer id,
			@PathVariable("totalAmount") double totalAmount,
			@PathVariable("description") String description,
			@PathVariable("id_user") Integer id_user
			) {
		try {
			billSv.updateBill(Long.valueOf(id), userSv.findUserId(Long.valueOf(id_user)), totalAmount, description);
			return new ResponseEntity<BillModel>(billSv.findBillId(Long.valueOf(id)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/delete/bill/{id}")
	public ResponseEntity<Map<String, Boolean>> deteleBill(
			@PathVariable("id") Integer id
			)  {
		try {
			billSv.findBillId(Long.valueOf(id));
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", true);
			return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
