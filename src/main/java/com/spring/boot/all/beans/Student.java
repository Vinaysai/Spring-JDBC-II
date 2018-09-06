package com.spring.boot.all.beans;

public class Student {

	private int id;
	private String name;
	private String qualification;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getQualification() {
		return qualification;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", qualification=" + qualification + "]";
	}

}
