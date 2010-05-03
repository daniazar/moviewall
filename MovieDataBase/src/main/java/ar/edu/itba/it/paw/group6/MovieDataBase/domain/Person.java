package ar.edu.itba.it.paw.group6.MovieDataBase.domain;

import java.util.Date;

public interface Person {

	 public String getName();
	
	 public String getSurname();
	 
	 public int getAge();
		
	 public Date getBirthday();
	 
	 public boolean isNew();
	 
	 public void setName(String name);
	
	 public void setBirthday(Date date);
	
	 public void setSurname(String surname);
	
}
