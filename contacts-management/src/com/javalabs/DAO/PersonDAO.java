package com.javalabs.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javalabs.model.PersonBean;

public class PersonDAO extends ConnectToRDBMS 
					   implements DAO<PersonBean> {

    private PreparedStatement preparedStatement;
    private ResultSet rs ;

    public PersonDAO() {
    
    }
    
    /**
     * 
     * @param id
     * @return                the person with its data or null otherwise
     * @throws SQLException
     */
    @Override
    public PersonBean select(PersonBean person) throws SQLException {
        
        try {
            connection = getConnection();
            preparedStatement = connection.
                    prepareStatement("select * from t_person where id=?");
            preparedStatement.setLong(1, person.getId());
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
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
System.out.println("PDao(upd): " + person.getId() + person.getFirstName() + person.getLastName()+person.getDob());
        return person;
    }
    
    /**
     * 
     * @param person
     * @throws SQLException
     */
    @Override
    public PersonBean insert(PersonBean person) throws SQLException {
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
            e.printStackTrace();
            throw e;
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        
        return person;
    }

    /**
     * 
     * @param person
     * @throws SQLException
     */
    @Override
    public PersonBean update(PersonBean person) throws SQLException {
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
        	e.printStackTrace();
            throw new SQLException(e);
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        
        return person;
    }

    /**
     * 
     * @param id
     * @throws SQLException
     */
    @Override
    public PersonBean delete(PersonBean person) throws SQLException {
        try {
            connection = getConnection();
            preparedStatement = connection
                    .prepareStatement("delete from t_person where id=?");
            // Parameters start with 1
            preparedStatement.setLong(1, person.getId());
            preparedStatement.executeUpdate();
            person.setId(0);

        } catch (SQLException e) {
        	e.printStackTrace();
            throw new SQLException(e);
        }finally{
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        
        return person;
    }
    
    /**
     * 
     * @return                an abstract list of all the persons
     * @throws SQLException
     */
    @Override
    public List<PersonBean> getAllDetails() throws SQLException {
        
        List<PersonBean> persons = new ArrayList<PersonBean>();
        Statement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from t_person");
            
            while (rs.next()) {
                PersonBean person = new PersonBean();
                person.setId(rs.getInt("id"));
                person.setFirstName(rs.getString("firstname"));
                person.setLastName(rs.getString("lastname"));
                person.setDob(rs.getDate("dob"));
                persons.add(person);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw e;
        } finally {
            closeResulset(rs);
            closeStatement(statement);
            closeConnection(connection);
        }

        return persons;
    }

}