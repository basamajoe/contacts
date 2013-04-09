package com.javalabs.model;

import java.util.Date;

public class PersonBean {

	private long id;
	private String firstName;
	private String lastName;
	private Date dob;
	
	public PersonBean(){
		
	}
	
	public PersonBean(Long id){
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dob=" + dob + "]";
	}

}