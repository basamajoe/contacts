package com.javalabs.services;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.javalabs.DAO.PersonDAO;
import com.javalabs.helper.ICommand;
import com.javalabs.model.Person;


public class PersonService implements ICommand {

	private HttpServletRequest request = null;
	private Person person = null;
	private PersonDAO personDAO = null;
	private String method = null;
	private String path = null;
	
	public PersonService() {
		
	}
	
	/**
	 * 
	 * @param request
	 */
	public PersonService(HttpServletRequest req) {
		this.request = req;
		method = req.getMethod();
		path =req.getRequestURI();
		personDAO = new PersonDAO();
	}

	@Override
	public String execute() {
		return handler();
	}

	public void setRequest(HttpServletRequest req) {
		this.request = req;
	}
	
	private String handler(){
		
		String page = "/index.jsp";
		
		if (path.contains("person/new")) {
			return this.addPerson();
		} else if (path.contains("person/update")) {
			return this.updatePerson();
		} else if (path.contains("person/delete")) {
			this.delPerson();
			page = "redirect:person";
		} else {
			return listPersons(null);
		}
		return page;
	}
	
	/**
	 * 
	 * @param pag
	 * @return
	 */
	private String listPersons(String pag) {
		try {
			request.setAttribute("persons", personDAO.getAllPersons());
		} catch (SQLException e) {
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		if (pag != null) {
			return pag;
		}
		return "/index.jsp";
		
	}

	/**
	 * 
	 * @return
	 */
	private String addPerson(){
		String page ="";
		
		if (method.equalsIgnoreCase("post")) {
			try {
				personDAO.addPerson(populate());
				request.setAttribute("message", "Person add success");
				page ="redirect:person";
			} catch (SQLException e) {
				request.setAttribute("error", e.getMessage());
				page ="/jsp/person.jsp"; 
				request.setAttribute("title", "Add person");
				request.setAttribute("titleUser", "New User");
				request.setAttribute("urls", "person/new");
			}
			
		} else {
			page = "/jsp/person.jsp"; 
			request.setAttribute("title", "Add person");
			request.setAttribute("titleUser", "New User");
			request.setAttribute("urls", "person/new");
		}
		
		return page;
	}
	
	/**
	 * 
	 */
	private void delPerson(){
		String path =request.getRequestURI();
		String [] datos =path.split("/");
		String id = datos[datos.length-1];
		try {
			personDAO.deletePerson(Integer.parseInt(id));
			request.setAttribute("message", "Person deleted success");
		} catch (NumberFormatException | SQLException e) {
			request.setAttribute("error", e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private String  updatePerson(){
		String page = "";
		if (method.equalsIgnoreCase("post")) {
			
			try {
				personDAO.updatePerson((populate()));				
				request.setAttribute("message", "Person update success");
				page = listPersons("redirect:person");
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				person = populate();
				person.setId(Long.valueOf(request.getParameter("id")));
				request.setAttribute("person", person);
				page = "/jsp/person.jsp";
				e.printStackTrace();
			}
		} else {
			String [] datos =path.split("/");
			String id = datos[datos.length-1];
			if (!isNumberic(id)) {
				id = request.getParameter("id");
			}
			
			if (id == null) {				
				return listPersons("redirect:person");
			}
			
			try {
				request.setAttribute("person", personDAO.getPersonById(Integer.valueOf(id)));
			} catch (NumberFormatException | SQLException e) {
				request.setAttribute("error", e.getMessage());
				e.printStackTrace();
			}
			page ="/jsp/gestionperson.jsp"; 
			request.setAttribute("urls", "person/update");
		}
		request.setAttribute("title", "Edit person");
		request.setAttribute("titleUser", "Edit User");
		request.setAttribute("urls", "person/update");
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	private Person populate(){
		String id = request.getParameter("id");
		person = new Person();
		
		if (id != null) { //new
			person.setId(Integer.valueOf(id));
		}
		
		person.setFirstName(request.getParameter("firstName"));
		person.setLastName(request.getParameter("lastName"));
		java.util.Date date = new java.util.Date();
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			date = fecha.parse(request.getParameter("dob"));
			person.setDob(new Date(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return person;
	}
	
	/**
	 * This function checks if the string passed as an arg 
	 * is a number.
	 * @param num
	 * @return boolean
	 */
	private boolean isNumberic(String num){
		try {
			Integer.parseInt(num);		
			return true;
		} catch (NumberFormatException nfe) {
			System.out.println("No es numeric "+num);
			nfe.printStackTrace();
			return false;
		}
	}

}