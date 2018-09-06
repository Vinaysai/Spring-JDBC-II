package com.spring.boot.all.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

		Student s = new Student();
		s.setId(rs.getInt("student_id"));
		s.setName(rs.getString("qualification"));
		s.setQualification(rs.getString("student_name"));
		return s;
	}

}
