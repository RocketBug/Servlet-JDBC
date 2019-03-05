package com.hello;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		
		
		int id = Integer.parseInt(req.getParameter("id"));
		int age = Integer.parseInt(req.getParameter("age"));
		String firstName = req.getParameter("first");
		String lastName = req.getParameter("last");
		
		session.setAttribute("id", id);
		session.setAttribute("age", age);
		session.setAttribute("firstName", firstName);
		session.setAttribute("lastName", lastName);
		res.sendRedirect("sq");
	}
}
