package com.javalabs.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class Handler {

	private HttpServletRequest request;
	private String entity = "";		/* Entity person... */
	private String action = "lst";	/* Action to perform add, lst, upd, del */
	private Long id = 0L;			/* Entity identifier */
	private String method = "";		/* Method type of form GET, POST */
	private String path = "";		/* Path retrieved from the servlet */
	
	private String pathOK = "";		/* Path if action goes ok */
	private String pathKO = "";		/* Path if action goes ko */
	private Properties prop = new Properties();
	
	public Handler(HttpServletRequest request) {
		super();
		this.request = request;
		parseRequest();
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public String getEntity() {
		return entity;
	}

	public String getAction() {
		return action;
	}

	public Long getId() {
		return id;
	}

	public String getMethod(){
		return method;
	}
	
	public String getPath(){
		return path;
	}
	
	public String getPathOK(){
		return pathOK;
	}
	
	public String getPathKO(){
		return pathKO;
	}
	
	public void setRequest(HttpServletRequest request){
		this.request = request;
		parseRequest();
	}
	
	private String mapper(){
		String newPath;
		
		// Get the inputStream --> This time we have specified the folder name too.  
	    InputStream inputStream = this.getClass().getClassLoader()
                  .getResourceAsStream("/properties/mapping.properties");

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
	
	private void parseRequest(){
		
		/* default options */
		entity = "";
		action = "lst";
		id = 0L;
		method = "GET";
		
		String uri = request.getRequestURI();
		method = request.getMethod();
		
		/* We delete first '/' -> /conext/servlet/path */
		uri = uri.substring(1);
		
		/* Array organization
		 * 0 - ContextPath
		 * 1 - ServletPath (person)
		 * 2 - PathInfo (add, lst, del...)
		 * 3 - PathInfo (id) 
		 *
		 * Path of the request */
		String [] paths = uri.split("/");
		path = "/" + paths[1]+ "/" + paths[2];
		
		pathOK = this.mapper();
		pathKO = "/jsp/persons.jsp";
		
		if ( paths.length == 4 ){
			try {
				id = Long.parseLong(paths[3]);
			} catch (NumberFormatException nfe) {
				id = 0L;
				nfe.printStackTrace();
			}
		}
		
		if ( paths[1].contains("person")){
			if (paths[2].contains("lst")) {
				entity = "person";
				action = "lst";
			} else if (paths[2].contains("new")) {
				entity = "person";
				action = "new";
			} else if (paths[2].contains("add")) {
				entity = "person";
				action = "add";
			} else if (paths[2].contains("edt")) {
				entity = "person";
				action = "edt";
			} else if (paths[2].contains("upd")) {
				entity = "person";
				action = "upd";
			} else if (paths[2].contains("del")) {
				entity = "person";
				action = "del";
			}
		}
	}
}