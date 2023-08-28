package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.UserEntity;

public interface UserService 
{
	public abstract UserEntity findByUsername(String username);
	
    void insert(UserEntity userEntity);

    void update(UserEntity userEntity);

    void delete(Integer userEntityId);

    Collection<UserEntity> findAll();
  
    UserEntity findById(Integer userEntityId);
    
    
	public abstract Collection<Object[]> findAll_withRoles1();
	public abstract Collection<Object[]> findAll_withRoles2();
	
}
