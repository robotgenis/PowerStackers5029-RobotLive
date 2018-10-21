package com.jwt.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signal
 */
@WebServlet("/video")
public class Signal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		if(action.equals("create")){
			String[] lines = Robot.readFile("data/data.txt");
			int greatest = 0;
			for(String line : lines){
				String[] data = line.split(",");
				int rin = Integer.parseInt(data[0].split(":")[2]);
				if(rin > greatest) greatest = rin;
			}
			PrintWriter write = response.getWriter();
			write.print(greatest + 1);
			write.close();
		}else if(action.equals("record")){
			StaticImage.recording = true;
			StaticImage.images = new ArrayList<java.awt.Image>();
		}else if(action.equals("done")){
			StaticImage.recording = false;
			//String name = request.getParameter("name");
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
