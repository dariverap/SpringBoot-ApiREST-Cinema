package pe.idat.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.idat.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> 
{
	@Query(value="select * from users where username=?1",nativeQuery=true)	
	public abstract UserEntity findByUsername(String username);
	
    @Query(value="select * from users_roles",nativeQuery=true)
	public abstract Collection<Object[]> findAll_withRoles1();
	
	
	@Query(value="select  u.user_id, u.username as 'username' ,r.role_id, r.type as 'rol' \r\n"
			+ "from users_roles ur \r\n"
			+ "inner join roles r on ur.role_id=r.role_id \r\n"
			+ "inner join users u on ur.user_id=u.user_id;",nativeQuery=true)
	public abstract Collection<Object[]> findAll_withRoles2();
    
}
