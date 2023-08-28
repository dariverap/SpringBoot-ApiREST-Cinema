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
import pe.idat.service.GeneroService;
import pe.idat.service.PeliculaService;
import pe.idat.vo.PeliculaGenero;


@RestController
@RequestMapping("/pelicula_genero")
public class PeliculaGeneroRestController 
{
	@Autowired
	private GeneroService generoService;
	
	@Autowired
	private PeliculaService peliculaService;
	
	public PeliculaGeneroRestController() {		
	}
	
	@GetMapping("/listar1")
	public ResponseEntity<?> listar1_GET() {
		return new ResponseEntity<>(peliculaService.findAll_withGeneros1(),HttpStatus.OK);
	}
	
	@GetMapping("/listar2")
	public ResponseEntity<?> listar2_GET() {
		return new ResponseEntity<>(peliculaService.findAll_withGeneros2(),HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar_POST(@RequestBody PeliculaGenero peliculaGenero)
	{
		Integer generoId=peliculaGenero.getGenero().getGeneroId();
		Genero generoDb=generoService.findById(generoId);
		

		if(generoDb!=null)
		{
			Integer peliculaId=peliculaGenero.getPelicula().getPeliculaId();
			Pelicula peliculaDb=peliculaService.findById(peliculaId);
			
			if(peliculaDb!=null)
			{
				peliculaDb.addGenero(generoDb);
				peliculaService.update(peliculaDb);
				
				return new ResponseEntity<>("¡Pelicula Genero CREADO!",HttpStatus.CREATED);
			}
			
			return new ResponseEntity<>("¡Pelicula no encontrada!",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>("¡Genero no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/eliminar/{peliculaId}/{generoId}")
	public ResponseEntity<?> eliminar_PUT(@PathVariable Integer peliculaId, @PathVariable Integer generoId)
	{
		Genero generoDb=generoService.findById(generoId);		
		if(generoDb!=null)
		{
			Pelicula peliculaDb=peliculaService.findById(peliculaId);
			
			if(peliculaDb!=null)
			{
				if (peliculaDb.contieneGenero(generoDb)) {
					
					peliculaDb.quitarGenero(generoDb);
					peliculaService.update(peliculaDb);
				
					return new ResponseEntity<>("¡Pelicula Genero Eliminado!",HttpStatus.CREATED);
					
				}
				return new ResponseEntity<>("¡Pelicula con ese Genero no encontrada!", HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>("¡Pelicula no encontrada!",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>("¡Genero no encontrado!",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/editar/{peliculaId}/{generoId}")
	public ResponseEntity<?> editar_PUT(@RequestBody PeliculaGenero peliculaGenero, @PathVariable Integer peliculaId,
			@PathVariable Integer generoId) {
		
		Genero generoDb = generoService.findById(generoId);
		if (generoDb != null) {
			Pelicula peliculaDb = peliculaService.findById(peliculaId);
			if (peliculaDb != null) {
				
				if (peliculaDb.contieneGenero(generoDb)) {
					
					peliculaDb.quitarGenero(generoDb);
					peliculaDb.addGenero(peliculaGenero.getGenero());

					peliculaService.update(peliculaDb);
					return new ResponseEntity<>("¡Pelicula Genero Actualizado!", HttpStatus.CREATED);
				}
				return new ResponseEntity<>("¡Pelicula con ese Genero no encontrada!", HttpStatus.NOT_FOUND);

			}
			return new ResponseEntity<>("¡Pelicula no encontrada!", HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>("¡Genero no encontrado!", HttpStatus.NOT_FOUND);
	}
}










