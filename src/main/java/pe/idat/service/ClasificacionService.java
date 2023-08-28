package pe.idat.service;
import pe.idat.entity.Clasificacion;

import java.util.Collection;

public interface ClasificacionService {

    void insert(Clasificacion clasificacion);

    void update(Clasificacion clasificacion);

    void delete(Integer clasificacionId);

    Clasificacion findById(Integer clasificacionId);

    Collection<Clasificacion> findAll();
    public abstract int isExistName(String nombre);
}

