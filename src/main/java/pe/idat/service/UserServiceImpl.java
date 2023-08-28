package pe.idat.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.RoleEntity;
import pe.idat.entity.UserEntity;
import pe.idat.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService
{
	@Autowired
	private UserRepository repository;
	
	public UserServiceImpl() {		
	}	

	@Override
	@Transactional(readOnly=true)
	public UserEntity findByUsername(String username) {
		return repository.findByUsername(username);		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserEntity userDb=this.findByUsername(username);
		
		if(userDb!=null)
		{
			Collection<GrantedAuthority> authorities=new ArrayList<>();
			
			for(RoleEntity role:userDb.getItemsRole()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getType()));
			}
			
			User user=new User(userDb.getUsername(),userDb.getPassword(),authorities);
			return user;
		}
		
		throw new UsernameNotFoundException("¡Error, username no existe!"); 
	}

    @Override
    @Transactional
    public void insert(UserEntity userEntity) {
        repository.save(userEntity);
    }

    @Override
    @Transactional
    public void update(UserEntity userEntity) {
        repository.save(userEntity);
    }

    @Override
    @Transactional
    public void delete(Integer userEntityId) {
        repository.deleteById(userEntityId);
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<UserEntity> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(Integer userEntityId) {
        return repository.findById(userEntityId).orElse(null);
    }
    
	@Override
	@Transactional(readOnly=true)
	public Collection<Object[]> findAll_withRoles1() {
		return repository.findAll_withRoles1();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Collection<Object[]> findAll_withRoles2() {
		return repository.findAll_withRoles2();
	}

}
















