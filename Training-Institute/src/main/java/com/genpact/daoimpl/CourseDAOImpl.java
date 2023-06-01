package com.genpact.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.genpact.dao.CourseDAO;
import com.genpact.pojo.Course;

@Service
public class CourseDAOImpl  implements CourseDAO{

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public boolean addNewCourse(Course course) {
		boolean b = false;
		String query = "INSERT INTO course(courseName, instituteID)"
				+ " VALUES(?,?)";
		
		try {
			int count = jdbcTemplate.execute(query, new PreparedStatementCallback<Integer>() {
				
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps) 
						throws SQLException, DataAccessException {
					
					ps.setString(1, course.getCourseName());
					ps.setInt(2, course.getInstituteID());
					
					return ps.executeUpdate();
				}
			}); 
				
			if(count > 0)
				b = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return b;
	}

	@Override
	public List<Course> getAllCourses() {
		
		List<Course> courses = new ArrayList<>();
		
		try {
			
			courses = jdbcTemplate.query("SELECT * FROM course",
					new BeanPropertyRowMapper<Course>(Course.class));
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
			courses.clear();
			return courses;
		}
	}


	@Override
	public List<Course> getAllCoursesByInstitute(int instituteId) {
		List<Course> courses = new ArrayList<>();
		
		try {
			
			courses = jdbcTemplate.query(
"SELECT * FROM course WHERE instituteID = ?",
new BeanPropertyRowMapper<Course>(Course.class),
instituteId);
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
			courses.clear();
			return courses;
		}
	}
	
/*	@Override
	public boolean deleteCourse(int courseID) {
		
		boolean b = false;
		String query = "DELETE FROM course WHERE courseID = ?";
		
		try {
			
			int count = jdbcTemplate.execute(query, new PreparedStatementCallback<Integer>() {
				
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps) 
						throws SQLException, DataAccessException {
					
					ps.setInt(1, courseID);
					
					return ps.executeUpdate();
				}
			});
			
			if(count > 0)
				b = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return b;
	}*/
	
	

}
