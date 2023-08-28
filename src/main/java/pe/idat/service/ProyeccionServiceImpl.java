package pe.idat.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.entity.Proyeccion;
import pe.idat.repository.ProyeccionRepository;

@Service
public class ProyeccionServiceImpl implements ProyeccionService {

    @Autowired
    private ProyeccionRepository repository;

    @Override
    @Transactional
    public void insert(Proyeccion proyeccion) {
        repository.save(proyeccion);
    }

    @Override
    @Transactional
    public void update(Proyeccion proyeccion) {
        repository.save(proyeccion);
    }

    @Override
    @Transactional
    public void delete(Integer proyeccionId) {
        repository.deleteById(proyeccionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Proyeccion findById(Integer proyeccionId) {
        return repository.findById(proyeccionId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Proyeccion> findAll() {
        return repository.findAll();
    }
}

