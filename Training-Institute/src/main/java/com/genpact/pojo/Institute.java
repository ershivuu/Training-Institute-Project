package com.genpact.pojo;

import java.sql.Date;

public class Institute {
	private int instituteID;
	private String instituteName;
	private String institutePassword;
	private String address;
	private int seats;
	private Date affiliationDate;
	private int filledseats;

	public Institute() {
		super();
	}

	public int getFilledseats() {
		return filledseats;
	}


	public void setFilledseats(int filledseats) {
		this.filledseats = filledseats;
	}


	public int getInstituteID() {
		return instituteID;
	}

	public void setInstituteID(int instituteID) {
		this.instituteID = instituteID;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getInstitutePassword() {
		return institutePassword;
	}

	public void setInstitutePassword(String institutePassword) {
		this.institutePassword = institutePassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Date getAffiliationDate() {
		return affiliationDate;
	}

	public void setAffiliationDate(Date affiliationDate) {
		this.affiliationDate = affiliationDate;
	}
	
	

	
}
