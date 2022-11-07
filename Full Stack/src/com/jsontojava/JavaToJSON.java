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
		JavaToJSON java = new JavaToJSON();
		java.jsonToJava();
		java.javaToJson();

	}

	private void javaToJson() {
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
			e.printStackTrace();
		}
	}

	private void jsonToJava() {
		ObjectMapper objectMapper1 = new ObjectMapper();
		try {
			URL url = new URL("https://reqres.in/api/unknown");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response;
			while ((response = reader.readLine()) != null) {
				try {
					Root root = objectMapper1.readValue(response, Root.class);

					System.out.println("page:" + root.page + "\nper_page:" + root.per_page + "\ntotal:" + root.total
							+ "\ntotal pages:" + root.total_pages + "\ndatas"+ "url:" + root.support.url);
					for (Datum d : root.data) {
						System.out.println("\nid:" + d.id + "\nname:" + d.name + "\nyear:" + d.year + "\ncolor:" + d.color
								+ "\npantone_value:" + d.pantone_value);
					}
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}

class Datum {
	public int id;
	public String name;
	public int year;
	public String color;
	public String pantone_value;
}

class Root {
	public int page;
	public int per_page;
	public int total;
	public int total_pages;
	public ArrayList<Datum> data;
	public Support support;
}

class Support {
	public String url;
	public String text;
}
//try {
//
//	List<Employee> list1 = objectMapper1.readValue(jsonData, new TypeReference<List<Employee>>() {
//	});
//	System.out.println(list1.size());
//	list1.forEach(e -> {
//		System.out.println(e.getEmployeeId() + ", " + e.getName());
//	});
//} catch (JsonMappingException e) {
//	e.printStackTrace();
//} catch (JsonProcessingException e) {
//	e.printStackTrace();
//}
