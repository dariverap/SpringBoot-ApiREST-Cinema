package pe.idat.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.idat.entity.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
	
    @Query(value="select * from pelicula_genero",nativeQuery=true)
	public abstract Collection<Object[]> findAll_withGeneros1();
	
	
	@Query(value="select  pel.pelicula_id, pel.titulo as 'pelicula' ,gen.genero_id, gen.nombre as 'genero'\r\n"
			+ "from pelicula_genero gr\r\n"
			+ "inner join generos gen on gr.genero_id=gen.genero_id \r\n"
			+ "inner join peliculas pel on gr.pelicula_id=pel.pelicula_id;",nativeQuery=true)
	public abstract Collection<Object[]> findAll_withGeneros2();
}

