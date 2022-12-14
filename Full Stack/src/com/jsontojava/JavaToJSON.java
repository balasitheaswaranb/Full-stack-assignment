package com.jsontojava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaToJSON {
	public static void main(String args[]) {
		JavaToJSON  converter = new JavaToJSON();
		converter.jsonToPojo();
		converter.pojoToJson();
	}

	private void pojoToJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Employee> list = new ArrayList<>();
		Employee employee1 = new Employee(100, "bala");
		list.add(employee1);
		Employee employee2 = new Employee(101, "kaja");
		list.add(employee2);
		Employee employee3 = new Employee(102, "sathish");
		list.add(employee3);
		Employee employee4 = new Employee(100, "bala");
		list.add(employee4);
		try {
			String jsonData = objectMapper.writeValueAsString(list);
			System.out.println();
			System.out.println(jsonData);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
	}

	private void jsonToPojo() {
		ObjectMapper objectMapper1 = new ObjectMapper();
		JFrames frame =new JFrames();
		
		try {
			URL url = new URL("https://reqres.in/api/unknown");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String jsonResponse = reader.readLine();
			Root root = objectMapper1.readValue(jsonResponse, Root.class);
			String []strArr1= {"page:" + root.page , "per_page:" + root.per_page ,"total:" + root.total
					,"total pages:" + root.total_pages , "url:" + root.support.url};
			frame.toJFrame(strArr1);
			
			System.out.println("page:" + root.page + "\nper_page:" + root.per_page + "\ntotal:" + root.total
					+ "\ntotal pages:" + root.total_pages + "\nurl:" + root.support.url);
			
			for (Datum data : root.data) {
				String strArr[]= {"id:" + data.id , "name:" + data.name , "year:" + data.year , "color:" + data.color
						, "pantone_value:" + data.pantone_value};
				frame.toJFrame(strArr);
				
				System.out.println("\nid:" + data.id + "\nname:" + data.name + "\nyear:" + data.year + "\ncolor:" + data.color
						+ "\npantone_value:" + data.pantone_value);
			}
		} catch (JsonMappingException e) {
			System.out.println(e.getMessage());
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	// pojo inner class
	static class Datum {
		public int id;
		public String name;
		public int year;
		public String color;
		public String pantone_value;
	}

	static class Root {
		public int page;
		public int per_page;
		public int total;
		public int total_pages;
		public ArrayList<Datum> data;
		public Support support;
	}

	static class Support {
		public String url;
		public String text;
	}

}
