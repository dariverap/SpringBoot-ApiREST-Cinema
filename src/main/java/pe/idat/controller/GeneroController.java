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

import pe.idat.entity.Genero;
import pe.idat.service.GeneroService;
import pe.idat.util.Mapper;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    public GeneroController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Genero> collection = generoService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Mapper.toGeneros(collection), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody Genero genero) {
        int isExist = generoService.isExistName(genero.getNombre());
        if (isExist==0) {
            generoService.insert(genero);
            return new ResponseEntity<>("¡Género creado!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("¡No se puede registrar el género porque el nombre " + genero.getNombre() + " ya existe!", HttpStatus.CONFLICT);
    }

    @PutMapping("/editar/{generoId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Genero generoNew, @PathVariable Integer generoId) {
        Genero generoDb = generoService.findById(generoId);

        if (generoDb != null) {
            int isExist = generoService.isExistName(generoNew.getNombre());
            if (isExist==0) {
                generoDb.setNombre(generoNew.getNombre());
                generoDb.setDescripcion(generoNew.getDescripcion());
                generoService.update(generoDb);
                return new ResponseEntity<>("¡Género editado!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("¡No se puede editar el género porque el nombre " + generoNew.getNombre() + " ya existe!", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("¡Género con ID " + generoId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{generoId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer generoId) {
        Genero generoDb = generoService.findById(generoId);

        if (generoDb != null) {
            generoService.delete(generoId);
            return new ResponseEntity<>("¡Género borrado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Género con ID " + generoId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{generoId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer generoId) {
        Genero generoDb = generoService.findById(generoId);

        if (generoDb != null) {
            return new ResponseEntity<>(generoDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Género con ID " + generoId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
}

