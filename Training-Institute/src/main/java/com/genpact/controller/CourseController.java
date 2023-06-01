package com.genpact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.genpact.dao.CourseDAO;
import com.genpact.pojo.Course;

@Controller
public class CourseController {

	private CourseDAO daoImpl;

	@Autowired
	public void setDaoImpl(CourseDAO daoImpl) {
		this.daoImpl = daoImpl;
	}

	@RequestMapping(value = "/addCourseForm", method = RequestMethod.GET)
	public String showRegisterPage() {

		return "add-course";
	}

	@RequestMapping(value = "/registerCourse", method = RequestMethod.POST)
	public String register(Course course) {

		if (daoImpl.addNewCourse(course))
			return "addCSuccess";
		else
			return "addCError";
	}

	@RequestMapping(value = "/deleteCourseForm", method = RequestMethod.POST)
	public String showDeletePage() {

		return "delete-course";
	}

	/*
	 * @RequestMapping(value = "/deleteCourse" , method = RequestMethod.GET) public
	 * String delete(Course course) {
	 * 
	 * if(daoImpl.deleteCourse(course.getCourseID())) return "deleteInstSuccess";
	 * else return "deleteInstError"; }
	 */

	@RequestMapping(value = "/getallcoursesbyinstitute", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Course> getCourses(@RequestParam("id") String instituteId) {
		List<Course> lst = daoImpl.getAllCoursesByInstitute(Integer.parseInt(instituteId));

		return lst;
	}
}
