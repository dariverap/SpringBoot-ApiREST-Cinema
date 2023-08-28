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

import pe.idat.entity.RoleEntity;
import pe.idat.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    public RoleController() {
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar_GET() {
        Collection<RoleEntity> collection = roleService.findAll();

        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar_POST(@RequestBody RoleEntity roleEntity) {
        int isExist = roleService.isExistType(roleEntity.getType());
        if (isExist == 0) {
            roleService.insert(roleEntity);
            return new ResponseEntity<>("¡Rol creado!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("¡No se puede registrar el rol porque el tipo " + roleEntity.getType() + " ya existe!", HttpStatus.CONFLICT);
    }

    @PutMapping("/editar/{roleId}")
    public ResponseEntity<?> editar_PUT(@RequestBody RoleEntity roleNew, @PathVariable Integer roleId) {
        RoleEntity roleDb = roleService.findById(roleId);

        if (roleDb != null) {
            int isExist = roleService.isExistType(roleNew.getType());
            if (isExist == 0) {
                roleDb.setType(roleNew.getType());
                roleService.update(roleDb);
                return new ResponseEntity<>("¡Rol editado!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("¡No se puede editar el rol porque el tipo " + roleNew.getType() + " ya existe!", HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("¡Rol con ID " + roleId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{roleId}")
    public ResponseEntity<?> borrar_DELETE(@PathVariable Integer roleId) {
        RoleEntity roleDb = roleService.findById(roleId);

        if (roleDb != null) {
            roleService.delete(roleId);
            return new ResponseEntity<>("¡Rol borrado!", HttpStatus.OK);
        }

        return new ResponseEntity<>("¡Rol con ID " + roleId + " no encontrado!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<?> buscar_GET(@PathVariable Integer roleId) {
        RoleEntity roleDb = roleService.findById(roleId);

        if (roleDb != null) {
            return new ResponseEntity<>(roleDb, HttpStatus.FOUND);
        }

        return new ResponseEntity<>("¡Rol con ID " + roleId + " no encontrado!", HttpStatus.NOT_FOUND);
    }
}

