package ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ConnectorManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.GenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.OnDemandGenre;

import org.springframework.stereotype.Repository;
@Repository
public class DatabaseGenreDao implements GenreDao {

	
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	private static DatabaseGenreDao instance;
	
	private ConnectorManager connectionManager;

	
	
	public static synchronized DatabaseGenreDao getInstance() {
		if (instance == null) {
			instance = new DatabaseGenreDao();
		}
		return instance;
	}
	
	private DatabaseGenreDao() {
		connectionManager = new ConnectorManager("org.postgresql.Driver", USERNAME, PASSWORD);
		

	}
	
	@Override
	public Iterable<Genre> getAll() {

		ArrayList<Genre> list = new ArrayList<Genre>();
		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement("SELECT name FROM genre");
			ResultSet results = genreStmt.executeQuery();

			while (results.next()) {
				list.add( new OnDemandGenre(results.getString(1)));
			}
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}

		return list;

	}

	@Override
	public boolean removeGenre(String id) {

		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement("DELETE FROM has_genre where genre = ?");
			genreStmt.setString(1, id);
			genreStmt.executeQuery();
			
			genreStmt = connection.prepareStatement("DELETE FROM genre where name = ?");
			genreStmt.setString(1, id);
			genreStmt.executeUpdate();
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}

		return true;
	}

	@Override
	public boolean removeGenre(Genre genre) {
		return this.removeGenre(genre.getName());
	}


	@Override
	public void saveGenre(Genre genre) {
		try {
			
	
			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement("insert INTO genre(name) values (?)");
			genreStmt.setString(1, genre.getName());
			genreStmt.executeUpdate();
			connection.commit();
			connection.close();
			
		} catch (SQLException e) {
			
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}

	}

	@Override
	public Genre getGenre(String id) {

		return new OnDemandGenre(id);
	}

}
