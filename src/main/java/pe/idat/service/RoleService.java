package pe.idat.service;

import java.util.Collection;

import pe.idat.entity.Genero;
import pe.idat.entity.RoleEntity;

public interface RoleService {

    void insert(RoleEntity roleEntity);

    void update(RoleEntity roleEntity);

    void delete(Integer roleEntityId);

    RoleEntity findById(Integer roleEntityId);

    Collection<RoleEntity> findAll();
  
    public abstract int isExistType(String type);

}
