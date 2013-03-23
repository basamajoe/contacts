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

	private HttpServletRequest request;
	private Person person= null;
	private PersonDAO personDAO = null;
	private String method = null;
	private String path =null;
	
	public PersonService() {
		
	}
	
	public PersonService(HttpServletRequest request) {
		this.request = request;
		method = request.getMethod();
		path =request.getRequestURI();
		personDAO = new PersonDAO();
	}



	@Override
	public String execute() {
		return handler();
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private String handler(){
		
		String page ="/index.jsp";
		
		if(path.contains("/new")){
			return this.addPerson();			
			
		}else if(path.contains("/delete")){
			this.delPerson();
			page ="redirect:person";
		}else if(path.contains("/update")){
			return this.updatePerson();
		}else{
			return listPersons(null);
		}
		return page;
	}
	
	private String listPersons(String pag) {
		try {
			request.setAttribute("persons", personDAO.getAllPersons());
		} catch (SQLException e) {
			request.setAttribute("message", e.getMessage());
		}
		if(pag!=null){
			return pag;
		}
		return "/index.jsp";
		
	}

	private String addPerson(){
		String page ="";
		
		if(method.equalsIgnoreCase("post")){
			try {
				personDAO.addPerson(populate());
				request.setAttribute("message", "Person add success");
				page ="redirect:person";
			} catch (SQLException e) {
				request.setAttribute("error", e.getMessage());
				page ="/jsp/gestionperson.jsp"; 
				request.setAttribute("title", "Add person");
				request.setAttribute("titleUser", "New User");
				request.setAttribute("urls", "person/new");
			}
			
		}else{
			page ="/jsp/gestionperson.jsp"; 
			request.setAttribute("title", "Add person");
			request.setAttribute("titleUser", "New User");
			request.setAttribute("urls", "person/new");
			
		}
		
		
		return page;
	}
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
	private String  updatePerson(){
		String page ="";
		if(method.equalsIgnoreCase("post")){
			
			try {
				personDAO.updatePerson((populate()));				
				request.setAttribute("message", "Person update success");
				page =listPersons("redirect:person");
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				person =populate();
				person.setId(Long.valueOf(request.getParameter("id")));
				request.setAttribute("person", person);
				page ="/jsp/gestionperson.jsp";  
				
			}
		}else{
			String [] datos =path.split("/");
			String id = datos[datos.length-1];
			if(!isNumberic(id)){
				id = request.getParameter("id");
			}
			
			if(id==null){				
				return listPersons("redirect:person");
			}
			
			try {
				request.setAttribute("person", personDAO.getPersonById(Integer.valueOf(id)));
			} catch (NumberFormatException | SQLException e) {
				request.setAttribute("error", e.getMessage());
			}
			page ="/jsp/gestionperson.jsp"; 
			request.setAttribute("urls", "person/update");
		}
		request.setAttribute("title", "Edit person");
		request.setAttribute("titleUser", "Edit User");
		request.setAttribute("urls", "person/update");
		
		return page;
	}
	
	
	private Person populate(){
		String id = request.getParameter("id");
		person = new Person();
		if(id!=null){ //new
			person.setId(Integer.valueOf(id));
		}
		person.setFirstName(request.getParameter("firstName"));
		person.setLastName(request.getParameter("lastName"));
		java.util.Date date = new java.util.Date();
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date =fecha.parse(request.getParameter("dob"));
			person.setDob(new Date(date.getTime()));
		} catch (ParseException e) {
		
		}
		
		
		return person;
	}
	
	private boolean isNumberic(String num){
		try{
			Integer.parseInt(num);
		
			return true;
		}catch(Exception e){
			System.out.println("No es numeric "+num);
			return false;
		}
	}
	

}
