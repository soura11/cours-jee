package com.example.utils;

import java.sql.*;

public class MyConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/cours_jdbc?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private Connection connexion = null;

	public MyConnection() {}

	// methode qui va retourner une unique connexion a la db
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connexion == null || connexion.isClosed()) {
			try {
				// Chargement du driver 8 mysql
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Etablit la connexion avec la base de donnees
				connexion = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return connexion;
	}

	// methode qui arrete l'unique connexion a la db
	public void stop() throws SQLException {
		if (connexion != null && !connexion.isClosed()) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
