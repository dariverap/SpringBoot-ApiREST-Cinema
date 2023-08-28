package pe.idat.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.entity.Administrador;
import pe.idat.repository.AdministradorRepository;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    @Override
    @Transactional
    public void insert(Administrador administrador) {
        repository.save(administrador);
    }

    @Override
    @Transactional
    public void update(Administrador administrador) {
        repository.save(administrador);
    }

    @Override
    @Transactional
    public void delete(Integer administradorId) {
        repository.deleteById(administradorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Administrador findById(Integer administradorId) {
        return repository.findById(administradorId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Administrador> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public int isExistDni(String dni) {
        return repository.isExistDni(dni);
    }
}

