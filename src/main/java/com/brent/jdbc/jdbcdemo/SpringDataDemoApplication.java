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
import com.brent.jdbc.jdbcdemo.jpa.PersonJpaRepository;
import com.brent.jdbc.jdbcdemo.spingdata.PersonSpringDataRepository;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {
	
	@Autowired
	PersonSpringDataRepository repo;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", repo.findAll());
		logger.info("User with ID 10002 -> {}", repo.findById(10002));
		// logger.info("ID of user with name {} -> {}", "James", dao.findByName("James"));
		repo.deleteById(10003);
		logger.info("Inserting user 10004 -> {}", repo.save(new Person("Brent", "Truro", new Date())));
		logger.info("Updating user with ID 10002 -> {}", repo.save(new Person(10002, "Dan", "Toronto", new Date())));

	}

}
