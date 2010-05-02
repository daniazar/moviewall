package ar.edu.itba.it.paw.group6.MovieDataBase.service.Impl;

import ar.edu.itba.it.paw.group6.MovieDataBase.dao.CommentDao;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.comments.Comment;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.movies.Movie;
import ar.edu.itba.it.paw.group6.MovieDataBase.domain.users.User;
import ar.edu.itba.it.paw.group6.MovieDataBase.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commDao;
	@Autowired
	public CommentServiceImpl(CommentDao commDao) {
		this.commDao = commDao;
		}
	
	@Override
	public Iterable<Comment> getAll() {
		return commDao.getAll();
	}

	@Override
	public void SetCantComments(Movie movie) {
		movie.setCantComments(commDao.getCantComments(movie));
		
	}
	
	@Override
	public Comment getComment(String id) {
		return commDao.getComment(id);
	}

	@Override
	public Iterable<Comment> getComments(User user) {
		return commDao.getComments(user);
	}

	@Override
	public Iterable<Comment> getComments(Movie movie) {
		return commDao.getComments(movie);
		}


	@Override
	public boolean removeComment(Comment comment) {
		return commDao.removeComment(comment);
	}

	@Override
	public boolean removeComment(String id) {
		return commDao.removeComment(id);
	}

	@Override
	public void saveComment(Comment comment) {
		commDao.saveComment(comment);

	}

}
