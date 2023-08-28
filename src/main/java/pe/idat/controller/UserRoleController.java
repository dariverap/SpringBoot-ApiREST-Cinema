package pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.entity.Genero;
import pe.idat.entity.Pelicula;
import pe.idat.entity.RoleEntity;
import pe.idat.entity.UserEntity;
import pe.idat.service.RoleService;
import pe.idat.service.UserService;
import pe.idat.vo.PeliculaGenero;
import pe.idat.vo.UserRole;

@RestController
@RequestMapping("/usuarios_roles")
public class UserRoleController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	public UserRoleController() {
	}
	
	@GetMapping("/listar1")
	public ResponseEntity<?> listar1_GET(){
		return new ResponseEntity<>(userService.findAll_withRoles1(),HttpStatus.OK);
	}
	
	@GetMapping("/listar2")
	public ResponseEntity<?> listar2_GET(){
		return new ResponseEntity<>(userService.findAll_withRoles2(),HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar_POST(@RequestBody UserRole userRole){
		
		Integer roleId=userRole.getRoleEntity().getRoleId();
		RoleEntity roleEntityDb = roleService.findById(roleId);
		
		if(roleEntityDb!=null) {
			
			Integer userId=userRole.getUserEntity().getUserId();
			UserEntity userEntityDb = userService.findById(userId);
			if (userEntityDb!=null) {
				
				userEntityDb.agregarRol(roleEntityDb);
				userService.update(userEntityDb);
				
				return new ResponseEntity<>("¡User Rol CREADO!",HttpStatus.CREATED);
				
			}
			return new ResponseEntity<>("¡Usuario no encontrada!",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("¡Rol no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/eliminar/{usuarioId}/{rolId}")
	public ResponseEntity<?> eliminar_PUT(@PathVariable Integer usuarioId, @PathVariable Integer rolId)
	{
		RoleEntity rolDb=roleService.findById(rolId);		
		if(rolDb!=null)
		{
			UserEntity usuarioDb=userService.findById(usuarioId);
			
			if(usuarioDb!=null)
			{
				if (usuarioDb.contieneRol(rolDb)) {
					
					usuarioDb.quitarRol(rolDb);
					userService.update(usuarioDb);
				
					return new ResponseEntity<>("¡Usuario Rol Eliminado!",HttpStatus.CREATED);
					
				}
				return new ResponseEntity<>("¡Usuario con ese Rol no encontrada!", HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>("¡Usuario no encontrado!",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>("¡Rol no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/editar/{usuarioId}/{rolId}")
	public ResponseEntity<?> editar_PUT(@RequestBody UserRole userRole, @PathVariable Integer usuarioId,
			@PathVariable Integer rolId) {
		
		RoleEntity rolDb=roleService.findById(rolId);		
		if(rolDb!=null)
		{
			UserEntity usuarioDb=userService.findById(usuarioId);
			if (usuarioDb != null) {
				
				if (usuarioDb.contieneRol(rolDb)) {
					
					usuarioDb.quitarRol(rolDb);
					usuarioDb.agregarRol(userRole.getRoleEntity());

					userService.update(usuarioDb);
					return new ResponseEntity<>("¡Usuario Rol Actualizado!", HttpStatus.CREATED);
				}
				return new ResponseEntity<>("¡Usuario con ese Rol no encontrado!", HttpStatus.NOT_FOUND);

			}
			return new ResponseEntity<>("¡Usuario no encontrado!", HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>("¡Rol no encontrado!", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
