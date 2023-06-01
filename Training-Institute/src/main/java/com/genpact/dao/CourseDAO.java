package com.genpact.dao;

import java.util.List;

import com.genpact.pojo.Course;

public interface CourseDAO {
	
	//add course
	boolean addNewCourse(Course course);
	
	//delete Course
	/*boolean deleteCourse(int courseID);*/

    List<Course> getAllCourses();    
    
    List<Course> getAllCoursesByInstitute(int instituteId);
}
