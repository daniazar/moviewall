package ar.edu.itba.it.paw.group6.MovieDataBase.domain;

public interface Genre {
	
	/**
	 * Obtiene el nombre del genero
	 * @return
	 */
	public String getName();

	/**
	 * Si es nuevo hay que persistirlo.
	 * @return
	 */
	public boolean isNew();
}
