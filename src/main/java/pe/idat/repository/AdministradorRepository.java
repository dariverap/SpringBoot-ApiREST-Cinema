package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.idat.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    @Query(value="select count(*) from administradores where dni=?",nativeQuery=true)
    public abstract int isExistDni(String dni);
}

