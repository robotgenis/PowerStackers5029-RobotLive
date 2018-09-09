package com.jwt.servlets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Live
 */
//@WebServlet("/Live")
public class Live extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Live() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		PrintWriter out = response.getWriter();
		
		switch(type){
		case "switch":
			String name = request.getParameter("name");
			String position = request.getParameter("value");
			
			ArrayList<String> array = new ArrayList(Arrays.asList(Var.names));
			if(array.contains(name)){
				int index = array.indexOf(name);
				
				System.out.println(name + " was changed at index " + index + " to " + position);
				Var.value[index] = position;
			}
			break;
		case "get":
			
			String list = "";
			
			for(int i = 0; i < Var.names.length; i++){
				if(Var.value[i].equals("")){
					Var.value[i] = " ";
				}
				
				list += Var.names[i] + "@" + Var.value[i] + "@" + Var.type[i];
				
				if(i +1 != Var.names.length){
					list += "!";
				}
			}

			out.print(list);
			
			break;
		}
		
		out.close();
	}
}
