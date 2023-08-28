package pe.idat.mapper;
import pe.idat.entity.Pelicula;
import java.time.LocalDate;

public class PeliculaMapper  {

    private Integer peliculaId;

    private String director;

    private LocalDate fechaEstreno;
    
    private String titulo;
    
    private String clasificacion;

    
	public PeliculaMapper(Pelicula pelicula) {
		this(pelicula.getPeliculaId(),pelicula.getDirector(),pelicula.getFechaEstreno(),pelicula.getTitulo(),pelicula.getClasificacion().getNombre());
	}
    
	public PeliculaMapper() {

	}

	public PeliculaMapper(Integer peliculaId, String director, LocalDate fechaEstreno, String titulo,
			String clasificacion) {
		this.peliculaId = peliculaId;
		this.director = director;
		this.fechaEstreno = fechaEstreno;
		this.titulo = titulo;
		this.clasificacion = clasificacion;
	}

	public Integer getPeliculaId() {
		return peliculaId;
	}

	public void setPeliculaId(Integer peliculaId) {
		this.peliculaId = peliculaId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
    
    
}

