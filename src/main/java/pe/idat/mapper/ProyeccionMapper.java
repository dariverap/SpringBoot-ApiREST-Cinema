package pe.idat.mapper;

import java.time.LocalDate;

import pe.idat.entity.Proyeccion;


public class ProyeccionMapper  {

    private Integer proyeccionId;

    private String idioma;

    private String tipo;
    
    private LocalDate fecha;
    
    private String cine;
    
    private String pelicula;

    
	public ProyeccionMapper(Proyeccion proyeccion) {
		this(proyeccion.getProyeccionId(),proyeccion.getIdioma(),proyeccion.getTipo(),proyeccion.getFecha(),proyeccion.getCine().getNombre(),proyeccion.getPelicula().getTitulo());
	}
    
	public ProyeccionMapper() {

	}

	public ProyeccionMapper(Integer proyeccionId, String idioma, String tipo, LocalDate fecha, String cine, String pelicula) {
		this.proyeccionId = proyeccionId;
		this.idioma = idioma;
		this.tipo = tipo;
		this.fecha = fecha;
		this.cine = cine;
		this.pelicula = pelicula;
	}

	public Integer getProyeccionId() {
		return proyeccionId;
	}

	public void setProyeccionId(Integer proyeccionId) {
		this.proyeccionId = proyeccionId;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getCine() {
		return cine;
	}

	public void setCine(String cine) {
		this.cine = cine;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

    
}
