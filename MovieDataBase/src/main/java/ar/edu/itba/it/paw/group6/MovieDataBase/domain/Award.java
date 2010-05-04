/**
 * 
 */
package ar.edu.itba.it.paw.group6.MovieDataBase.domain;

/**
 * @author dani
 *
 */
public class Award {
	
	private boolean won;
	private int id;
	private String name;
	
	
	public Award(boolean won, int id, String name) {
		super();
		this.won = won;
		this.id = id;
		this.name = name;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	/**
	 * @return the won
	 */
	public boolean getWon() {
		return won;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
}


