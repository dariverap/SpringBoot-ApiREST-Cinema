package pe.idat.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idat.entity.Pelicula;
import pe.idat.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository repository;

    @Override
    @Transactional
    public void insert(Pelicula pelicula) {
        repository.save(pelicula);
    }

    @Override
    @Transactional
    public void update(Pelicula pelicula) {
        repository.save(pelicula);
    }

    @Override
    @Transactional
    public void delete(Integer peliculaId) {
        repository.deleteById(peliculaId);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula findById(Integer peliculaId) {
        return repository.findById(peliculaId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Pelicula> findAll() {
        return repository.findAll();
    }
    
	@Override
	@Transactional(readOnly=true)
	public Collection<Object[]> findAll_withGeneros1() {
		return repository.findAll_withGeneros1();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Collection<Object[]> findAll_withGeneros2() {
		return repository.findAll_withGeneros2();
	}
}
