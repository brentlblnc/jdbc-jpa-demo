package com.brent.jdbc.jdbcdemo.spingdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brent.jdbc.jdbcdemo.entity.Person;

public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {

}
