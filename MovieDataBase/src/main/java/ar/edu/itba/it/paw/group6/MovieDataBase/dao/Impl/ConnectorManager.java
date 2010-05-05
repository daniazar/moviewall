package ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectorManager {

		private String username;
		private String password;
		
		public ConnectorManager(String driver, String username, String password){
			this.username = username;
			this.password = password;
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				throw new DatabaseException(e.getMessage(), e);
			}
		}
		
		public Connection getConnection() throws SQLException {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/MovieWall", username, password);
			connection.setAutoCommit(false);
			return connection;
		}
	}

