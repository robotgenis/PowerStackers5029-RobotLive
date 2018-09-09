package com.jwt.servlets;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
		data = readFile("libs/root.txt");
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
	
	private String[] readFile(String filename){
		try{
			FileReader fileReader = new FileReader(filename);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	List<String> lines = new ArrayList<String>();
        	String line = null;
        	while ((line = bufferedReader.readLine()) != null) {
        		lines.add(line);
        	}
        	bufferedReader.close();
        	return lines.toArray(new String[lines.size()]);
		}catch(Exception e){
			return null;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
