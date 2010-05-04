package ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies;


import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Award;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.MovieGenre;

public class Movie {
	private float   ratingvip = 0f;
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
	private int cantComments = 0;
	private List<Award> award= null;

	public Movie (int movieId, String title, String director, String imgUrl, int duration, Date release, String synopsis, Date creation){

		this(title, director, imgUrl, duration, release, synopsis);
		this.movieId = movieId;
		this.creation =creation;
	}

	public Movie (int movieId, String title, String director, String imgUrl, int duration, Date release, String synopsis){

		this(title, director, imgUrl, duration, release, synopsis);
		this.movieId = movieId;
	}
public void setAward(List<Award> award) {
	this.award = award;
}

public List<Award> getAward() {
	return award;
}
	public Movie (String title, String director, String imgUrl, int duration, Date release, String synopsis){

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
public void setGenres(List<MovieGenre> genres) {
	this.genres = genres;
}

	
	public Date getCreation() {
		return creation;
	};
	
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

	public void setRating(float rating) {
		this.rating = rating;
	}
	public float getRating() {		
			return this.rating;
	}
	
	public float getRatingvip() {
		return ratingvip;
	}
	
	public void setRatingvip(float ratingvip) {
		this.ratingvip = ratingvip;
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
