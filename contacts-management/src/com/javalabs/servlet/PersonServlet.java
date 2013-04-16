package com.javalabs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.annotation.Resource;
import javax.sql.DataSource;

import com.javalabs.helper.Helper;
import com.javalabs.helper.ICommand;

/**
 * Person Servlet
 *
 * @since 24 Mar 2012
 * @version 0.0 24 Mar 2012
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	 this.doRequest(req, resp);
    	 
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
            throws ServletException, IOException {
   	 
    	Helper helper = new Helper(req);
     	ICommand cmd = helper.getCommand();
     	String page = cmd.execute();
System.out.println("PServlet(page):" + page);
     	if (page.contains("redirect:")) {
System.out.println("PServlet: redirect");
     		resp.sendRedirect(this.resolutor(page, req));
     	} else {
System.out.println("PServlet: forward");
     		RequestDispatcher rd = req.getRequestDispatcher(page);
	    	rd.forward(req, resp);
     	}
    }
    
    private String resolutor(String pag, HttpServletRequest req) {
    	String pags = "";
        String path = req.getContextPath();
        String [] redi = pag.split(":");
        pags = path + redi[1];
System.out.println("PServlet: " + pags);
        return pags;
    }
}