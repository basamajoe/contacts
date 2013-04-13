package com.javalabs.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class Handler {

	private HttpServletRequest request;
	private String context = "";	/* Path of the context*/
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

	public String getContext() {
		return context;
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
		
	private void parseRequest(){
		
		/* default options */
		this.entity = "";
		this.action = "lst";
		this.id = 0L;
		this.method = "GET";
		
		String uri = request.getRequestURI();
		this.method = request.getMethod();
		
		this.path = parseUri(uri);
		
		this.pathOK = this.mapper(path);
		this.pathKO = "/jsp/persons.jsp";
System.out.println("Handler(parseReq):" + pathOK + " - " + pathKO);
	}
	
	private String mapper(String path){
		String newPath;
		
		// Get the inputStream --> This time we have specified the folder name too.  
	    InputStream inputStream = this.getClass().getClassLoader()
                  .getResourceAsStream("/properties/mapping.properties");
System.out.println("Handler(req): " + path);
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
System.out.println("Handler(parseuri): " + uri);		
		/* Array organization
		 * 0 - ContextPath-context
		 * 1 - ServletPath-Entity (person)
		 * 2 - PathInfo (add, lst, del...)
		 * 3 - PathInfo (id) 
		 *
		 * Path of the request */
		
		String [] paths = u.split("/");
		
		if ( paths.length >= 1 ) {
			this.context = paths[0];
		}
		
		if ( paths.length >= 2 ) {
			this.path = "/" + paths[1];
		}
		
		if ( paths.length >= 3) {
			this.path = this.path + "/" + paths[2];
		}
		
		if ( paths.length >= 4 ){
			try {
				this.id = Long.parseLong(paths[3]);
			} catch (NumberFormatException nfe) {
				this.id = 0L;
				nfe.printStackTrace();
			}
		}
		
		if ( paths[1].contains("person")){
			
			this.entity = "person";
			
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
		}
		
System.out.println("Handler(lenght" + paths.length + "): " + path);
		return path;
	}
}