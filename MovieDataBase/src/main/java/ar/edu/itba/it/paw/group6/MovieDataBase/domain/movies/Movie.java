package ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.GenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.ManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.dao.Impl.DatabaseManagerFactory;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.MovieGenre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.SiteMovieGenre;

public class Movie {

	private int		movieId;
	private float	rating;
	private int		duration;
	private String	title;
	private String	director;
	private String	imgUrl;
	private String	synopsis;
	private Date	release;
	private Date 	creation;
	private List<MovieGenre> genres;
	private ManagerFactory factory;
	private int cantComments = 0;


	public Movie (int movieId, String title, String director, String imgUrl, int duration, Date release, String synopsis, Date creation){

		this(title, director, imgUrl, duration, release, synopsis);
		this.movieId = movieId;
		this.loadGenres();
		this.creation =creation;
	}

	public Movie (int movieId, String title, String director, String imgUrl, int duration, Date release, String synopsis){

		this(title, director, imgUrl, duration, release, synopsis);
		this.movieId = movieId;
		this.loadGenres();
	}

	
	public Movie (String title, String director, String imgUrl, int duration, Date release, String synopsis){

		this.factory = DatabaseManagerFactory.getInstance();
		this.movieId = -1;
		this.title = title;
		this.director = director;
		this.imgUrl = imgUrl;
		this.duration = duration;
		this.release = release;
		this.synopsis = synopsis;
		long d = new java.util.Date().getTime();
		creation = new Date(d); 
	}

	// Genres:

	public Date getCreation() {
		return creation;
	};
	public void loadGenres(){
		
		this.genres = new LinkedList<MovieGenre>();

		MovieDao movieManager =  factory.getMovieManager();
		GenreDao genreManager =  factory.getGenreManager();
		Set<Genre> movieGenres = movieManager.GetMovieGenres(this);
		Iterable<Genre> aux = genreManager.getAll();

		for(Genre genre: aux) {
			MovieGenre target = new SiteMovieGenre(genre.getName());
			if(movieGenres.contains(genre)) {
				target.addMovieGenreTag();
			}	
			this.genres.add(target);	
		}
	}
	
//	public void addGenre(Genre genre) {
//		MovieManager manager =  factory.getMovieManager();
//		manager.addGenreToMovie(genre, this);
//		return;
//	}
	
	public Iterable<Genre> getGenres(){
		
		LinkedList<Genre> lista = new LinkedList<Genre>();
		
		for(MovieGenre genre: genres) {
			if (genre.getMovieGenreTag()) {
				lista.add(genre);
			}
		}
		return lista;
	}

	public List<MovieGenre> getAllMovieGenres() {
		return genres;
	}

	// Comment:
	


	public int getCantComments() {		
		return cantComments;
	}
	
	public void removeComment(Comment comment) {

		CommentDao manager =  factory.getCommentManager();
		manager.removeComment(comment);
		return;
	}

	public void removeComment(String id) {

		CommentDao manager =  factory.getCommentManager();
		manager.removeComment(id);
		return;
	}

	// Director:
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	// Duration:

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	// Id:
	
	public int getId() {
		return this.movieId;
	}
	
	public void setId(int id) {
		this.movieId = id;
	}

	// Rating:

	public float getRating() {
		if( rating == 0)
		{
		CommentDao manager =  factory.getCommentManager();
		Iterable<Comment> com =manager.getComments(this);
		int count = 0, points = 0;
		
		for (Comment comment : com) {
			count++;
			points += comment.getRating();
		}
		if (count != 0 ){
		rating = points / count;	
		}
		else {
			rating = -1;
		}
		}
		
			return this.rating;
	}

	// Release:
	
	public void setRelease(Date release) {
		this.release = release;
	}
	
	public Date getRelease() {
		return this.release;
	}
	
	//Synopsys:
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}

	public String getShort() {
		if (synopsis.length() > 100)
		{
		return synopsis.substring(0, 100);
		}
		return synopsis;
	}
	
	public String getShortSynopsis(int length) {
		return this.getShort();
	}

	// Title:
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

	// New:

	public boolean isNew() {
		
		return (this.movieId == -1);
	}
	
	// ImgUrl:

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgUrl() {
		return this.imgUrl;
	}
	
	public void setCantComments(int cantComments) {
		this.cantComments = cantComments;
	}
}
