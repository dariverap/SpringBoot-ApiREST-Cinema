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
import pe.idat.entity.Cine;
import pe.idat.service.CineService;
import pe.idat.util.Mapper;

@RestController
@RequestMapping("/cines")
public class CineController {

    @Autowired
    private CineService cineService;

    public CineController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Cine> collection = cineService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Mapper.toCines(collection), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody Cine cine) {

        cineService.insert(cine);
        return new ResponseEntity<>("¡Cine creado!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{cineId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Cine cineNew, @PathVariable Integer cineId) {
        Cine cineDb = cineService.findById(cineId);

        if (cineDb != null) {
            cineDb.setNombre(cineNew.getNombre());
            cineDb.setDireccion(cineNew.getDireccion());
            cineDb.setNumeroSalas(cineNew.getNumeroSalas());
            cineDb.setAforo(cineNew.getAforo());
            cineDb.setAdministrador(cineNew.getAdministrador());
            cineService.update(cineDb);
            return new ResponseEntity<>("¡Cine editado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Cine ID " + cineId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{cineId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer cineId) {
        Cine cineDb = cineService.findById(cineId);

        if (cineDb != null) {
            cineService.delete(cineId);
            return new ResponseEntity<>("¡Cine borrado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Cine ID " + cineId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{cineId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer cineId) {
        Cine cineDb = cineService.findById(cineId);

        if (cineDb != null) {
            return new ResponseEntity<>(cineDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Cine ID " + cineId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
}

