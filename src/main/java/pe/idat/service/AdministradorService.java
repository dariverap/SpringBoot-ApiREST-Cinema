package pe.idat.service;
import pe.idat.entity.Administrador;

import java.util.Collection;

public interface AdministradorService {

    void insert(Administrador administrador);

    void update(Administrador administrador);

    void delete(Integer administradorId);

    Administrador findById(Integer administradorId);

    Collection<Administrador> findAll();
    
    public abstract int isExistDni(String dni);
}

