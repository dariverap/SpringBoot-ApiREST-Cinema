package pe.idat.service;
import pe.idat.entity.Pelicula;

import java.util.Collection;

public interface PeliculaService {

    void insert(Pelicula pelicula);

    void update(Pelicula pelicula);

    void delete(Integer peliculaId);

    Pelicula findById(Integer peliculaId);

    Collection<Pelicula> findAll();
    
	public abstract Collection<Object[]> findAll_withGeneros1();
	public abstract Collection<Object[]> findAll_withGeneros2();
	

}

