package pe.idat.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.entity.Clasificacion;
import pe.idat.repository.ClasificacionRepository;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {

    @Autowired
    private ClasificacionRepository repository;

    @Override
    @Transactional
    public void insert(Clasificacion clasificacion) {
        repository.save(clasificacion);
    }

    @Override
    @Transactional
    public void update(Clasificacion clasificacion) {
        repository.save(clasificacion);
    }

    @Override
    @Transactional
    public void delete(Integer clasificacionId) {
        repository.deleteById(clasificacionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Clasificacion findById(Integer clasificacionId) {
        return repository.findById(clasificacionId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Clasificacion> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public int isExistName(String nombre) {
        return repository.isExistName(nombre);
    }
}

