package com.brent.jdbc.jdbcdemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.brent.jdbc.jdbcdemo.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			
			return person;
		}
		
	}
	
	// SELECT * FROM person
	// print values of each person
	public List<Person> findAll() {
		return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
	}
	
	public Person findById(int id) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM person WHERE id=?", 
				new Object[] {id}, 
				new PersonRowMapper()
				);
	}
	
	public Person findByName(String name) {
		return jdbcTemplate.queryForObject(
				"SELECT id FROM person WHERE name=?", 
				new Object[] {name}, 
				new PersonRowMapper()
				);
	}
	
	public int deleteById(int id) {
		return jdbcTemplate.update(
				"DELETE FROM person WHERE id=?", 
				new Object[] {id} 
				);
	}
	
	public int insert(Person person) {
		return jdbcTemplate.update(
				"INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE) VALUES(?,?,?,?)", 
				new Object[] {
						person.getId(),
						person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime())
					} 
				);
	}
	
	public int update(Person person) {
		return jdbcTemplate.update(
				"UPDATE PERSON "
				+ "SET name=?, location=?, birth_date=? "
				+ "WHERE id=?", 
				new Object[] {
						person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()),
						person.getId()
					} 
				);
	}
}
