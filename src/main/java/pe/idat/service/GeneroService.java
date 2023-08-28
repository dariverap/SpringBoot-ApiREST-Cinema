package pe.idat.service;
import java.util.Collection;
import pe.idat.entity.Genero;

public interface GeneroService {

    void insert(Genero genero);

    void update(Genero genero);

    void delete(Integer generoId);

    Genero findById(Integer generoId);

    Collection<Genero> findAll();
  
    public abstract int isExistName(String nombre);

}
