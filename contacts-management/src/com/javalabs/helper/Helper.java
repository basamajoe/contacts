package com.javalabs.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.javalabs.services.PersonService;

public class Helper {

	private HttpServletRequest request;
	private ICommand cmd = null;	
	private String context = "";	/* Path of the context*/
	private String entity = "";		/* Entity person... */
	
	public Helper(HttpServletRequest request) {
		super();
		this.request = request;
		parse();
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
	/**
     * This method get a reference of the object regarding
     * the uri, in this case /person 
     * */
    public ICommand getCommand(){
        String path =request.getRequestURI();
        if (path.contains("/person")) {
            return person();
        } else {
            return null;
        }    
    }
    
    
    private ICommand person(){
        cmd = new PersonService(request);
        return cmd;
    }
    
	private void parse(){
		
		/* We delete first '/' -> /context/entity/action/id */
		String u = this.request.getRequestURI().substring(1);
		
		/* Array organization
		 * 0 - ContextPath-context
		 * 1 - ServletPath-Entity (person)
		 * 2 - PathInfo (add, lst, del...)
		 * 3 - PathInfo (id) 
		 *
		 * Path of the request */
		
		String [] paths = u.split("/");
		
		if ( paths.length >= 0 ) {
			this.context = paths[0];
		}
		
		if ( paths.length >= 1 ) {
			this.entity = paths[1];
		}
	}
}