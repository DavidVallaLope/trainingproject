package com.amdocs.training.model;

public class Course {
	private int id;
	private String name;
	private String desp;
	private int fee;
	private String resource;
	
	public Course() {
		
	}
	public Course(int id, String name, String desp, int fee, String resource) {
		super();
		this.id = id;
		this.name = name;
		this.desp = desp;
		this.fee = fee;
		this.resource = resource;
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
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
}
