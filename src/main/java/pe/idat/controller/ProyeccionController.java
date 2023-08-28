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
import pe.idat.entity.Proyeccion;
import pe.idat.service.ProyeccionService;
import pe.idat.util.Mapper;

@RestController
@RequestMapping("/proyecciones")
public class ProyeccionController {

    @Autowired
    private ProyeccionService proyeccionService;

    public ProyeccionController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Proyeccion> collection = proyeccionService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Mapper.toProyecciones(collection), HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody Proyeccion proyeccion) {
        proyeccionService.insert(proyeccion);
        return new ResponseEntity<>("¡Proyección creada!", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{proyeccionId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Proyeccion proyeccionNew, @PathVariable Integer proyeccionId) {
        Proyeccion proyeccionDb = proyeccionService.findById(proyeccionId);

        if (proyeccionDb != null) {
            proyeccionDb.setIdioma(proyeccionNew.getIdioma());
            proyeccionDb.setTipo(proyeccionNew.getTipo());
            proyeccionDb.setFecha(proyeccionNew.getFecha());
            proyeccionDb.setCine(proyeccionNew.getCine());
            proyeccionDb.setPelicula(proyeccionNew.getPelicula());
            proyeccionService.update(proyeccionDb);
            return new ResponseEntity<>("¡Proyección editada!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Proyección ID " + proyeccionId + " no encontrada!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{proyeccionId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer proyeccionId) {
        Proyeccion proyeccionDb = proyeccionService.findById(proyeccionId);

        if (proyeccionDb != null) {
            proyeccionService.delete(proyeccionId);
            return new ResponseEntity<>("¡Proyección borrada!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Proyección ID " + proyeccionId + " no encontrada!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{proyeccionId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer proyeccionId) {
        Proyeccion proyeccionDb = proyeccionService.findById(proyeccionId);

        if (proyeccionDb != null) {
            return new ResponseEntity<>(proyeccionDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Proyección ID " + proyeccionId + " no encontrada!", HttpStatus.NOT_FOUND);
    }
}
