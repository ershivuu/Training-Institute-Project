package com.genpact.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import com.genpact.dao.StudentDAO;
import com.genpact.pojo.Request;
import com.genpact.pojo.Student;

@Service
public class StudentDAOImpl implements StudentDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean checkStudent(Student student) {
		
		String query = "SELECT * FROM student WHERE userID = ? "
				+ "and studentPassword = ?";
		
		try {
			Student ref = jdbcTemplate.queryForObject(query, 
					new BeanPropertyRowMapper<Student>(Student.class)
					, new Object[] {student.getUserID() ,
							student.getStudentPassword()});
			
			if(ref != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public boolean updateStudent(Student student) {
		
		String query = "UPDATE student SET emailID = ? , "
+ "studentName = ? , qualification = ?, number = ?, address = ? "
+ "WHERE userID= ?";
		
		try {
			int count = jdbcTemplate.update(query, 
					new Object[] {student.getEmailID() , 
			student.getStudentName() , student.getQualification(),
			student.getNumber() , student.getAddress() ,
			student.getUserID()});
			
			if(count > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean addNewStudent(Student student) {
		
		String query = "INSERT INTO student values (?,?,?,?,?,?,?)";
		
		try {
			int count = jdbcTemplate.update(query, 
					new Object[] {student.getUserID() , 
			student.getEmailID() , student.getStudentName(),
			student.getQualification() , student.getStudentPassword(),
			student.getNumber() , student.getAddress()});
			
			if(count > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public List<Student> getAllStudents() {
		
		List<Student> lst = new ArrayList<>();
		
		String query = "SELECT * FROM student";
		
		try {
			lst = jdbcTemplate.query(query, 
				new BeanPropertyRowMapper<Student>(Student.class));
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
			return lst;
		}
	}



	@Override
	public Student getStudentDetail(String userID) {
		
		String query = "SELECT * FROM student WHERE userID = ?";
		
		try {
			Student ref = jdbcTemplate.queryForObject(query, 
					new BeanPropertyRowMapper<Student>(Student.class)
					, new Object[] {userID});
			
			if(ref != null)
				return ref;
			else
				return new Student();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Student();
		}
}



	@Override
	public List<Request> viewAllResponse(String userID) {
		
		List<Request> lstList = new ArrayList<Request>();
		
		String query = "SELECT * FROM admissionrequest "
				+ "WHERE userID = ?";
		
		try {
			
			lstList = jdbcTemplate.query(query,
					new BeanPropertyRowMapper<Request>(Request.class)
					, new Object[] {userID});
			
			return lstList;
			
		} catch (Exception e) {
			e.printStackTrace();
			lstList.clear();
			return lstList;
		}
	}
	
}











