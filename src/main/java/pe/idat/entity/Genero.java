package pe.idat.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "generos")
public class Genero implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer generoId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;
    
    @ManyToMany(mappedBy="itemsGenero")
    private Set<Pelicula> itemsPelicula=new HashSet<>();

	public Genero() {
	}

	public Genero(Integer generoId, String nombre, String descripcion, Set<Pelicula> itemsPelicula) {
		super();
		this.generoId = generoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.itemsPelicula = itemsPelicula;
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



	public Set<Pelicula> getItemsPelicula() {
		return itemsPelicula;
	}



	public void setItemsPelicula(Set<Pelicula> itemsPelicula) {
		this.itemsPelicula = itemsPelicula;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    
    
}
