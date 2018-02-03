package org.java.api.restapi.model;

public class AdminModel {
	private int uid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	private String name;
	private String address;
	private int phone;
	private String email;
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public int getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
