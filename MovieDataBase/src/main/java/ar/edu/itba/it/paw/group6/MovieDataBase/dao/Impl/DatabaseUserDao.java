package ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ConnectorManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.UserDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import org.springframework.stereotype.Repository;
@Repository
public class DatabaseUserDao implements UserDao{

	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	private static UserDao instance;

	private ConnectorManager connectionManager;
	
	public static synchronized UserDao getInstance() {
		if (instance == null) {
			instance = new DatabaseUserDao();
		}
		return instance;
	}
	
	DatabaseUserDao() {
		connectionManager = new ConnectorManager("org.postgresql.Driver", USERNAME, PASSWORD);
		
	}
	
	
	public User authenticate(String username, String password) {
			
		User user = null;
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT email,name,surname,isVip,isAdmin,birthday FROM App_User WHERE username = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				String email = results.getString(1);
				String name = results.getString(2);
				String surname = results.getString(3);
				boolean isVip = results.getBoolean(4);
				boolean isAdmin = results.getBoolean(5); 
				Date dateBirth = results.getDate(6);
				user = new User(username, password, email, name, surname, isVip, isAdmin, dateBirth);	
			}
	
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		return user;

	}

	/*public User getLoguedUser(HttpServletRequest req) {
		
		return null;
	}
*/
	
	public User getUser(String username) {
		
		User user=null;
		
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT password,email,name,surname,isVip,isAdmin,birthday FROM App_user WHERE username = ?");
			stmt.setString(1, username);

			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				String password = results.getString(1);
				String email = results.getString(2);
				String name = results.getString(3);
				String surname = results.getString(4);
				boolean isVip = results.getBoolean(5);
				boolean isAdmin = results.getBoolean(6);
				Date birthday = results.getDate(7);
				
				user = new User(username, password, email, name, surname, isVip, isAdmin, birthday);
				//No es un usuario nuevo, entonces seteo el campo isNew en falso
				user.setNotNew();
			}
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		
		return user;
	}

	public boolean removeUser(User user) {
		
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement stmt;
			
				stmt = connection.prepareStatement("DELETE FROM app_user WHERE username = ?");
				stmt.setString(1, user.getUsername());	
				stmt.executeUpdate();
		
				connection.commit();
				connection.close();
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		
		return true;
	}

	public boolean removeUser(String username) {
		
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement stmt;
				
			stmt = connection.prepareStatement("DELETE FROM app_user WHERE username = ?");
			stmt.setString(1, username);		
			
			stmt.executeUpdate();
			connection.commit();
			connection.close();
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		
		return true;
	}

	public void saveUser(User user) {
		
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement stmt;

			if (user.isNew()) {
				
				stmt = connection.prepareStatement("INSERT INTO app_user (username,password,name,surname,email,isVip,isAdmin,birthday) values(?, ?, ?, ?,?,?,?,?)");
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.setString(3, user.getName());
				stmt.setString(4, user.getSurname());
				stmt.setString(5, user.getEmail());
				stmt.setBoolean(6, user.getisVip());
				stmt.setBoolean(7, user.getisAdmin());
				stmt.setDate(8, new java.sql.Date(user.getBirthday().getTime()));
				
				
			} else 
			{	
				stmt = connection.prepareStatement("UPDATE app_user SET password = ?, name = ?, surname = ?, email = ?, isVip = ?, isAdmin = ? WHERE username = ?");
				stmt.setString(1, user.getPassword());
				stmt.setString(2, user.getName());
				stmt.setString(3, user.getSurname());
				stmt.setString(4, user.getEmail());
				stmt.setBoolean(5,user.getisVip());
				stmt.setBoolean(6, user.getisAdmin());
				stmt.setString(7, user.getUsername());
			}
			stmt.executeUpdate();
			connection.commit();
			connection.close();
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		
	}
	
	
	public Iterable<User> getAll() {
		return searchWith("");
	}
	
	
	
	/**
	 * Does a query asking when ever a single argument matches some user field
	 * such us username, email, surname or what ever, then does a ranking with
	 * the matches and returns the collections ordererd by matches.
	 */
	public Iterable<User> searchWith(String q){
		
		List<User> ret = new LinkedList<User>();
		
		try {
			
			Connection con;
			con = connectionManager.getConnection();
			PreparedStatement stmt = null;
			
			if(q == null || q.trim().compareTo("") == 0){
				stmt = con.prepareStatement("SELECT username, name, surname, email, isvip, isAdmin, birthday FROM app_user");
			}
			else
			{
				String[] keywords = q.trim().split(" ");
				String subQuery = "(SELECT username, name, surname, email, isvip, isadmin, birthday FROM app_user WHERE LOWER(username) like ? OR LOWER(name) like ? OR LOWER(surname) like ? OR LOWER(email) like ?)";
				String query = "SELECT username, name, surname, email, isvip, isAdmin, birthday, count(*) as oc FROM (";
				
				//if we are here, at least we have a keyword.
				query += subQuery;
				
				for(int i = 1 ; i < keywords.length ; i++){
					query += "UNION ALL" + subQuery;
				}
				
				query += ") as aux GROUP BY username, name, surname, email, isvip, isadmin, birthday ORDER BY oc DESC";
				
				int qty = 4; //The cant of the "?" in the sub-query
				stmt = con.prepareStatement(query);
				
				for(int i = 0; i < keywords.length ; i++){
					stmt.setString(qty*i + 1, '%' + keywords[i].toLowerCase() + '%');
					stmt.setString(qty*i + 2, '%' + keywords[i].toLowerCase() + '%');
					stmt.setString(qty*i + 3, '%' + keywords[i].toLowerCase() + '%');
					stmt.setString(qty*i + 4, '%' + keywords[i].toLowerCase() + '%');
				}
			}
			
			ResultSet result = stmt.executeQuery();
		
			while (result.next()) {
				
				String username = result.getString(1);
				String name = result.getString(2);
				String surname = result.getString(3);
				String email = result.getString(4);
				boolean isVip = result.getBoolean(5);
				boolean isAdmin = result.getBoolean(6);
				Date birth = result.getDate(7);
				
				
				
				User user = new User(username, "", email, name, surname, isVip, isAdmin, birth);
				ret.add(user);
				 	
			}
			con.close();
	} catch (SQLException e) {
		throw new DatabaseException(e.getMessage(), e);
	}
		return ret;
	
	}
}