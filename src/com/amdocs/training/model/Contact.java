package com.amdocs.training.model;

public class Contact extends User {
	private String msg;
	private int id;
	

	public Contact(int id, String name, String email, Long telephone, String address, String regDate, String password,
			String photo) {
		super(id, name, email, telephone, address, regDate, password, photo);
		// TODO Auto-generated constructor stub
	}

	public Contact(String msg, int id) {
		super();
		this.msg = msg;
		this.id = id;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
