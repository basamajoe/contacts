package com.javalabs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.javalabs.model.Person;

public class PersonDAO {

	@Resource(name="jdbc/javalabs")
	private DataSource ds;
	private Connection connection;
	
	public PersonDAO() {
		try{
			connection = ds.getConnection();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void addPerson(Person person) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into persons(firstname,lastname,dob) values (?, ?, ?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(person.getDob().getTime()));
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletePerson(int id) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from t_person where id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePerson(Person person) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update t_person set firstname=?, lastname=?, dob=? " +
							"where id=?");
			// Parameters start with 1
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(person.getDob().getTime()));
			preparedStatement.setLong(5, person.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from t_person");
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setFirstName(rs.getString("firstname"));
				person.setLastName(rs.getString("lastname"));
				person.setDob(rs.getDate("dob"));
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return persons;
	}

	public Person getPersonById(int personId) {
		Person person = new Person();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from persons where personid=?");
			preparedStatement.setInt(1, personId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				person.setId(rs.getInt("id"));
				person.setFirstName(rs.getString("firstname"));
				person.setLastName(rs.getString("lastname"));
				person.setDob(rs.getDate("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return person;
	}
}