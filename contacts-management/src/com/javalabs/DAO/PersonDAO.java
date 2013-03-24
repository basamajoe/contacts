package com.javalabs.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javalabs.model.Person;

public class PersonDAO extends ConnectToRDBMS {

	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet rs ;
	

	public PersonDAO() {
	
	}

	/**
	 * 
	 * @param person
	 * @throws SQLException
	 */
	public void addPerson(Person person) throws SQLException {
		try {
			connection = getConnection();
			 preparedStatement = connection
					.prepareStatement("insert into t_person(firstname,lastname,dob) values (?, ?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setDate(3, new Date(person.getDob().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally{
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	/**
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deletePerson(int id) throws SQLException {
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("delete from t_person where id=?");
			// Parameters start with 1
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException(e);
		}finally{
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	/**
	 * 
	 * @param person
	 * @throws SQLException
	 */
	public void updatePerson(Person person) throws SQLException {
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("update t_person set firstname=?, lastname=?, dob=? " +
							"where id=?");
			// Parameters start with 1
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setDate(3, new Date(person.getDob().getTime()));
			preparedStatement.setLong(4, person.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
	}

	/**
	 * 
	 * @return				an abstract list of all the persons
	 * @throws SQLException
	 */
	public List<Person> getAllPersons() throws SQLException {
		
		List<Person> persons = new ArrayList<Person>();
		Statement statement = null;
		
		try {
			connection = getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_person");
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setFirstName(rs.getString("firstname"));
				person.setLastName(rs.getString("lastname"));
				person.setDob(rs.getDate("dob"));
				persons.add(person);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closeResulset(rs);
			closeStatement(statement);
			closeConnection(connection);
		}

		return persons;
	}

	/**
	 * 
	 * @param id
	 * @return				the person with its data or null otherwise
	 * @throws SQLException
	 */
	public Person getPersonById(int id) throws SQLException {
		
		Person person = null;
		
		try {
			connection = getConnection();
			preparedStatement = connection.
					prepareStatement("select * from t_person where id=?");
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				person = new Person();
				person.setId(rs.getInt("id"));
				person.setFirstName(rs.getString("firstname"));
				person.setLastName(rs.getString("lastname"));
				person.setDob(rs.getDate("dob"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeResulset(rs);
			closeStatement(preparedStatement);
			closeConnection(connection);
		}

		return person;
	}
}