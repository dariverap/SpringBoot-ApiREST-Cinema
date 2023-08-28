package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.idat.entity.Clasificacion;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, Integer> {
    @Query(value="select count(*) from clasificaciones where nombre=?",nativeQuery=true)
    public abstract int isExistName(String nombre);
}

