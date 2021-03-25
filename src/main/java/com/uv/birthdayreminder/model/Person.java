package com.uv.birthdayreminder.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PERSON")
public class Person {
	
	@Id
	@Column(name="PERSON_ID")
	@GeneratedValue
	private Long personId;
	
	@Size(min =2, message = "Name should have atleast 2 characters")
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;	

	@NotEmpty
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CONTACT")
	private String contact;
	
	@JsonIgnore
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="FRIENDS_CONNECTION",
		joinColumns={@JoinColumn(name="PERSON_ID")},
		inverseJoinColumns={@JoinColumn(name="FRIEND_ID")})
	private Set<Person> users = new HashSet<Person>();

	@ManyToMany(mappedBy="users", fetch= FetchType.LAZY)
	private Set<Person> friends = new HashSet<Person>();

	public Person() {
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

	public Set<Person> getUsers() {
		return users;
	}

	public void setUsers(Set<Person> users) {
		this.users = users;
	}

	public Set<Person> getFriends() {
		return friends;
	}

	public void setFriends(Set<Person> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", contact=" + contact + ", users=" + users + ", friends=" + friends + "]";
	}
	
}