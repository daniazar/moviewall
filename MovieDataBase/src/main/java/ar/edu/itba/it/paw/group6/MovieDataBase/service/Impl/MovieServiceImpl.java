package ar.edu.itba.it.paw.group6.MovieDataBase.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.MovieDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
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

	
	
	
    @Autowired
	public MovieServiceImpl(MovieDao movieDao) {
		this.movieDao = movieDao;
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
		return movieDao.getAllMovies();
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
		return m;
	
	}

	@Override
	public Movie getMovie(int id) {
		return movieDao.getMovie(id);
	}

	@Override
	public Iterable<Movie> getMovie(String name) {
		return movieDao.getMovie(name);
	}


	@Override
	public Iterable<Movie> getMoviesByGenre(Genre genre) {
		return movieDao.getMoviesByGenre(genre);
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
		return m;
	
	}

	@Override
	public boolean removeMovie(int id) {
		return movieDao.removeMovie(id);
	}

	@Override
	public boolean removeMovie(Movie movie) {
		return movieDao.removeMovie(movie);

	}

	@Override
	public void saveMovie(Movie movie) {
		movieDao.saveMovie(movie);
	}

	@Override
	public Iterable<Movie> searchWith(String q) {
		return searchWith(q);
	}

}
