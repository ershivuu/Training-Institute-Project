package com.genpact.dao;

import java.util.List;

import com.genpact.pojo.Course;
import com.genpact.pojo.Institute;
import com.genpact.pojo.Request;

public interface RequestDAO {
	
	//view all request for institute
	List<Request> viewAllRequestForInstitute(
			int instituteID);
	
	//submit Request
	boolean submitRequest(Request request);
	
	//get Request info
	Request getRequestInfo(int requestID);
	
    boolean isAlreadyRequested(Request request);
}
