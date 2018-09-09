package com.jwt.servlets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dash
 */
@WebServlet("/Dash")
public class Dash extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dash() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Load up variables to arrays
		if(Var.loaded == false){
			String items = readFILE("C:/Users/Brandon/Documents/Tomcat/MyData/Live/data/data.txt");
			
			String[] itemArray = items.split(",");
			int itemCount = itemArray.length;
			
			Var.names = new String[itemCount];
			Var.type = new String[itemCount];
			Var.value = new String[itemCount];
			
			for(int i = 0; i < itemCount; i++){
				System.out.println(itemArray[i]);
				if(itemArray[i] == "break"){
					Var.names[i] = "";
					Var.type[i] = "break";
					Var.value[i] = "";
				}
				else
				{
					String[] item = itemArray[i].split("-");
					
					Var.names[i] = item[0];
					Var.type[i] = item[1];
					Var.value[i] = (item[1].equals("switch")) ? "false" : "";
				}
			}
			Var.loaded = true;
		}
		
		String html = readFILE("C:/Users/Brandon/Documents/Tomcat/MyData/Live/html/dash/page.txt");
	
		String htmlInputs = getItems();
		
		html = html.replaceAll("@@", htmlInputs);
		
		PrintWriter out = response.getWriter();
		
		out.print(html);
		out.close();
	}

	
	public String getItems(){
		String swit = readFILE("C:/Users/Brandon/Documents/Tomcat/MyData/Live/html/dash/switch.txt");
		String text = readFILE("C:/Users/Brandon/Documents/Tomcat/MyData/Live/html/dash/text.txt");
		
		String htmlInputs = "";
		
		for(int i = 0; i < Var.names.length; i++){

			String name = Var.names[i];
		    String type = Var.type[i];
		    String value = Var.value[i];
		    
		    System.out.println("item number " + i + " is a " + type + " and has the name of " + name + " and a value of " + value);
		    
		    String item = "";
		    
		    switch(type){
		    case "switch":
		    	item = swit;
		    	if(value.equals("true")){
		    		item = item.replaceAll("!!", "checked");
		    	}else{
		    		item = item.replaceAll("!!", "");
		    	}
		    	break;
		    case "break":
		    	item = "<br/>";
		    case "text":
		    	item = text;
		    	item = item.replaceAll("!!", value);
		    	break;
			}
		    item = item.replaceAll("@@", name);
		    htmlInputs += item;
		}
		
		return htmlInputs;
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
