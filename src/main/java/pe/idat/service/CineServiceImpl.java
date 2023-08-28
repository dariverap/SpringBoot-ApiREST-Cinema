package pe.idat.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.entity.Cine;
import pe.idat.repository.CineRepository;

@Service
public class CineServiceImpl implements CineService {

    @Autowired
    private CineRepository repository;

    @Override
    @Transactional
    public void insert(Cine cine) {
        repository.save(cine);
    }

    @Override
    @Transactional
    public void update(Cine cine) {
        repository.save(cine);
    }

    @Override
    @Transactional
    public void delete(Integer cineId) {
        repository.deleteById(cineId);
    }

    @Override
    @Transactional(readOnly = true)
    public Cine findById(Integer cineId) {
        return repository.findById(cineId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Cine> findAll() {
        return repository.findAll();
    }
}

