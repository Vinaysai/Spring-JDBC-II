package com.spring.boot.all.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.all.beans.Student;
import com.spring.boot.all.beans.StudentMapper;

@Repository
public class DaoImplClass implements DaoClass {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private NamedParameterJdbcTemplate nametemplate;

	@Override
	public int save(Student stu) {

		String sql = "insert into spring.student (student_id,qualification,student_name) values (?,?,?)";

		Object[] args = new Object[] { stu.getId(), stu.getQualification(), stu.getName() };
		int out = template.update(sql, args);

		if (out != 0) {
			System.out.println("inserted::");
		} else {
			System.out.println("fail::");
		}
		return out;
	}

	@Override
	public Student getById(Integer id) {

		String sql = "SELECT * FROM spring.student where student_id=?;";

		Student st = template.queryForObject(sql, new Object[] { id }, new StudentMapper());

		if (st != null) {
			System.out.println("Read");
		}

		return st;
	}

	@Override
	public String update(Student stu) {

		/*
		 * SimpleJdbcInsert simpleinsert = new SimpleJdbcInsert(template);
		 * 
		 * simpleinsert.setGeneratedKeyName("id");
		 * 
		 * Map<String, Object> kv = new HashMap<>();
		 * 
		 * kv.put("name", "vinay"); kv.put("qualification", "sai");
		 * 
		 * List<String> colms = new ArrayList<>();
		 * 
		 * colms.add("name"); colms.add("qualification");
		 * 
		 * simpleinsert.setTableName("stu"); simpleinsert.setColumnNames(colms);
		 * 
		 * Number key = simpleinsert.executeAndReturnKey(kv);
		 * 
		 * System.out.println(key + "Updated:::");
		 */

		String sql = "UPDATE `spring`.`student` SET `qualification`=? , `student_name`=? WHERE `student_id`=?;";
		template.update(sql, stu.getName(), stu.getQualification(), stu.getId());
		return "pass";
	}

	@Override
	public void deleteById(Integer id) {

		Map<String, Object> m = new HashMap<>();
		m.put("id", id);

		nametemplate.update("delete from spring.student where student_id=:id ", m);

	}

	@Override
	public List<Student> readall() {

		List<Student> stu = template.query("select *from spring.student", new StudentMapper());

		System.out.println(stu);
		return stu;
	}

	@Override
	@Transactional
	public void batch(List<Student> pairs) {

		String query = "insert into spring.student (student_id,qualification,student_name) values (?,?,?)";
		List<Object[]> inputList = new ArrayList<Object[]>();
		for (Student stu : pairs) {
			Object[] tmp = { stu.getId(), stu.getName(), stu.getQualification() };

			inputList.add(tmp);
		}
		template.batchUpdate(query, inputList);
		
		throw new DataAccessException("Test exception:") {
		};
	}
}
