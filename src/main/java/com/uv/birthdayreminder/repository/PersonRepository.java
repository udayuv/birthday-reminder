package com.uv.birthdayreminder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uv.birthdayreminder.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	Optional<Person> findByPersonId(Long id);
	Optional<Person> findByEmail(String email);

}
