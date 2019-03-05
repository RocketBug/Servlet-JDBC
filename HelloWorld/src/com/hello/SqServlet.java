package com.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SqServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{	
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		
		
		int idVal = (int)session.getAttribute("id");
		int ageVal = (int)session.getAttribute("age");
		String firstName = (String)session.getAttribute("firstName");
		String lastName = (String)session.getAttribute("lastName");
		
		out.write("<html>");
		out.write("<head>");
		out.write("</head>");
		out.write("<body>");
		out.println("ID : "+"<h4>"+idVal+"</h4>"+"</br>");
		out.println("Age : "+"<h4>"+ageVal+"</h4>"+"<br>");
		out.println("First : "+"<h4>"+firstName+"</h4>"+"</br>");
		out.println("Last : "+"<h4>"+lastName+"</h4>"+"<br>");
		out.write("</body>");
		out.write("</html>");
		
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://localhost:3306/TEST";
		
		final String USER = "root";
		final String PASS = "admin";
		res.setContentType("text/html");
		String title = "Database Result";
		String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      
	      out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor = \"#f0f0f0\">\n" +
	         "<h1 align = \"center\">" + title + "</h1>\n");
	      
	      try {
	          // Register JDBC driver
	          Class.forName(JDBC_DRIVER);
	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	          Statement stmt = conn.createStatement();
	          
	          String insertDB = "insert into Employees"+"(id, age, first, last) values"+"(?, ?, ?, ?)";
	          PreparedStatement preparedstmt = conn.prepareStatement(insertDB);
	          preparedstmt.setInt(1, idVal);
	          preparedstmt.setInt(2, ageVal);
	          preparedstmt.setString(3, firstName);
	          preparedstmt.setString(4, lastName);
	          preparedstmt.executeUpdate();
	          
	          String sql;
	          sql = "SELECT id, first, last, age FROM Employees";
	          ResultSet rs = stmt.executeQuery(sql);

	          // Extract data from result set
	          while(rs.next()){
	             //Retrieve by column name
	             int id  = rs.getInt("id");
	             int age = rs.getInt("age");
	             String first = rs.getString("first");
	             String last = rs.getString("last");

	             //Display values
	             
	             out.println("ID: " + id +", Age: " + age + ", First: " + first + ", Last: " + last + "<br>");
	             
	          }
	          out.println("</body></html>");

	          // Clean-up environment
	          rs.close();
	          stmt.close();
	          conn.close();
	       } catch(SQLException se) {
	          //Handle errors for JDBC
	          se.printStackTrace();
	       } catch(Exception e) {
	          //Handle errors for Class.forName
	          e.printStackTrace();
	       } 
	}
}