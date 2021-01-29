package com.jolley.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionGenerator {

	//jdbc:postgresql:// <endpoint> / db name
	private static final String URL = "jdbc:postgresql://database-1.cepw3hzcjt1g.us-east-2.rds.amazonaws.com/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Hitishe1";

	private static Connection conn;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
