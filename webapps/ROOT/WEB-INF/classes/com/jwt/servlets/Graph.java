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
 * Servlet implementation class Graph
 */
@WebServlet("/graph")
public class Graph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Graph() {
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
//		ArrayList<String> categories = new ArrayList<String>();
//		//get Chart categories
//		for(String run : data){
//			String[] items = run.split(",");
//			for(String item : items){
//				String name = item.split(":")[0];
//				if(!categories.contains(name)){
//					categories.add(name);
//				}
//			}
//		}
//		int numberOfCategories = categories.size();
//		int numberOfTrials = data.length;
//		String[][] chartData = new String[numberOfTrials+1][numberOfCategories];
//		for(int i = 0; i < numberOfCategories; i++){
//			chartData[0][i] = categories.get(i);
//		}
//		for(int trial = 0; trial < numberOfTrials; trial++){
//			String[] items = data[trial].split(",");
//			for(String item : items){
//				String[] itemSplit = item.split(":");
//				int index = categories.indexOf(itemSplit[0]);
//				chartData[trial+1][index] = item;
//			}
//		}

		ArrayList<String[]> chartData = new ArrayList<String[]>();
		
		for(int i = 0; i < data.length; i++){
			chartData.add(data[i].split(","));
		}
		
		for(String[] indexData : chartData){
			if(indexData[0].equals("RIN:I:" + request.getParameter("RIN"))){
				for(String i : indexData){
					if(i.startsWith(request.getParameter("ITEM"))){
						out.println(i);
					}
				}
			}
		}
		out.close();
	}

	private String[] readFile(String filename) {
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> lines = new ArrayList<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if(line != ""){
        			lines.add(line);
        		}
			}
			bufferedReader.close();
			return lines.toArray(new String[lines.size()]);
		} catch (Exception e) {
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
