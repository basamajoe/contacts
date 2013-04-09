package com.javalabs.DAO;

import java.util.List;

import java.sql.SQLException;


public interface DAO<T> {
	
	public T select(T t) throws SQLException;
	public T insert(T t) throws SQLException;
	public T update(T t) throws SQLException;
	public T delete(T t) throws SQLException;
	public List<T> getAllDetails() throws SQLException;;
}
