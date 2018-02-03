package org.java.api.restapi.model;

public class LoginModel {
	
	private int id;
	private String uname;
	private String pass;
	private int idofcus;
	public int getId() {
		return id;
	}
	public String getUname() {
		return uname;
	}
	public String getPass() {
		return pass;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setPass(String pass) {
		this.pass = "*****";
	}
	public void setIdofcus(int idofcus) {
		this.idofcus = idofcus;
	}
	public int getIdofcus() {
		return idofcus;
	}

}
