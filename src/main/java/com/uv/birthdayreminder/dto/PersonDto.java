package com.uv.birthdayreminder.dto;

public class PersonDto {

	private Long personId;
	private String firstname;	
	private String lastname;
	private String email;	
	private String contact;
	
	
	public PersonDto(){
		
	}
	
	public PersonDto(Long personId,String firstname, String lastname, String email, String contact) {
		super();
		this.personId = personId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.contact = contact;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	

}
