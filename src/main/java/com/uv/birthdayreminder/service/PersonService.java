package com.uv.birthdayreminder.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uv.birthdayreminder.dto.PersonDto;
import com.uv.birthdayreminder.exception.DuplicatePersonFoundException;
import com.uv.birthdayreminder.model.Person;
import com.uv.birthdayreminder.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	PersonRepository repo;
	
	
	/*CREATE A NEW USER*/
	
	public Person createFriend(Person model){
		Person user = new Person();
        if (repo.findByEmail(model.getEmail()).isPresent()) {
        	
            throw new DuplicatePersonFoundException("Cannot insert duplicate Record");
        } else {
            user.setFirstname(model.getFirstname());
            user.setLastname(model.getLastname());
            user.setContact(model.getContact());
            user.setEmail(model.getEmail());

            Person savedUser = repo.save(user);
            if (repo.findByPersonId(savedUser.getPersonId()).isPresent())
                return repo.findByPersonId(savedUser.getPersonId()).get();
            else return null;
        }
	}
	
	public ResponseEntity<Object> createPerson(Person model) {
		Person user = new Person();
        if (repo.findByEmail(model.getEmail()).isPresent()) {
        	
           return ResponseEntity.badRequest().body("The id is already Present, Failed to Create new User");
        } else {
            user.setFirstname(model.getFirstname());
            user.setLastname(model.getLastname());
            user.setContact(model.getContact());
            user.setEmail(model.getEmail());

            Person savedUser = repo.save(user);
            if (repo.findById(savedUser.getPersonId()).isPresent())
                return ResponseEntity.ok("User Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
        }
	}

	/**FIND ALL USERS*/
	public List<PersonDto> findAll() {
		List<PersonDto> dtoList = new ArrayList<>();
		
		for(Person p: repo.findAll()){
			dtoList.add(new PersonDto(p.getPersonId(),p.getFirstname(),p.getLastname(),p.getEmail(),p.getContact()));
		}
		return dtoList;
	}

	/**ADD A FRIEND*/
	public ResponseEntity<Object> addFriend(Long personId, Person friend) {
		Person checkFriend = createFriend(friend);
		if(checkFriend != null){
		if(repo.findByPersonId(personId).isPresent()){
			Person person = repo.findByPersonId(personId).get();
			person.getUsers().add(checkFriend);
			Person savedUser = repo.save(person);
            if(repo.findByPersonId(savedUser.getPersonId()).isPresent())
                return  ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
		}else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");		
	}

	/**FINDING ALL FRIENDS OF A FRIEND*/
	public Set<Person> findAllFriends(Long personId) {
		Person person = repo.findByPersonId(personId).get();
		Set<Person> friends = person.getUsers();
		return (friends !=null)?friends:null;
	}

	/**FINDING  A PERSON*/
	public Person findPersonById(Long personId) {
		if(repo.findByPersonId(personId).isPresent()){
			Person person = repo.findByPersonId(personId).get();
			return person;
		}
		return null;
	}

	/**Deleteing a friend by id*/
	public Person deleteByFriendId(Long pid){
		Iterator<Person> iterator = repo.findAll().iterator();
		while(iterator.hasNext()){
			Person person = iterator.next();
			if(person.getPersonId() == pid){
				iterator.remove();
				return person;
			}
		}
		return null;
	}
	

}
