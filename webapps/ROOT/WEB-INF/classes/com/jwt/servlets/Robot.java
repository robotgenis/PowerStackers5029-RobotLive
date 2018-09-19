package com.jwt.servlets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Robot
 */
@WebServlet("/r")
@MultipartConfig
public class Robot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Robot() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Recive data");

		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();

		Collection<Part> parts = request.getParts();
		for (Part filePart : parts) {
			//System.out.println(filePart.getName());
			//System.out.println(filePart.getContentType());
			switch (filePart.getContentType()) {
			case "text/plain; charset=UTF-8":
				names.add(filePart.getName());
				data.add(request.getParameter(filePart.getName()));
				break;
			case "image/jpeg":
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				
				InputStream fileContent = filePart.getInputStream();
				Image i = ImageIO.read(fileContent);
				if(filePart.getName().equals("LIVE")){
					StaticImage.live = i;
				}else{
					names.add(filePart.getName());
					data.add("F:" + fileName);
					try {
						BufferedImage bi = (BufferedImage) i;
						File outputfile = new File("files/" + fileName);
						ImageIO.write(bi, "jpeg", outputfile);
					} catch (IOException e) {

					}
				}	
				break;
			case "null":
				String vidName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				
				names.add(filePart.getName());
				data.add("F:" + vidName);
				
				InputStream vidContent = filePart.getInputStream();
				
				byte[] buffer = new byte[vidContent.available()];
			    vidContent.read(buffer);
			    
			    File targetFile = new File("files/" + vidName);
			    OutputStream outStream = new FileOutputStream(targetFile);
			    outStream.write(buffer);
			    outStream.close();
				break;
			}
		}

		// for(int i = 0; i < names.size(); i++){
		// System.out.println(names.get(i) + ":" + data.get(i));
		// }

		String[] readData = readFile("data/data.txt");
		int row = -1;

		for (int i = 0; i < readData.length; i++) {
			if (readData[i].startsWith("RIN:" + data.get(names.indexOf("RIN")))) {
				row = i;
			}
		}
		// System.out.println("-Data Row: " + row);
		if (row == -1) {
			String write = "";
			for (int i = 0; i < names.size(); i++) {
				write += names.get(i) + ":" + data.get(i);
				if (i + 1 < names.size()) {
					write += ",";
				}
			}

			try (FileWriter fw = new FileWriter("data/data.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println();
				out.print(write);
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}
		} else {
			ArrayList<String> items = new ArrayList<>(Arrays.asList(readData[row].split(",")));
			for (int i = 0; i < names.size(); i++) {
				String currentData = data.get(i);
				String currentName = names.get(i);
				int itemIndex = -1;
				for (int k = 0; k < items.size(); k++) {
					if (items.get(k).startsWith(currentName)) {
						itemIndex = k;
					}
				}
				if (itemIndex == -1) {
					items.add(currentName + ":" + currentData);
				} else {
					String identifier = currentData.split(":")[0];
					if (identifier.equals("T") || identifier.equals("I") || identifier.equals("F")) {
						items.set(itemIndex, currentName + ":" + currentData);
					} else if (identifier.equals("C") || identifier.equals("P")) {
						items.set(itemIndex, items.get(itemIndex) + "~" + currentData.split(":")[1]);
					}
				}
			}

			String outputToArray = "";
			for (int itemNumber = 0; itemNumber < items.size(); itemNumber++) {
				outputToArray += items.get(itemNumber);
				if (itemNumber + 1 < items.size()) {
					outputToArray += ",";
				}
			}

			readData[row] = outputToArray;

			try (FileWriter fw = new FileWriter("data/data.txt", false);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				for (int i = 0; i < readData.length; i++) {
					out.print(readData[i]);
					if(i + 1 < readData.length){
						out.println();
					}
				}
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
				System.out.println("Error in File:" + e.getMessage());
			}

		}

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
}
