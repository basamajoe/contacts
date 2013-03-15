package com.javalabs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/person.do")
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
        try {
            Connection con = ds.getConnection();
 
            Statement stmt = con.createStatement();
            String query = "select * from t_person";
            ResultSet rs = stmt.executeQuery(query);
 
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<center><h1>Person Details</h1></center>");
            out.print("<html><body>");
            out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
            out.print("<tr><th>ID</th>");
            out.print("<th>First Name</th>");
            out.print("<th>Last Name </th>");
            out.print("<th>Date of birth</th></tr>");
 
            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getLong("id") + "</td>");
                out.print("<td>" + rs.getString("firstName") + "</td>");
                out.print("<td>" + rs.getString("lastName") + "</td>");
                out.print("<td>" + rs.getDate("dob") + "</td>");
                out.print("</tr>");
            }
            out.print("</table></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}