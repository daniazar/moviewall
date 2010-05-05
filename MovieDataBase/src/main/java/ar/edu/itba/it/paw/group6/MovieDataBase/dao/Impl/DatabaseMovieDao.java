package ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.GenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Award;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.MovieGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.OnDemandGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.SiteMovieGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
@Repository

public class DatabaseMovieDao implements MovieDao{
	
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	private ConnectorManager connectionManager;
	private GenreDao genreDao;

        
	
	
	public DatabaseMovieDao() {
		this.connectionManager = new ConnectorManager("org.postgresql.Driver", USERNAME, PASSWORD);
		this.genreDao = new DatabaseGenreDao();
	}
	
	public Movie getMovie(int id) {
		
		Movie newMovie=null;

		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement selectMovies = connection.prepareStatement
				("SELECT id, duration, synopsis, director, release, title, imgurl, insertDate   FROM movie WHERE id = ?");
			
			selectMovies.setInt(1, id);
			ResultSet results = selectMovies.executeQuery();

			if (results.next()) {
				int movieId = results.getInt(1);
				int duration = results.getInt(2);
				String synopsis = results.getString(3);
				String director = results.getString(4);
				Date release = new Date(results.getDate(5).getTime());
				String title = results.getString(6);
				String imgUrl = results.getString(7);
				Date creation = new Date(results.getDate(8).getTime());
				
				newMovie = new Movie(movieId, title, director, imgUrl, duration, release, synopsis, creation);
				getRating(newMovie);
				loadGenres(newMovie);
				setAwards(newMovie);
			}
			connection.commit();
			connection.close();
				
		} catch (SQLException e) {

			throw new DatabaseException(e.getMessage(), e);
		}

		return newMovie;
	}

	private Iterable<Movie> getMoviesGeneric(String criteria) {

		ArrayList<Movie> list = new ArrayList<Movie>();

		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement selectMovies = connection.prepareStatement
				("SELECT id, duration, synopsis, director, release, title, imgurl, insertDate FROM movie" + criteria);
			
			ResultSet results = selectMovies.executeQuery();
			
			while(results.next()) {
				
				int id = results.getInt(1);
				int duration = results.getInt(2);
				String synopsis = results.getString(3);
				String director = results.getString(4);
				Date release = new Date(results.getDate(5).getTime());
				String title = results.getString(6);
				String imgUrl = results.getString(7);
				Date creation = new Date(results.getDate(8).getTime());

				Movie item = new Movie(id, title, director, imgUrl, duration, release, synopsis, creation);
				getRating(item);
				loadGenres(item);
				list.add(item);
	
			}

			//connection.commit(); We aren't saving nothing in the db.
			connection.close();

		} catch (SQLException e) {

			throw new DatabaseException(e.getMessage(), e);
		}

		return list;	
	}
	
	public Iterable<Movie> getAllMovies() {
		
		return this.getMoviesGeneric("");
	}

	
	public Iterable<Movie> getMovie(String name) {
		
		return this.getMoviesGeneric("");
	}

	public Iterable<Movie> getMoviesByComments() {
		
		return this.getMoviesGeneric("");
	}

	public boolean removeMovie(int id) {
		
		try {

			Connection connection = connectionManager.getConnection();
			
			//Verificar que no sea referenciada por has_genre
			PreparedStatement deleteGenre = connection.prepareStatement("DELETE FROM has_genre where movie=?;");
			deleteGenre.setInt(1, id);
			deleteGenre.executeUpdate();
			
			//Verificar que no sea referenciada por comments
			PreparedStatement deleteComment = connection.prepareStatement("DELETE FROM comment where movie=?;");
			deleteComment.setInt(1, id);
			deleteComment.executeUpdate();
			
			//Borrar de la tabla movie
			PreparedStatement deleteMovie = connection.prepareStatement("Delete FROM movie where id = ?");
			deleteMovie.setInt(1, id);
			deleteMovie.executeUpdate();
			
			connection.commit();
			connection.close();
			return true;
			
		} catch (SQLException e) {

			throw new DatabaseException(e.getMessage(), e);
		}
		
	}

	public boolean removeMovie(Movie movie) {

		return this.removeMovie(movie.getId());
	}

	public void saveMovie(Movie movie) {
		
		Connection connection = null;
		try {

			connection = connectionManager.getConnection();
			PreparedStatement statement;
			
			// Begin transaction:
			connection.setAutoCommit(false);
			
			if (movie.isNew()){
				
				statement = connection.prepareStatement
					("INSERT INTO movie(title, duration, synopsis, director, imgurl, release, insertDate) values(?, ?, ?, ?, ?, ?, ?)");
			}
			else {
				statement = connection.prepareStatement
					("UPDATE movie SET title=?, duration=?, synopsis=?, director=?, imgurl=?, release = ?, insertDate = ? WHERE id=?");
				statement.setInt(8, movie.getId());
			}
			
			statement.setString(1, movie.getTitle());
			statement.setInt(2, movie.getDuration());
			statement.setString(3, movie.getSynopsis());
			statement.setString(4, movie.getDirector());
			statement.setString(5, movie.getImgUrl());
			statement.setDate(6, new java.sql.Date(movie.getRelease().getTime()));
			long d = new java.util.Date().getTime();
			
			statement.setDate(7, new Date(d));
			
			statement.executeUpdate();
			
			if(movie.isNew()) {
				
				PreparedStatement selectMovie = connection.prepareStatement ("SELECT id FROM movie WHERE title LIKE ? ORDER BY id desc");
				selectMovie.setString(1, movie.getTitle());
				
				ResultSet results = selectMovie.executeQuery();

				if (results.next()) {

					movie.setId(results.getInt(1));
				}
			}

			connection.commit();

		} catch (SQLException e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DatabaseException(e.getMessage(), e);
			}
			throw new DatabaseException(e.getMessage(), e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage(), e);
			}
		}
	}
	
	// Funciones relacionadas con genero.
	
	public Iterable<Movie> getMoviesByGenre(Genre genre) {

		ArrayList<Movie> list = new ArrayList<Movie>();

		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement selectMovies = connection.prepareStatement
				("SELECT DISTINCT id, duration, synopsis, director, release, title, imgurl, insertDate " +
						"FROM movie, has_genre WHERE movie.id = has_genre.movie AND has_genre.genre LIKE '" + genre.getName() +"'");
			
			ResultSet results = selectMovies.executeQuery();
			
			while(results.next()) {
				
				int id = results.getInt(1);
				int duration = results.getInt(2);
				String synopsis = results.getString(3);
				String director = results.getString(4);
				Date release = new Date(results.getDate(5).getTime());
				String title = results.getString(6);
				String imgUrl = results.getString(7);
				Date creation = new Date(results.getDate(8).getTime());

				Movie item = new Movie(id, title, director, imgUrl, duration, release, synopsis, creation);
				getRating(item);
				loadGenres(item);
				list.add(item);
	
			}

			connection.commit();
			connection.close();

		} catch (SQLException e) {

			throw new DatabaseException(e.getMessage(), e);
		}

		return list;	
	}
	
	

	@Override
	public Set<Genre> GetMovieGenres(Movie movie) {

		Set<Genre> set = new HashSet<Genre>();
		
		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement
					("SELECT genre FROM has_genre where movie = ?");
			genreStmt.setInt(1, movie.getId());
			
			ResultSet results = genreStmt.executeQuery();
			while (results.next()) {
				set.add(new OnDemandGenre(results.getString(1)));
			}
			
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		return set;
	}

	@Override
	public void addGenreToMovie(Genre genre, Movie movie) {
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement ("INSERT INTO HAS_GENRE VALUES (?, ?)");

			genreStmt.setInt(1, movie.getId());
			genreStmt.setString(2, genre.getName());
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
	public void deleteMovieGenres(Movie movie) {
		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement
					("DELETE FROM has_genre WHERE movie = ?");

			genreStmt.setInt(1, movie.getId());
			genreStmt.executeUpdate();

			connection.commit();
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	public Iterable<Movie> searchWith(String q){
		List<Movie> ret = new LinkedList<Movie>();
		
		String[] keywords = q.split(" ");
		String query = "SELECT id, title, duration, director, release"; 
		String subQuery = "(SELECT m.id, m.title, m.duration, m.director, m.release FROM movie m LEFT OUTER JOIN has_genre g ON m.id = g.movie WHERE lower(m.title) LIKE ? OR lower(m.director) LIKE ? OR lower(g.genre) LIKE ?)";
	
		Connection con = null;
		try {
		
			con = connectionManager.getConnection();
			PreparedStatement stmt = null;
			
			
		if(q == null || q.trim().compareTo("") == 0){
			query += " FROM movie;";
			stmt = con.prepareStatement(query);
		}
		else
		{
			
			//Here at least we have one keyword;
			query += ", count(*) as oc FROM (" + subQuery;
			
			for(int i = 1; i < keywords.length ; i++)
				query += "UNION ALL" + subQuery;
			
			query += ") as aux GROUP BY aux.id, aux.title, aux.duration, aux.director, aux.release ORDER BY oc DESC";

			stmt = con.prepareStatement(query);
			
			int qty = 3; //The qty of "?" in the subQuery
			
			for(int i = 0; i < keywords.length ; i++){
				stmt.setString(qty*i + 1, '%' + keywords[i].toLowerCase() + '%');
				stmt.setString(qty*i + 2, '%' + keywords[i].toLowerCase() + '%');
				stmt.setString(qty*i + 3, '%' + keywords[i].toLowerCase() + '%');
			}
			
		}	
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				
				int id = result.getInt(1);
				String title = result.getString(2);
				int duration = result.getInt(3);
				String director = result.getString(4);
				Date release = new Date(result.getDate(5).getTime());
				Movie item = new Movie(id, title, director, "", duration, release, "");
				getRating(item);
				loadGenres(item);
				ret.add(item);
			}
			
		} catch(SQLException e){
			throw new DatabaseException(e.getMessage(),e);
		}
		finally{
			try{
				if(con != null)
					con.close();
			} catch (SQLException e){
				throw new DatabaseException(e.getMessage(),e);
			}
		}
		
		return ret;
	}
	
	
	private void getRating(Movie movie){
		
		float rating = 0, ratingvip=0;
		try {
			
	
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("SELECT avg(rating)   FROM comment where movie = ?");
			commentStmt.setInt(1, movie.getId());
			ResultSet results = commentStmt.executeQuery();

			while(results.next()) {
				rating = results.getFloat(1); 
			}

			connection = connectionManager.getConnection();
			commentStmt = connection.prepareStatement("SELECT avg(rating)   FROM comment c, App_user  u where c.movie = ? and u.username = c.username and u.isvip");
			commentStmt.setInt(1, movie.getId());
			results = commentStmt.executeQuery();

			while(results.next()) {
				ratingvip= results.getFloat(1); 
			}

			
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}

		movie.setRating(rating);
		movie.setRatingvip(ratingvip);

		
		
		
		/*
		Iterable<Comment> com = getComments(movie);
		int count = 0, points = 0;
		int countvip=0,pointsvip=0;
		for (Comment comment : com) {
			count++;
			points += comment.getRating();
			if(comment.getUser().getisVip())
			{
				countvip++;
				pointsvip+=comment.getRating();
			}
		}
		if (count != 0 ){
		movie.setRating(((float)points) / count);
		if (countvip != 0) {
			movie.setRatingvip(((float)pointsvip) / countvip);
			
		}
		}
		else {
			movie.setRating(-1);
		}
*/
		
	}
	
	public void loadGenres(Movie movie){
		
		LinkedList<MovieGenre> genres = new LinkedList<MovieGenre>();


		Set<Genre> movieGenres = this.GetMovieGenres(movie);
		Iterable<Genre> aux = genreDao.getAll();

		for(Genre genre: aux) {
			MovieGenre target = new SiteMovieGenre(genre.getName());
			if(movieGenres.contains(genre)) {
				target.addMovieGenreTag();
			}	
			genres.add(target);	
		}
		
		movie.setGenres(genres);
	}

	public void setAwards(Movie movie){
		
		List<Award> set = new ArrayList<Award>();
		
		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement
					("SELECT id, name, won   FROM has_award, award where movie = ? and award = id");
			genreStmt.setInt(1, movie.getId());
			
			ResultSet results = genreStmt.executeQuery();
			while (results.next()) {
				set.add(new Award(results.getBoolean(3), results.getInt(1), results.getString(2)));
			}
			
			connection.commit();
			connection.close();
			movie.setAward(set);
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		

	}
/*	
	public Iterable<Comment> getComments(Movie movie) {

		ArrayList<Comment> list = new ArrayList<Comment>();

		try {
			
	
			Connection connection = connectionManager.getConnection();
			PreparedStatement commentStmt = connection.prepareStatement("SELECT movie, username, content, rating, creation, id, flag   FROM comment where movie = ?");
			commentStmt.setInt(1, movie.getId());
			ResultSet results = commentStmt.executeQuery();

			while(results.next()) {

				java.sql.Date date = results.getDate(5);
				Date date2 = new Date(date.getTime());
				list.add( new Comment(movie, getUser(results.getString(2)), results.getString(3), date2,results.getInt(4), results.getInt(6), results.getBoolean(7)));
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
*/
	@Override
	public Award getAward(int id) {
		Award award= null; 
		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement
					("SELECT id, name FROM award where id = ?");
			genreStmt.setInt(1, id);
			
			ResultSet results = genreStmt.executeQuery();
			while (results.next()) {
				award =new Award(false, results.getInt(1), results.getString(2));
			}
			
			connection.commit();
			connection.close();
		
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		return award;

	}	
	
	
	@Override
	public void add(Award award) {
		Connection connection = null;
		try {

			connection = connectionManager.getConnection();
			PreparedStatement statement;
		
			statement = connection.prepareStatement
			("INSERT INTO award(name) values(?)");
				
		statement.setString(1, award.getName());		
		statement.executeUpdate();
		connection.commit();
		connection.close();
	
	}catch (Exception e) {
		;
	}
	
	}
	
	
	@Override
	public void add(Movie movie, Award award) {
		Connection connection = null;
		try {

			connection = connectionManager.getConnection();
			PreparedStatement statement;			
		
			statement = connection.prepareStatement
			("INSERT INTO has_award(award, movie, won) values(?, ?, ?)");
				
		statement.setInt(1, award.getId());
		statement.setInt(2, movie.getId());		
		statement.setBoolean(3, award.getWon());		
		
		statement.executeUpdate();
		connection.commit();
		connection.close();
	
	}catch (Exception e) {
		;
	}
		
	}
	
	@Override
	public void delete(Award award) {

		Connection connection = null;
		try {

			connection = connectionManager.getConnection();
			PreparedStatement statement;			

				
			statement = connection.prepareStatement
			("Delete FROM has_award where award = ?");
				
		statement.setInt(1, award.getId());
		
		statement.executeUpdate();
		connection.commit();

		statement = connection.prepareStatement
		("Delete FROM award where id = ?");
			
	statement.setInt(1, award.getId());
	
	statement.executeUpdate();
	connection.commit();
		
		connection.close();
	
	}catch (Exception e) {
		;
	}
		
	}
@Override
public void remove(Movie movie, Award award) {
	Connection connection = null;
	try {
		
	connection = connectionManager.getConnection();
	PreparedStatement statement;			

		
	statement = connection.prepareStatement
	("Delete FROM has_award where award = ? and movie = ?");
		
statement.setInt(1, award.getId());
statement.setInt(2, movie.getId());

	statement.executeUpdate();
	connection.commit();

	connection.close();	
	} catch (Exception e) {
		;// TODO: handle exception
	}

	}
	
	@Override
	public Iterable<Award> getAllAwards() {
		ArrayList<Award> award= new ArrayList<Award>(); 
		try {

			Connection connection = connectionManager.getConnection();
			PreparedStatement genreStmt = connection.prepareStatement
					("SELECT id, name FROM award");
			
			ResultSet results = genreStmt.executeQuery();
			while (results.next()) {
				award.add(new Award(false, results.getInt(1), results.getString(2)));
			}
			
			connection.commit();
			connection.close();
		
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (Exception e) {
			;
		}
		return award;

	}
/*
private User getUser(String username) {
		
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
*/
}

