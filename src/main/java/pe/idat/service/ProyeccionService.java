package pe.idat.service;

import pe.idat.entity.Proyeccion;

import java.util.Collection;

public interface ProyeccionService {

    void insert(Proyeccion proyeccion);

    void update(Proyeccion proyeccion);

    void delete(Integer proyeccionId);

    Proyeccion findById(Integer proyeccionId);

    Collection<Proyeccion> findAll();

}

