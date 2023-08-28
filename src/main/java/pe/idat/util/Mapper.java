package pe.idat.util;

import java.util.ArrayList;
import java.util.Collection;

import pe.idat.entity.Cine;
import pe.idat.entity.Genero;
import pe.idat.entity.Pelicula;
import pe.idat.entity.Proyeccion;
import pe.idat.mapper.CineMapper;
import pe.idat.mapper.GeneroMapper;
import pe.idat.mapper.PeliculaMapper;
import pe.idat.mapper.ProyeccionMapper;


public class Mapper {

	public static Collection<CineMapper> toCines(Collection<Cine> cines)
	{
		Collection<CineMapper> collection=new ArrayList<>();
		
		for(Cine cine:cines) {
			
			CineMapper mapper=new CineMapper(cine);
			collection.add(mapper);
			
		}
		
		return collection;
	}
	
	public static Collection<PeliculaMapper> toPeliculas(Collection<Pelicula> peliculas){
		Collection<PeliculaMapper> collection=new ArrayList<>();
		
		for(Pelicula pelicula:peliculas) {
			
			PeliculaMapper mapper=new PeliculaMapper(pelicula);
			collection.add(mapper);
			
		}
		
		return collection;
		
	}
	
	public static Collection<ProyeccionMapper> toProyecciones(Collection<Proyeccion> proyecciones){
		Collection<ProyeccionMapper> collection=new ArrayList<>();
		
		for(Proyeccion proyeccion:proyecciones) {
			
			ProyeccionMapper mapper=new ProyeccionMapper(proyeccion);
			collection.add(mapper);
			
		}
		
		return collection;
		
	}
	
	public static Collection<GeneroMapper> toGeneros(Collection<Genero> generos){
		Collection<GeneroMapper> collection=new ArrayList<>();
		
		for(Genero genero:generos) {
			
			GeneroMapper mapper=new GeneroMapper(genero);
			collection.add(mapper);
			
		}
		
		return collection;
		
	}
}
