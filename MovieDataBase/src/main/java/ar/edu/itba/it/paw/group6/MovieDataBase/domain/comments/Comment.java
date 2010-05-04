/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments;

import java.sql.Date;

import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;

public class Comment {

	public Comment(Movie movie, User user, String content, Date date,
			int raiting, int id, boolean flag) {
		super();
		this.content = content;
		this.date = date;
		this.raiting = raiting;
		this.id = id;			
		this.movie = movie;
		this.user = user;
		this.flag= flag;
		
		validate();
	}

	public Comment(Movie movie, User user, String content, Date date,
			int raiting, boolean flag) {
		super();
		this.content = content;
		this.date = date;
		this.raiting = raiting;
		this.movie = movie;
		this.user = user;
		this.flag= flag;		
		validate();	
	}
	
	private void validate(){
		if(!(0 <= raiting && raiting <= 5))
			throw new NumberFormatException("Rating must be between 0 and 5");

	}
	
	
	private String content;
	private Date date;
	private int raiting;
	private Integer id;
	private User user;
	private Movie movie;
	private boolean flag; 
	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment#IsNew()
	 */
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean getFlag() {
		return flag;
	}
	
	public boolean IsNew() {
		if (id == null)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment#getContent()
	 */
	public String getContent() {
		return content;
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment#getDate()
	 */
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment#getMovie()
	 */
	public Movie getMovie(){
		
		return movie;

		
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment#getRating()
	 */
	public int getRating() {
		return raiting;
	}

	/* (non-Javadoc)
	 * @see ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment#getUser()
	 */
	public User getUser() {
		return (User) user;
	}

	public int getRaiting() {
		return raiting;
	}

	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
