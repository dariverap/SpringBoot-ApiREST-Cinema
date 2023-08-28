package pe.idat.service;

import pe.idat.entity.Cine;

import java.util.Collection;

public interface CineService {

    void insert(Cine cine);

    void update(Cine cine);

    void delete(Integer cineId);

    Cine findById(Integer cineId);

    Collection<Cine> findAll();

}

