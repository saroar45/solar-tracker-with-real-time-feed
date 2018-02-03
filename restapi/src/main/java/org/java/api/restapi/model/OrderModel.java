package org.java.api.restapi.model;

import java.sql.Date;

public class OrderModel {
	private int id;
	private int tableNo;
	private String foods;
	private double price;
	private String payments;
	private Date date;
	private String progress;
	private String phone;
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getProgress() {
		return progress;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTable_no(int table_no) {
		this.tableNo = table_no;
	}
	public void setFoods(String foods) {
		this.foods = foods;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setPayments(String payments) {
		this.payments = payments;
	}
	public int getId() {
		return id;
	}
	public int getTable_no() {
		return tableNo;
	}
	public String getFoods() {
		return foods;
	}
	public double getPrice() {
		return price;
	}
	public String getPayments() {
		return payments;
	}
	

}
