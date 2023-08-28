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
import pe.idat.entity.Administrador;
import pe.idat.service.AdministradorService;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    public AdministradorController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<Administrador> collection = administradorService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody Administrador administrador) {
        int isExist = administradorService.isExistDni(administrador.getDni());

        if(isExist==0) {
            administradorService.insert(administrador);
            return new ResponseEntity<>("¡Administrador creado!", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("¡No se puede registrar el administrador porque el DNI "+administrador.getDni()+" ya existe!", HttpStatus.CONFLICT);
    }

    @PutMapping("/editar/{administradorId}")
    public ResponseEntity<?> editar_PUT(@RequestBody Administrador administradorNew, @PathVariable Integer administradorId) {
    	
    	Administrador administradorDb = administradorService.findById(administradorId);
    	
    	if(administradorDb != null) {
    		int isExist = administradorService.isExistDni(administradorNew.getDni());
    		System.out.println(isExist);
	        if (isExist==0) {
	            administradorDb.setNombre(administradorNew.getNombre());
	            administradorDb.setDni(administradorNew.getDni());
	            administradorDb.setTelefono(administradorNew.getTelefono());
	            administradorDb.setEmail(administradorNew.getEmail());
	            administradorDb.setDni(administradorNew.getDni());
	            administradorService.update(administradorDb);
	            return new ResponseEntity<>("¡Administrador editado!", HttpStatus.OK);
	        }else {
	        	return new ResponseEntity<>("¡No se puede Editar el administrador porque el DNI "+administradorNew.getDni()+" ya existe!", HttpStatus.CONFLICT);
	        
	        }
    	}
        return new ResponseEntity<>("¡Administrador con ID " + administradorId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{administradorId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer administradorId) {
        Administrador administradorDb = administradorService.findById(administradorId);

        if (administradorDb != null) {
            administradorService.delete(administradorId);
            return new ResponseEntity<>("¡Administrador borrado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Administrador ID " + administradorId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{administradorId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer administradorId) {
        Administrador administradorDb = administradorService.findById(administradorId);

        if (administradorDb != null) {
            return new ResponseEntity<>(administradorDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Administrador con ID " + administradorId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
}


