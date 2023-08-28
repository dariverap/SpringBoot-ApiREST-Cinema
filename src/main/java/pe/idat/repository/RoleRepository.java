package pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.idat.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer>  {
	
    @Query(value="select count(*) from roles where type=?",nativeQuery=true)
    public abstract int isExistType(String type);
    
}
