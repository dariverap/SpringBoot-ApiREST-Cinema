package pe.idat.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.idat.entity.Pelicula;
import pe.idat.service.PeliculaService;
import pe.idat.util.Mapper;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    public PeliculaController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Pelicula> collection = peliculaService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Mapper.toPeliculas(collection), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody Pelicula pelicula) {
        peliculaService.insert(pelicula);
        return new ResponseEntity<>("¡Película creada!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{peliculaId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Pelicula peliculaNew, @PathVariable Integer peliculaId) {
        Pelicula peliculaDb = peliculaService.findById(peliculaId);

        if (peliculaDb != null) {
            peliculaDb.setTitulo(peliculaNew.getTitulo());
            peliculaDb.setDirector(peliculaNew.getDirector());
            peliculaDb.setFechaEstreno(peliculaNew.getFechaEstreno());
            peliculaDb.setClasificacion(peliculaNew.getClasificacion());
            peliculaService.update(peliculaDb);
            return new ResponseEntity<>("¡Película editada!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Película ID " + peliculaId + " no encontrada!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{peliculaId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer peliculaId) {
        Pelicula peliculaDb = peliculaService.findById(peliculaId);

        if (peliculaDb != null) {
            peliculaService.delete(peliculaId);
            return new ResponseEntity<>("¡Película borrada!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Película ID " + peliculaId + " no encontrada!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{peliculaId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer peliculaId) {
        Pelicula peliculaDb = peliculaService.findById(peliculaId);

        if (peliculaDb != null) {
            return new ResponseEntity<>(peliculaDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Película ID " + peliculaId + " no encontrada!", HttpStatus.NOT_FOUND);
    }
}
