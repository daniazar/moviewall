package ar.edu.itba.it.paw.group6.MovieDataBase.service.Impl;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.GenreDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.Genre;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dani
 *
 */
@Service
public class GenreServiceImpl implements GenreService {

private GenreDao genreDao;
	@Autowired
	public GenreServiceImpl(GenreDao genreDao) {
		this.genreDao = genreDao;
	}
	
	@Override
	public Iterable<Genre> getAll() {
		return genreDao.getAll();
	}
	
	@Override
		public Genre getGenre(String id) {
		return genreDao.getGenre(id);
		}

	@Override
		public boolean removeGenre(Genre genre) {
		return genreDao.removeGenre(genre);

		}

	@Override
		public boolean removeGenre(String id) {
		return genreDao.removeGenre(id);
	}
	
	@Override
		public void saveGenre(Genre genre) {
			genreDao.saveGenre(genre);
		}
	
	
}
