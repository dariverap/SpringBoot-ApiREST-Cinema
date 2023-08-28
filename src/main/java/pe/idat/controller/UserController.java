package pe.idat.controller;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.entity.Genero;
import pe.idat.entity.RoleEntity;
import pe.idat.entity.UserEntity;
import pe.idat.service.GeneroService;
import pe.idat.service.UserService;
import pe.idat.util.Mapper;


@RestController
@RequestMapping("/usuarios")
public class UserController {
	
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<UserEntity> collection = userService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }
    
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody UserEntity userEntity) {
    	UserEntity userDb=userService.findByUsername(userEntity.getUsername());
		
		if(userDb==null)
		{	
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			userService.insert(userEntity);
            return new ResponseEntity<>("¡Usuario creado!", HttpStatus.CREATED);
		}
        return new ResponseEntity<>("¡No se puede registrar el usuario porque el username " + userEntity.getUsername() + " ya existe!", HttpStatus.CONFLICT);
    }
    
    @DeleteMapping("/borrar/{userEntityId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer userEntityId) {
    	UserEntity userEntityIdDb = userService.findById(userEntityId);

        if (userEntityIdDb != null) {
        	userService.delete(userEntityId);
            return new ResponseEntity<>("¡Usuario borrado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Usuario con ID " + userEntityId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/buscar/{userEntityId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer userEntityId) {
    	UserEntity userEntityIdDb = userService.findById(userEntityId);

        if (userEntityIdDb != null) {
            return new ResponseEntity<>(userEntityIdDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Usuario con ID " + userEntityId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
    
    
    
    @PutMapping("/editar/{userEntityId}")
    public ResponseEntity<?> editar_PUT(@RequestBody UserEntity userNew, @PathVariable Integer userEntityId) {

        UserEntity userEntityIdDb = userService.findById(userEntityId);

        if (userEntityIdDb != null) {

            UserEntity userDb = userService.findByUsername(userNew.getUsername());
            if (userDb == null || userDb.getUsername().equals(userNew.getUsername())) {

                userEntityIdDb.setNombre(userNew.getNombre());
                userEntityIdDb.setApellidos(userNew.getApellidos());
                userEntityIdDb.setEdad(userNew.getEdad());
                userEntityIdDb.setEmail(userNew.getEmail());
                userEntityIdDb.setDireccion(userNew.getDireccion());
                userEntityIdDb.setUsername(userNew.getUsername());
                
                userEntityIdDb.setPassword(passwordEncoder.encode(userNew.getPassword()));
                
                userService.update(userEntityIdDb);

                return new ResponseEntity<>("¡Usuario editado!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("¡No se puede editar el usuario porque el nombre de usuario " + userNew.getUsername() + " ya existe!", HttpStatus.CONFLICT);
            }
        }

        return new ResponseEntity<>("¡Usuario con ID " + userEntityId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
    
    
    
    
    
    
    
    
}
