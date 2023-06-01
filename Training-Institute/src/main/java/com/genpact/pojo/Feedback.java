package com.genpact.pojo;

import java.sql.Date;

public class Feedback 
{
	private int feedbackID;
	private int userID;
	private int instituteID;
	private String description;
	private Date feedbackDate;
	
	public Feedback() {
		super();
	}
	public int getFeedbackID() {
		return feedbackID;
	}
	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getInstituteID() {
		return instituteID;
	}
	public void setInstituteID(int instituteID) {
		this.instituteID = instituteID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
}