package com.brent.jdbc.jdbcdemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.brent.jdbc.jdbcdemo.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {

	// Connect to DB
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("Find all persons", Person.class);
		 return namedQuery.getResultList();
	}
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}
	
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	public Person insert(Person person) {
		return entityManager.merge(person);
	}
	
	public void deleteById(int id) {
		Person person = this.findById(id);
		entityManager.remove(person);
	}
}
