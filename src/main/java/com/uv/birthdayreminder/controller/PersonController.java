package com.uv.birthdayreminder.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uv.birthdayreminder.dto.PersonDto;
import com.uv.birthdayreminder.exception.PersonNotFoundException;
import com.uv.birthdayreminder.model.Person;
import com.uv.birthdayreminder.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService pservice;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> createPerson(@Valid @RequestBody Person person){
		Person savedPerson = pservice.createFriend(person);
		/** LOCATION will be set in the headers with the new user id*/
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest() // it will consider the default path i.e. /person/create
				.path("/{id}")// it will append the user id to previous path after creating the person ie /person/create/{id}
				.buildAndExpand(savedPerson.getPersonId())
				.toUri();// will create the path with the genrated id /person/create/2
		return ResponseEntity.created(location).build();
		//return pservice.createPerson(person);
	}
	
	@GetMapping("/find/{personId}")
	public Person findPerson(@PathVariable Long personId){
		Person person = pservice.findPersonById(personId);
		if(person == null)
			throw new PersonNotFoundException("id- "+personId);
		
		//"all-persons",SERVER_PATH +"/persons"
		//retrievallperson
		//Resource<Person> resource = new Resource
		return person;
	}
	
	@GetMapping("/all")
	public List<PersonDto> getAllPerson(){
		return pservice.findAll();
	}
	
	@PostMapping("/addFriend/{personId}")
	public ResponseEntity<Object> addFriendForPerson(@PathVariable Long personId, @RequestBody Person friend){
		return pservice.addFriend(personId, friend);
	}
	
	@GetMapping("/friends/all/{personId}")
	public Set<Person> getAllFriends(@PathVariable Long personId){
		return pservice.findAllFriends(personId);
	}
	
	@DeleteMapping("/{personId}")
	public void deleteFriendOfPerson(@PathVariable Long personId){
		Person person = pservice.deleteByFriendId(personId);
		if(person == null)
			throw new PersonNotFoundException("id- "+personId);
	}
	
}
