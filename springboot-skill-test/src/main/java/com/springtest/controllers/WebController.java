package com.springtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springtest.services.BillService;
import com.springtest.services.UserService;

@Controller
public class WebController {
	
	@Autowired
	private UserService user;
	@Autowired
	private BillService bill;
	
	// Test de base de datos únicamente para administradores
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/test")
	public String test(Model model) {
		user.createUser(Long.valueOf(1), "Pedrito", 30, "pedrito@yopmail.com");
		bill.createBill(Long.valueOf(1), user.findUserId(1), 10.56, "Compra");
		bill.createBill(Long.valueOf(2), user.findUserId(1), 86.54, "Compra2");
		user.createUser(Long.valueOf(2), "José", 45, "jose@yopmail.com");
		bill.createBill(Long.valueOf(3), user.findUserId(1), 56.4, "Compra3");
		bill.createBill(Long.valueOf(4), user.findUserId(2), 100.54, "Compra1");
		return "test";
	}
	
	// Muestra de listas en web para administradores y usuarios
	@GetMapping("/show")
	public String show(Model model) {
		model.addAttribute("users", user.listUsers());
		model.addAttribute("bills", bill.listBills());
		return "show";
	}
	
	// Muestra de listas por usuario para administradores y usuarios
	@GetMapping("/show/user")
	public String showUser(@RequestParam(name="id", required=false, defaultValue="0") int id, Model model) {
		if(id!=0) {
			model.addAttribute("users", user.findUserId(Long.valueOf(id)));
		} else {
			model.addAttribute("users", user.listUsers());
		}
		return "show";
	}
	
	// Muestra de listas por factura para administradores y usuarios
	@GetMapping("/show/bill")
	public String showBill(@RequestParam(name="id", required=false, defaultValue="0") int id, Model model) {
		if(id!=0) {
			model.addAttribute("bills", bill.findBillId(Long.valueOf(id)));
		} else {
			model.addAttribute("bills", bill.listBills());
		}
		return "show";
	}
	
	// Muestra de listas por factura de id de usuario para administradores y usuarios 
	@GetMapping("/showusbill")
	public String showUsBill(@RequestParam(name="id", required=false, defaultValue="0") int id, Model model) {
		
		if(id!=0) {
			model.addAttribute("users", user.findUserId(id));
			model.addAttribute("bills", bill.findBills(user.findUserId(Long.valueOf(id))));
		} 
		return "show";
	}
}
