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

/**
 * Servlet implementation class Chart
 */
@WebServlet("/chart")
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String[] data;
		
		//Put in chart
		data = readFile("data/data.txt");
		ArrayList<String> categories = new ArrayList<String>();
		//get Chart categories
		for(String run : data){
			String[] items = run.split(",");
			for(String item : items){
				String name = item.split(":")[0];
				if(!categories.contains(name)){
					categories.add(name);
				}
			}
		}
		int numberOfCategories = categories.size();
		int numberOfTrials = data.length;
		String[][] chartData = new String[numberOfTrials+1][numberOfCategories];
		for(int i = 0; i < numberOfCategories; i++){
			chartData[0][i] = categories.get(i);
		}
		for(int trial = 0; trial < numberOfTrials; trial++){
			String[] items = data[trial].split(",");
			for(String item : items){
				String[] itemSplit = item.split(":");
				int index = categories.indexOf(itemSplit[0]);
				switch(itemSplit[1]){
				case "C":
					chartData[trial+1][index] = "C," + item;
					break;
				case "F":
					chartData[trial+1][index] = "F," + item;
					break;
				case "T":
					chartData[trial+1][index] = itemSplit[2];
					break;
				case "I":
					chartData[trial+1][index] = itemSplit[2];
					break;
				}
			}
		}
		
		out.println("<table>");
		for(int trial = 0; trial < numberOfTrials+1; trial++){
			out.println("<tr>");
			for(int category = 0; category < numberOfCategories; category++){
				out.println("<td>");
				String item = chartData[trial][category];
				if(item == null){
					item = "";
					chartData[trial][category] = "";
				}
				if(item.startsWith("C,")){
					out.println("<button class=\"button\" onclick=\"matchInfo = " + chartData[trial][0] + ";dataType = '" + item.split(",")[1].split(":")[0] + "'\">Chart</button>");
				}else if(item.startsWith("F")){
					out.println("<a class=\"button\"  target=\"_blank\" href=\"/file?name=" + item.split(",")[1].split(":")[2] + "\">Image/Video</a>");
				}else{
					out.println("<p>" + chartData[trial][category] + "</p>");
				}
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.close();
	}

	private String[] readFile(String filename){
		try{
			FileReader fileReader = new FileReader(filename);
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
        	List<String> lines = new ArrayList<String>();
        	String line = null;
        	while ((line = bufferedReader.readLine()) != null) {
        		if(!line.equals("")){
        			lines.add(line);
        		}
        	}
        	bufferedReader.close();
        	return lines.toArray(new String[lines.size()]);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
