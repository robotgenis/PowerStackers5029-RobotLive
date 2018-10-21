package com.jwt.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class Root extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Root() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String[] data;
		
		//First half of HTML
		data = Robot.readFile("libs/root.txt");
		for(String line : data){
			out.println(line);
		}
		
		//Second half of HTML
//		data = readFile("libs/root2.txt");
//		for(String line : data){
//			out.println(line);
//		}
//		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
