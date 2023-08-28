package pe.idat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.idat.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer>{
    @Query(value="select count(*) from generos where nombre=?",nativeQuery=true)
    public abstract int isExistName(String nombre);
    
    

}
