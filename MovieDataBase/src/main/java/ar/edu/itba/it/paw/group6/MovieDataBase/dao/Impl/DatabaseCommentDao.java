package ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ConnectorManager;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.User;

import org.springframework.stereotype.Repository;
@Repository
public class DatabaseCommentDao implements CommentDao {

	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	private static DatabaseCommentDao instance;
	
	private ConnectorManager connectionManager;
	private DatabaseMovieDao moviesDao;
	private DatabaseUserDao usersDao;
	
	
	
	public static synchronized DatabaseCommentDao getInstance() {
		if (instance == null) {
			instance = new DatabaseCommentDao();
		}
		return instance;
	}
	
	private DatabaseCommentDao() {
		
		connectionManager = new ConnectorManager("org.postgresql.Driver", USERNAME, PASSWORD);
		moviesDao = new DatabaseMovieDao();
		usersDao = new DatabaseUserDao();
	}

	@Override
	public int getCantComments(Movie movie) {
		// select count(*) from comment join movie on comment.movie = movie.id where comment.movie=2;
		int cant=0;
		try {
			
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("select count(*) from comment join movie on comment.movie = movie.id where comment.movie=?");
			commentStmt.setInt(1, movie.getId());
			ResultSet results = commentStmt.executeQuery();
			if (results.next()) {	
				cant = results.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		return cant;
	}

	public int getUserCantComments(String username) {
		int cant=0;
		try {
			
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("select count(*) from comment join app_user on comment.username = app_user.username where app_user.username= ?;");
			commentStmt.setString(1, username);
			ResultSet results = commentStmt.executeQuery();
			if (results.next()) {	
				cant = results.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		return cant;

	}

	
	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager#getAll()
	 */
	public Iterable<Comment> getAll() {
		
		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			
	
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("SELECT movie, username, content, rating, creation, id   FROM comment");
			
			ResultSet results = commentStmt.executeQuery();
			while (results.next()) {
				java.sql.Date date = results.getDate(5);
				
				Date date2 = new Date(date.getTime());
				
				list.add( new Comment(moviesDao.getMovie(results.getInt(1)), usersDao.getUser(results.getString(2)), results.getString(3), date2,results.getInt(4), results.getInt(6)));
			}
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		return list;


	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager#getComment(java.lang.String)
	 */
	public Comment getComment(String id) {
		Comment c = null; 
		try {
			
	
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("SELECT movie, username, content, rating, creation, id   FROM comment where id = ?");
			commentStmt.setInt(1, Integer.valueOf(id));
			ResultSet results = commentStmt.executeQuery();
			while (results.next()) {
				
				java.sql.Date date = results.getDate(5);
				
				Date date2 = new Date(date.getTime());
				
				c= ( new Comment(moviesDao.getMovie(results.getInt(1)), usersDao.getUser(results.getString(2)), results.getString(3), date2,results.getInt(4), results.getInt(6)));
			}
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		return c;

	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager#getComments(ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User)
	 */
	public Iterable<Comment> getComments(User user) {
	
		ArrayList<Comment> list = new ArrayList<Comment>();

		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("SELECT movie, username, content, rating, creation, id   FROM comment where username = ?");
			commentStmt.setString(1, user.getUsername());
			ResultSet results = commentStmt.executeQuery();

			while(results.next()) {
				java.sql.Date date = results.getDate(5);
				Date date2 = new Date(date.getTime());
				list.add( new Comment(moviesDao.getMovie(results.getInt(1)), user, results.getString(3), date2,results.getInt(4), results.getInt(6)));
			}
			
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}

		return list;		

	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager#getComments(ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie)
	 */
	public Iterable<Comment> getComments(Movie movie) {

		ArrayList<Comment> list = new ArrayList<Comment>();

		try {
			
	
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("SELECT movie, username, content, rating, creation, id   FROM comment where movie = ?");
			commentStmt.setInt(1, movie.getId());
			ResultSet results = commentStmt.executeQuery();

			while(results.next()) {

				java.sql.Date date = results.getDate(5);
				Date date2 = new Date(date.getTime());
				list.add( new Comment(movie, usersDao.getUser(results.getString(2)), results.getString(3), date2,results.getInt(4), results.getInt(6)));
			}

			connection.commit();
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}

		return list;	
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager#removeComment(ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment)
	 */
	public boolean removeComment(Comment comment) {
		try {
			
			
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("Delete FROM comment where id = ?");
			commentStmt.setInt(1, comment.getId());
			commentStmt.executeUpdate();
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

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.managers.CommentManager#removeComment(java.lang.String)
	 */
	public boolean removeComment(String id) {
		try {
			
			
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("Delete FROM comment where id = ?");
			commentStmt.setInt(1, Integer.valueOf(id));
			commentStmt.executeUpdate();
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


	public void saveComment(Comment comment) {
		try {

		Connection connection = connectionManager.getConnection();
		PreparedStatement commentStmt = connection.prepareStatement("INSERT INTO comment(content , username, movie, rating,	creation) values(?, ?, ? , ? , ?)");
		commentStmt.setString(1, comment.getContent());
		commentStmt.setString(2, comment.getUser().getUsername());
		commentStmt.setInt(3, comment.getMovie().getId());
		commentStmt.setInt(4, comment.getRating());
		commentStmt.setDate(5, new java.sql.Date(comment.getDate().getTime()));
		
		commentStmt.executeUpdate();
		connection.commit();
		connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}


	
	}

	

}
