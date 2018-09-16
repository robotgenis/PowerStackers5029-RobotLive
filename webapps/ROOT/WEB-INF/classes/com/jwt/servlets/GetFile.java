package com.jwt.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class File
 */
@WebServlet("/file")
public class GetFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String file = request.getParameter("name");
		if(file.endsWith(".jpg")){
			response.setContentType("image/jpeg");
			BufferedImage bi = null;
			try {
		    	bi = ImageIO.read(new File("files/" + file));
				OutputStream out = response.getOutputStream();
				ImageIO.write(bi, "jpeg", out);
				//out.flush();
				out.close();
			} catch (IOException e) {
			}
		}else if(file.endsWith(".mp4")){
			response.setContentType("video/mp4");
	        ServletOutputStream out = response.getOutputStream();
	        FileInputStream fin = new FileInputStream("files/" + file);

	        byte [] buf = new byte[4096];
	        int read;
	        while((read = fin.read(buf)) != -1)
	        {
	            out.write(buf, 0, read);
	        }

	        fin.close(); 
	        out.flush();
	        out.close();
		}
//		PrintWriter out = response.getWriter();
//		out.println("<html>");
//		out.println("<body>");
//		
//		
//		
//		out.println("<body>");
//		out.println("<html>");
		//out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
