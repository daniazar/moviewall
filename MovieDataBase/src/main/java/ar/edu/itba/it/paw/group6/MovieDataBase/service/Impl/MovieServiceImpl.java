package ar.edu.itba.it.paw.group6.MovieDataBase.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Award;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dani
 *
 */
@Service
public class MovieServiceImpl implements MovieService {

	private MovieDao movieDao;
	private CommentService commserv;
	
    static final Comparator<Movie> RATING = new Comparator<Movie>() {
        public int compare(Movie e1, Movie e2) {
            return ((Float)e2.getRating()).compareTo(e1.getRating());
        	}
        };

    static final Comparator<Movie> MODIFICATION = new Comparator<Movie>() {
         public int compare(Movie e1, Movie e2) {
        	 return e2.getCreation().compareTo(e1.getCreation());
         	}
        };
        
        static final Comparator<Movie> RELEASE = new Comparator<Movie>() {
            public int compare(Movie e1, Movie e2) {
                return e2.getRelease().compareTo(e1.getRelease());
            	}
            };

@Override
	public Iterable<Movie> getMoviesByreleaseweek() {
	Iterable<Movie> mov =movieDao.getAllMovies();
	ArrayList<Movie> movies= new ArrayList<Movie>();
	  Calendar calendar1 = Calendar.getInstance();
	  calendar1.set(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH)+1,calendar1.get(Calendar.DAY_OF_MONTH));	

	for (Movie movie : mov) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Integer year= Integer.parseInt((formatter.format(movie.getRelease())));
		formatter = new SimpleDateFormat("MM");
		Integer month= Integer.parseInt((formatter.format(movie.getRelease())));
		formatter = new SimpleDateFormat("dd");
		Integer day= Integer.parseInt((formatter.format(movie.getRelease())));
		    Calendar calendar2 = Calendar.getInstance();
		    calendar2.set(year, month, day);
		    long milliseconds1 = calendar1.getTimeInMillis();
		    long milliseconds2 = calendar2.getTimeInMillis();
		    long diff = milliseconds2 - milliseconds1;

		//    System.err.println(calendar1.get(Calendar.DAY_OF_WEEK) +  "day:" + calendar1.get(Calendar.DAY_OF_MONTH)  );
		    long diffDays = diff / (24 * 60 * 60 * 1000);
		 //   System.err.println(diffDays);
		    if( Math.abs(diffDays) <= 7){
		    	day =calendar1.get(Calendar.DAY_OF_WEEK);
		 //   	int day2 =calendar2.get(Calendar.DAY_OF_WEEK);
		    	
		    	if (day >=1 && milliseconds1 >= milliseconds2)
		    		movies.add(movie);
		    	if (day <=1 && milliseconds1 <= milliseconds2)
		    		movies.add(movie);
		    	
		    		
		    }
	}
		
		
		return movies;
	}	
	
	
    @Autowired
	public MovieServiceImpl(MovieDao movieDao, CommentService commserv) {
		this.movieDao = movieDao;
		this.commserv = commserv;
		}

	
	@Override
	public Set<Genre> GetMovieGenres(Movie movie) {
			return movieDao.GetMovieGenres(movie);
			
	}

	@Override
	public void addGenreToMovie(Genre genre, Movie movie) {
		movieDao.addGenreToMovie(genre, movie);

	}

	@Override
	public void deleteMovieGenres(Movie movie) {
		movieDao.deleteMovieGenres(movie);
	}

	@Override
	public Iterable<Movie> getAllMovies() {
		
		Iterable<Movie> movies = movieDao.getAllMovies();
		 
		 for (Movie movie : movies) {
			 commserv.SetCantComments(movie);
		}
		return movies;

	}

	@Override
	public Iterable<Movie> getBefore() {
		Iterable<Movie> mov = this.getAllMovies();
		Collections.sort((ArrayList<Movie>)mov, RELEASE);
		ArrayList<Movie> m= new ArrayList<Movie>();
		int i = 0;
		for (Movie movie : mov) {
			if( i < 5 ){
				m.add(movie);
			}
				i++;
		}
		 for (Movie movie : m) {
			 commserv.SetCantComments(movie);
		}
		
		return m;
	
	}

	@Override
	public Movie getMovie(int id) {
		Movie movie = movieDao.getMovie(id);
		return movie;
	}

	@Override
	public Iterable<Movie> getMovie(String name) {
		Iterable<Movie> movies = movieDao.getMovie(name);
		 
		 for (Movie movie : movies) {
			 commserv.SetCantComments(movie);
		}
		return movies;
		
	}


	@Override
	public Iterable<Movie> getMoviesByGenre(Genre genre) {
		
		Iterable<Movie> movies = movieDao.getMoviesByGenre(genre);
		 
		 for (Movie movie : movies) {
			 commserv.SetCantComments(movie);
		}
		return movies;
		
		}

	@Override
	public Iterable<Movie> getMoviesByModification() {
		Iterable<Movie> mov = this.getAllMovies();
		Collections.sort((ArrayList<Movie>)mov, MODIFICATION);
		ArrayList<Movie> m= new ArrayList<Movie>();
		int i = 0;
		for (Movie movie : mov) {
			if( i < 5 ){
				m.add(movie);
			}
				i++;
		}
		
		 
		 for (Movie movie : m) {
			 commserv.SetCantComments(movie);
		}		
		return m;

	}

	@Override
	public Iterable<Movie> getMoviesByRanking() {
		Iterable<Movie> mov = this.getAllMovies();
		Collections.sort((ArrayList<Movie>)mov, RATING);
		ArrayList<Movie> m= new ArrayList<Movie>();
		int i = 0;
		for (Movie movie : mov) {
			if( i < 5 ){
				m.add(movie);
			}
				i++;
		}
		
		 for (Movie movie : m) {
			 commserv.SetCantComments(movie);
		}		

		return m;
	
	}

	@Override
	public boolean deleteMovie(int id) {
		return movieDao.removeMovie(id);
	}

	@Override
	public boolean deleteMovie(Movie movie) {
		return movieDao.removeMovie(movie);

	}

	@Override
	public void saveMovie(Movie movie) {
		movieDao.saveMovie(movie);
	}

	@Override
	public Iterable<Movie> searchWith(String q) {
		Iterable<Movie> movies = movieDao.searchWith(q);
		 
		 for (Movie movie : movies) {
			 commserv.SetCantComments(movie);
		}
		return movies;
		

	}

	public Award getAward(int id){
		return movieDao.getAward(id);
	}
	
	public void remove(Movie movie, Award award){
		movieDao.remove(movie, award);
	
	}
	@Override
	public void add(Award award) {
		movieDao.add(award);
				
	}
	@Override
	public void add(Movie movie, Award award) {
		movieDao.add(movie, award);
		
	}
	@Override
	public void delete(Award award) {
		movieDao.delete(award);
				
	}
	@Override
		public Iterable<Award> getAllAwards() {
			
			return movieDao.getAllAwards();
		}	
	
}
