package com.javalabs.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.javalabs.DAO.PersonDAO;
import com.javalabs.helper.ICommand;
import com.javalabs.model.PersonBean;

public class PersonService implements ICommand {

	private HttpServletRequest request = null;
	private PersonBean person = null;
	private PersonDAO personDAO = null;
	private Long id;
	private String action = "";
	private String method = "";
	private String path = "";
	private String pathOK = "";
	private String pathKO = "";
	
	public PersonService() {
		super();
	}
	
	public PersonService(HttpServletRequest req) {
		super();
		this.request = req;
		parseRequest();
		person = new PersonBean();
		personDAO = new PersonDAO();
	}
	
	public void setRequest(HttpServletRequest req) {
		this.request = req;
		parseRequest();
	}

	/**
	 * 
	 * @return
	 */
	private Integer newPerson(){
		
		Integer result = 0;

		request.setAttribute("title", "Create new person");
		request.setAttribute("formAction", "person/add");
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	private Integer addPerson(){
		
		Integer result = 0;

		if (this.method.equalsIgnoreCase("post")) {	/* Create new person */
			try {
				personDAO.insert(populate(this.action));
				request.setAttribute("msg", "Person added successfully!");
				request.setAttribute("title", "List of persons");
				request.setAttribute("persons", personDAO.getAllDetails());
				result = 0;
			} catch (SQLException e) {
				request.setAttribute("err", e.getMessage());
				e.printStackTrace();
				result = -1;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	private Integer lstPersons() {
	
		Integer result = 0;
		
		try {
			request.setAttribute("persons", personDAO.getAllDetails());
			request.setAttribute("title", "List of persons");
			result = 0;
		} catch (SQLException e) {
			request.setAttribute("err", e.getMessage());
			e.printStackTrace();
			result = -1;
		}
		
		return result;
		
	}

	/**
	 * 
	 * @return
	 */
	private Integer  edtPerson(){
		Integer result = 0;
		
		//if (handler.getMethod().equalsIgnoreCase("post")) {
			person = populate(this.action);
			request.setAttribute("person", person);
			request.setAttribute("title", "Edit person");
			request.setAttribute("formAction", "person/upd");
		//}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	private Integer  updPerson(){
		Integer result = 0;
		
		if (this.method.equalsIgnoreCase("post")) {
			
			try {
				person = personDAO.update(populate(this.action));
				//With redirect we lost request and attributes
				request.setAttribute("title", "List of persons");
				request.setAttribute("msg", "Person updated successfully!");
				request.setAttribute("persons", personDAO.getAllDetails());
				result = lstPersons();
			} catch (SQLException e) {
				request.setAttribute("err", e.getMessage());
				//person = populate();
				//person.setId(Long.valueOf(request.getParameter("id")));
				//request.setAttribute("person", person);
				e.printStackTrace();
				result = -1;
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @return id of the person deleted 
	 * 		   -1 if an error occur
	 */
	private Integer delPerson(){
		Integer result = 0;
System.out.println("PService(id del):" + this.id);
		if (this.id != 0){
			try {
				personDAO.delete(new PersonBean(this.id));
				request.setAttribute("msg", "Person deleted successfully!");
			} catch (SQLException e) {
				request.setAttribute("err", e.getMessage());
				e.printStackTrace();
				result = -1;
			}
		} else {
			request.setAttribute("msg", "None person was deleted.");
		}
		
		return result;
	}
	
	/**
	 * Load parameters received from the form into a person object
	 * or pathInfo
	 * 
	 * @return
	 */
	private PersonBean populate(String action){
		person = new PersonBean();
		
		if ( action == "add" ) { /* New person */
			populatePersonFromForm();
		} else if ( action == "edt" ) { /* Populate data from an already created person DB */
			person.setId(this.id);
			
			try {
				person = personDAO.select(person);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ( action == "upd" ) { /* Populate data from an fields of the form */
			
			person.setId(Long.parseLong(request.getParameter("id")));
			populatePersonFromForm();
		}
		
		return person;
	}
	
	private void populatePersonFromForm(){
		
		person.setFirstName(request.getParameter("firstName"));
		person.setLastName(request.getParameter("lastName"));
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sDate = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			date = sDate.parse(request.getParameter("dob"));
			person.setDob(new Date(date.getTime()));
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	@Override
	public String execute() {
		return handler();
	}
	
	public String handler() {
		
		String page;
		Integer result = -1;
		
		if (this.action.equals("lst")) {
			result = this.lstPersons();
		} else if (this.action.equals("new")) { /* Empty form */
			result = this.newPerson();
		} else if (this.action.equals("add")) { /* Add person data form */
			result = this.addPerson();
		} else if (this.action.equals("edt")) { /* Load data from person */
			result = this.edtPerson();
		} else if (this.action.equals("upd")) { /* Update data from person*/
			result = this.updPerson();
		} else if (this.action.equals("del")) { /* Delete person selected */
			result = this.delPerson();
		}
		
		if (result == 0) {
			page = this.pathOK; 
		} else {
			page = this.pathKO;
		}
System.out.println("PService(Execute pge>" + action + "): " + page);
		return page;
	}

	
	private void parseRequest(){
		
		/* default options */
		this.id = 0L;
		this.action = "lst";
		this.method = "GET";
		
		String uri = request.getRequestURI();
		this.method = request.getMethod();
		
		this.path = parseUri(uri);
		
		this.pathOK = this.mapper(path);
		this.pathKO = "/jsp/persons.jsp";
	}
	
	private String mapper(String path){
		
		String newPath;
		Properties prop = new Properties();
		
		// Get the inputStream --> This time we have specified the folder name too.  
	    InputStream inputStream = this.getClass().getClassLoader()
                  .getResourceAsStream("/properties/mapping.properties");
System.out.println("PService(req): " + path);
		try{
			//load a properties file
    		prop.load(inputStream);
    		newPath = prop.getProperty(path);

		} catch (FileNotFoundException fnfe) {
    		fnfe.printStackTrace();
    		System.out.println(fnfe.getCause());
        	return null;
        } catch (IOException ioe){
        	ioe.printStackTrace();
        	System.out.println(ioe.getCause());
        	return null;
        } finally {
        	//close the stream to release system resources
            try {
                if (inputStream != null) {
                	inputStream.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
		
		return newPath;
	}
	
	
	private String parseUri(String uri){
				
		/* We delete first '/' -> /context/entity/path/id */
		String u = uri.substring(1);
System.out.println("PService(parseuri): " + uri);		
		/* Array organization
		 * 0 - ContextPath-context
		 * 1 - ServletPath-Entity (person)
		 * 2 - PathInfo (add, lst, del...)
		 * 3 - PathInfo (id) 
		 *
		 * Path of the request */
		
		String [] paths = u.split("/");
				
		if ( paths.length >= 2 ) {
			this.path = "/" + paths[1];
		}
		
		if ( paths.length >= 3) {
			this.path = this.path + "/" + paths[2];
		}
		
		if ( paths.length >= 4 ){
			try {
				if (isNumeric(paths[3])){
					this.id = Long.parseLong(paths[3]);
				} else {
					this.id = 0L;
				}
				this.id = Long.parseLong(paths[3]);
			} catch (NumberFormatException nfe) {
				this.id = 0L;
				nfe.printStackTrace();
			}
		}
			
		if (paths[2].contains("lst")) {
			action = "lst";
		} else if (paths[2].contains("new")) {
			action = "new";
		} else if (paths[2].contains("add")) {
			action = "add";
		} else if (paths[2].contains("edt")) {
			action = "edt";
		} else if (paths[2].contains("upd")) {
			action = "upd";
		} else if (paths[2].contains("del")) {
			action = "del";
		}
		
		return path;
	}
	
    /**
     * This function checks if the string passed as an arg 
     * is a number.
     * @param num
     * @return boolean
     */
    private boolean isNumeric(String num){
            try {
                    Integer.parseInt(num);          
                    return true;
            } catch (NumberFormatException nfe) {
                    System.out.println("It is not numeric." + num);
                    nfe.printStackTrace();
                    return false;
            }
    }
}