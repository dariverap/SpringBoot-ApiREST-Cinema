package pe.idat.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.entity.RoleEntity;
import pe.idat.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	   @Autowired
	    private RoleRepository repository;

	    @Override
	    @Transactional
	    public void insert(RoleEntity roleEntity) {
	        repository.save(roleEntity);
	    }

	    @Override
	    @Transactional
	    public void update(RoleEntity roleEntity) {
	        repository.save(roleEntity);
	    }

	    @Override
	    @Transactional
	    public void delete(Integer roleEntityId) {
	        repository.deleteById(roleEntityId);
	    }


	    @Override
	    @Transactional(readOnly = true)
	    public Collection<RoleEntity> findAll() {
	        return repository.findAll();
	    }
	    
	    @Override
	    @Transactional(readOnly = true)
	    public RoleEntity findById(Integer roleEntityId) {
	        return repository.findById(roleEntityId).orElse(null);
	    }
	    
	   @Override
	   @Transactional(readOnly=true)
	   public int isExistType(String type) {
	       return repository.isExistType(type);
	   }

}
