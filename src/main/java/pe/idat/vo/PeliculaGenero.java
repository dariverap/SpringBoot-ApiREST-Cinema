package pe.idat.vo;

import pe.idat.entity.Genero;
import pe.idat.entity.Pelicula;


public class PeliculaGenero
{
	private Genero genero;
	private Pelicula pelicula;
	
	public PeliculaGenero() {		
	}

	public PeliculaGenero( Pelicula pelicula,Genero genero) {
		this.genero = genero;
		this.pelicula = pelicula;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}


}
