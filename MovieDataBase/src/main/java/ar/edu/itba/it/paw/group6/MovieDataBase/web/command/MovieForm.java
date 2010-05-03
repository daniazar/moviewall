package ar.edu.itba.it.paw.group6.MovieDataBase.web.command;

import java.sql.Date;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Movie;

public class MovieForm {
	

	private String title = "";
	private String director = "";
	private String sinopsys = "";
	private String imgUrl = "";
	private String release = "";
	private String duration;
	private String id;
	
	public MovieForm() {
		id = "-1";
	}
	
	public MovieForm(Movie movie ){
		this.title=movie.getTitle();
		this.director=movie.getDirector();
		this.sinopsys=movie.getSynopsis();
		this.release=movie.getRelease().toString();
		this.imgUrl=movie.getImgUrl();
		this.id= String.valueOf(movie.getId());
		this.duration= String.valueOf(movie.getDuration());
	}

	
	
	public Movie build() {
		Date creation = new Date(new java.util.Date().getTime()); 
		return new Movie(Integer.parseInt(id), title, director, imgUrl, Integer.parseInt(duration), Date.valueOf(release), sinopsys, creation);

	}




	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}




	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}




	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}




	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}




	/**
	 * @return the sinopsys
	 */
	public String getSinopsys() {
		return sinopsys;
	}




	/**
	 * @param sinopsys the sinopsys to set
	 */
	public void setSinopsys(String sinopsys) {
		this.sinopsys = sinopsys;
	}




	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}




	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}




	/**
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}




	/**
	 * @param release the release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}




	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}




	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}




	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
