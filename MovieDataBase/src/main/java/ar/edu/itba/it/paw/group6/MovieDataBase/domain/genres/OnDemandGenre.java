package ar.edu.itba.it.paw.group6.MovieDataBase.domain.genres;

public class OnDemandGenre implements Genre {

	private String name;
	
	public OnDemandGenre(String name) {
		//super();
		this.name = name;
	}

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public boolean isNew() {
		// Para implementarlo nececista un id en la base de datos el cual no existe.
		return false;
		
		
	}

	@Override
	public int hashCode() {
		
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OnDemandGenre))
			return false;
		OnDemandGenre other = (OnDemandGenre) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

}
