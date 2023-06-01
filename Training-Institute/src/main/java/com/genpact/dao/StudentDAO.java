package com.genpact.dao;

import java.util.List;

import com.genpact.pojo.Request;
import com.genpact.pojo.Student;

public interface StudentDAO {
	
	boolean checkStudent(Student student);
	
    boolean updateStudent(Student student);

	boolean addNewStudent(Student student);
	
	List<Student> getAllStudents();
	
    Student getStudentDetail(String studentID);
    
    List<Request> viewAllResponse(String studentID);
}
