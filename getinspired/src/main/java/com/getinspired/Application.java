package com.getinspired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test.APIConnectionTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class Application {
	private static final String filepath = "src/main/resources/API_KEY.txt";
	public final static String API_KEY = retrieveAPIKey(filepath);

	/**
	 * This is the main method that is responsible for running the Spring Boot application.
	 * Prior to initiating, it will run a test on whether there is a valid key to connect with the API.
	 * Without a possible connection with the API, the application will not run. Thus, it is important
	 * to ensure that a valid API key is written to API_KEY.txt
	 * A valid API key can be obtained at "https://www.pexels.com/api/"
	 * @param args - passed requirements to run the Spring Boot application.
	 * @see APIConnectionTest
	 */
	public static void main(String[] args) {
		APIConnectionTest test = new APIConnectionTest();
		try {
			if (test.testConnection()) {
				SpringApplication.run(Application.class, args);
			} else {
				System.out.println("An error occurred whilst trying to connect to API.\n" +
						"Please, ensure you have a valid API key in the API_KEY.txt file.");
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method takes the filepath which is final "src/main/resources/API_KEY.txt"
	 * The file API_KEY.txt should be a single line text with the API key.
	 * The Scanner will read only the first line, meaning any additional lines are ignored.
	 * @param filepath - final Application.filepath which refers to path "src/main/resources/API_KEY.txt"
	 * @return the API key as a String. If file no found, return null.
	 */
	private static String retrieveAPIKey(String filepath){
		try {
			return new Scanner(new File(filepath)).nextLine();
		} catch (FileNotFoundException e) {
			return null;
		}
	}
}
