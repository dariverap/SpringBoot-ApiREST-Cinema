package pe.idat.mapper;

import pe.idat.entity.Genero;

public class GeneroMapper  {
	
    private Integer generoId;
    private String nombre;
    private String descripcion;

    public GeneroMapper(Genero genero) {
    	this(genero.getGeneroId(),genero.getNombre(),genero.getDescripcion());
	}
    
	public GeneroMapper() {
	}
	
	
	public GeneroMapper(Integer generoId, String nombre, String descripcion) {
		this.generoId = generoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	public Integer getGeneroId() {
		return generoId;
	}


	public void setGeneroId(Integer generoId) {
		this.generoId = generoId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


    

    
    
}
