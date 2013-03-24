package com.javalabs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.javalabs.helper.Helper;
import com.javalabs.helper.ICommand;

/**
 * Person Servlet
 *
 * @since 24 Mar 2012
 * @version .0 24 Mar 2012
 */
@WebServlet("/person/*")
public class PersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Resource(name = "jdbc/javalabs")
    DataSource ds;
 
    public PersonServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 this.doRequest(request, response);
    	 
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	this.doRequest(request, response);
    }
    
    /**
     * Description of the method {@link URL}.
 	 * <p>
 	 * Explain the method features. 
 	 * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
   	 
    	Helper helper = new Helper(req);
    	ICommand comand = helper.getCommand();
    	String pag = comand.execute();
    	
    	if(pag.contains("redirect:")){    		
    		resp.sendRedirect(resoluctor(pag, req));
    	} else if(pag.contains("json")) {  
    		PrintWriter out = resp.getWriter();
    		resp.setContentType("application/json");
    		String xml = (String) req.getAttribute("xml");
    		out.print(xml);
    	} else if(pag.contains("xml")) {  
    		PrintWriter out = resp.getWriter();
    		resp.setContentType("application/xml");
    		String xml = (String) req.getAttribute("xml");
    		out.print(xml);
    	} else {
    		RequestDispatcher rd = req.getRequestDispatcher(pag);
    		
        	rd.forward(req, resp);
    	}
    	
    }
    
    /**
     * 
     * @param pag
     * @param req
     * @return			
     */
    private String resoluctor(String pag, HttpServletRequest req){
    	String pags = "";
    	String path = req.getContextPath();
    	String [] redi = pag.split(":");
    	pags = path + "/" + redi[1];
    	return pags;
    }
}