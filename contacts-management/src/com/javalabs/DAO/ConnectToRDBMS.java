package com.javalabs.DAO;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectToRDBMS {
	
	
	@Resource(name = "jdbc/javalabs")
	private DataSource ds = null;
	private Connection connection = null;
	 
	public ConnectToRDBMS() {
		
	}

	protected Connection getConnection() throws SQLException {
		if(ds!=null){
			connection = ds.getConnection();
		}else{
			Context initCtx = null;
			Context envCtx;
			try {
				initCtx = new InitialContext();
				envCtx = (Context) initCtx.lookup("java:comp/env");
				ds = (DataSource) envCtx.lookup("jdbc/javalabs");
				connection =ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
		}
		
		return connection;
	}
	
	protected void closeConnection(Connection con) {
		try {
			if(con!=null){
				if(!con.isClosed()){
					con.close();
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion\n "+e.getMessage());
		}
	}
	
	protected void closeStatement(Statement stmt) {
		try {
			if(stmt!=null){
				if(!stmt.isClosed()){
					stmt.close();
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error al cerrar el statement\n "+e.getMessage());
		}
	}
	protected void closeResulset(ResultSet rs) {
		try {
			if(rs!=null){
				if(!rs.isClosed()){
					rs.close();
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Error al cerrar el Resultset\n "+e.getMessage());
		}
	}
	
	
	
	
	  
}
