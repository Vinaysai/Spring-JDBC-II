package com.spring.boot.all.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.all.beans.Student;
import com.spring.boot.all.dao.DaoClass;
import com.spring.boot.all.dao.DaoImplClass;
import com.spring.boot.all.util.ServiceError;

@RestController
public class Test {

	@Autowired
	private DaoClass daoClass;

	@PutMapping("insert")
	public int insert(@RequestBody Student stu) {

		int a = daoClass.save(stu);

		if (a != 0) {
			System.out.println(a + "Inserted");
		}
		return a;

	}

	@GetMapping("/read/{id}")
	public Student getById(@PathVariable Integer id) {

		Student st = daoClass.getById(id);

		if (st != null) {
			System.out.println("Read");
		}

		return st;
	}

	@PostMapping("/insertnew")
	public String insertnew(@RequestBody Student stu) {

		String st = daoClass.update(stu);

		if (st.equals(null)) {
			System.out.println("Fail");
		}

		return st;
	}

	@PostMapping("/readall")
	public void readall(@RequestBody List<Student> pairs) {

		daoClass.batch((List<Student>) pairs);

	}

	@DeleteMapping("/deleteid/{id}")
	public void delete(@PathVariable Integer id) {
		daoClass.deleteById(id);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceError> error(RuntimeException ex) {

		ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.OK);

	}

	@GetMapping("/test")
	public @ResponseBody Object test() {
		throw new DataAccessException("test for exception") {

		};
	}

}
