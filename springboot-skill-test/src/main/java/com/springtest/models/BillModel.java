package com.springtest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BillModel {

	@Id
	private long id;
	
	@Column(name="totalAmount")
	private double totalAmount;
	
	@Column(name="desc")
	private String desc;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserModel id_user;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public UserModel getId_user() {
		return id_user;
	}
	public void setId_user(UserModel id_user) {
		this.id_user = id_user;
	}
	
	
	
}
