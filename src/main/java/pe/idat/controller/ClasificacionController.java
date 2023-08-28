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
import pe.idat.entity.Clasificacion;
import pe.idat.service.ClasificacionService;

@RestController
@RequestMapping("/clasificaciones")
public class ClasificacionController {

    @Autowired
    private ClasificacionService clasificacionService;

    public ClasificacionController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Clasificacion> collection = clasificacionService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody Clasificacion clasificacion) {
    	int isExist = clasificacionService.isExistName(clasificacion.getNombre());
        if(isExist==0) {
            clasificacionService.insert(clasificacion);
            return new ResponseEntity<>("¡Clasificación creada!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("¡No se puede registrar la clasificacione porque el nombre "+clasificacion.getNombre()+" ya existe!", HttpStatus.CONFLICT);
    }

    @PutMapping("/editar/{clasificacionId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Clasificacion clasificacionNew, @PathVariable Integer clasificacionId) {
        Clasificacion clasificacionDb = clasificacionService.findById(clasificacionId);

        if (clasificacionDb != null) {
        	int isExist = clasificacionService.isExistName(clasificacionNew.getNombre());
            if(isExist==0) {
	            clasificacionDb.setNombre(clasificacionNew.getNombre());
	            clasificacionDb.setDescripcion(clasificacionNew.getDescripcion());
	            clasificacionService.update(clasificacionDb);
	            return new ResponseEntity<>("¡Clasificación editada!", HttpStatus.OK);
            }else {
            	return new ResponseEntity<>("¡No se puede Editar la clasificacion porque el nombre "+clasificacionNew.getNombre()+" ya existe!", HttpStatus.CONFLICT);

            }
        }    
        return new ResponseEntity<>("¡Clasificación con ID " + clasificacionId + " no encontrada!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{clasificacionId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer clasificacionId) {
        Clasificacion clasificacionDb = clasificacionService.findById(clasificacionId);

        if (clasificacionDb != null) {
            clasificacionService.delete(clasificacionId);
            return new ResponseEntity<>("¡Clasificación borrada!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Clasificación con ID " + clasificacionId + " no encontrada!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{clasificacionId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer clasificacionId) {
        Clasificacion clasificacionDb = clasificacionService.findById(clasificacionId);

        if (clasificacionDb != null) {
            return new ResponseEntity<>(clasificacionDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Clasificación ID " + clasificacionId + " no encontrada!", HttpStatus.NOT_FOUND);
    }
}

