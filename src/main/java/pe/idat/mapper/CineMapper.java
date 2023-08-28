package pe.idat.mapper;
import pe.idat.entity.Cine;


public class CineMapper  {

    private Integer cineId;
    
    private Integer aforo;
    
    private String direccion;

    private String nombre;

    private Integer numeroSalas;

    private String administrador;

    
    
	public CineMapper(Cine cine) {
		this(cine.getCineId(),cine.getAforo(),cine.getDireccion(),cine.getNombre(),cine.getNumeroSalas(),cine.getAdministrador().getNombre());
	}
    
	public CineMapper() {
	}

	public CineMapper(Integer cineId, Integer aforo, String direccion, String nombre, Integer numeroSalas,
			String administrador) {
		this.cineId = cineId;
		this.aforo = aforo;
		this.direccion = direccion;
		this.nombre = nombre;
		this.numeroSalas = numeroSalas;
		this.administrador = administrador;
	}

	public Integer getCineId() {
		return cineId;
	}

	public void setCineId(Integer cineId) {
		this.cineId = cineId;
	}

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroSalas() {
		return numeroSalas;
	}

	public void setNumeroSalas(Integer numeroSalas) {
		this.numeroSalas = numeroSalas;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}
    

}

