package org.java.api.restapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerModel {
	private String name;
	private String address;
	private int phone;
	private int id;
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public int getPhone() {
		return phone;
	}
	public int getId() {
		return id;
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
	public void setId(int id) {
		this.id = id;
	}
	

}
