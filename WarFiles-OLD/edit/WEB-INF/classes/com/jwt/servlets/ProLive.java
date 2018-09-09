package com.jwt.servlets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyleConstants.CharacterConstants;
import javax.websocket.Session;

import javafx.scene.input.KeyCode;

public class ProLive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private int userIndex = 0;
	
    public ProLive() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Variables.userNumber = Variables.userNumber + 1;
		userIndex = Variables.userNumber;
		
		Variables.clientCursorIndex[userIndex] = 0;
		
		System.out.println("Player Joined (" + userIndex + ")");
		
		String html = readFILE("C:/Users/Brandon/Documents/Tomcat/MyData/ProLiveHtml.txt");
		html = html.replaceAll("@@", Variables.text);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(html);
		out.close();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		switch(type){
		case "PUT":
			int position = Integer.parseInt( request.getParameter("position"));
			String letter = request.getParameter("key");
			int keyCode = Integer.parseInt(request.getParameter("keyCode"));
			
			if (keyCode == 13){
				letter = "\n";
			}
			
			String text = request.getParameter("text");
			
			StringBuffer string = new StringBuffer(Variables.text);
			string.insert(position, letter);
			
			Variables.text = string.toString();
			Variables.clientCursorIndex[userIndex] = position + 1;
			
			out.print(formatData());
			break;
		case "GET":
			out.print(formatData());
			break;
		case "CURSOR":
			Variables.clientCursorIndex[userIndex] = Integer.parseInt(request.getParameter("position"));
			System.out.println(Variables.clientCursorIndex[userIndex]);
			out.print(formatData());
			break;
		}
		out.close();
	}
	
	private String formatData(){
		return Variables.text + "," + Variables.clientCursorIndex[userIndex];
	}
	
	private String readFILE(String file){
		String code = "";

		try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream(file);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  	code += strLine;
			  }
			  //Close the input stream
			  in.close();
		}catch (Exception e){//Catch exception if any
			return "error: " + e;
		}
		return code;
	}

}
