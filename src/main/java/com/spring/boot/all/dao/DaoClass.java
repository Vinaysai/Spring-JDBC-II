package com.spring.boot.all.dao;

import java.util.List;

import com.spring.boot.all.beans.Student;

public abstract interface DaoClass {

	// Create
	public int save(Student employee);

	// Read
	public Student getById(Integer id);

	// Update
	public String update(Student employee);

	// Delete
	public void deleteById(Integer id);

	
	public List<Student> readall();

	//Student batch(List<Object> pairs);

	void batch(List<Student> pairs);
}
