package com.amdocs.training.model;

public class Admin {
	private int id;
	private String name;
	private String password;
	private String emai;
	
	public Admin() {
		super();
	}
	public Admin(int id, String name, String password, String emai) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.emai = emai;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmai() {
		return emai;
	}
	public void setEmai(String emai) {
		this.emai = emai;
	}
}
