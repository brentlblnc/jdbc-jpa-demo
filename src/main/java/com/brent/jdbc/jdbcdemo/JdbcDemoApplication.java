package com.brent.jdbc.jdbcdemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brent.jdbc.jdbcdemo.entity.Person;
import com.brent.jdbc.jdbcdemo.jdbc.PersonJdbcDao;

//@SpringBootApplication
public class JdbcDemoApplication implements CommandLineRunner {
	
	@Autowired
	PersonJdbcDao dao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", dao.findAll());
		logger.info("User with ID 10001 -> {}", dao.findById(10001));
		logger.info("ID of user with name {} -> {}", "James", dao.findByName("James"));
		logger.info("Deleting user with ID 10001 -> Number of rows deleted - {}", dao.deleteById(10001));
		logger.info("Inserting user 10004 -> {}", dao.insert(new Person(10004, "Brent", "Truro", new Date())));
		logger.info("Updating user with ID 10002 -> {}", dao.update(new Person(10002, "Johnny", "Toronto", new Date())));

	}

}
