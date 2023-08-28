package pe.idat.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer peliculaId;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;
    
    @ManyToOne
    @JoinColumn(name = "clasificacion_id",nullable = false)
    private Clasificacion clasificacion;
   
    @OneToMany(mappedBy = "pelicula")
    private Collection<Proyeccion> itemsProyeccion;
    // Constructor por defecto
    
  @ManyToMany
  @JoinTable(
          name = "pelicula_genero",
          joinColumns = @JoinColumn(name = "pelicula_id"),
          inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private Set<Genero> itemsGenero=new HashSet<>();
    
    public Pelicula() {
    }

    // Constructor con parámetros
    public Pelicula(String titulo, String director, LocalDate fechaEstreno, Clasificacion clasificacion) {
        this.titulo = titulo;
        this.director = director;
        this.fechaEstreno = fechaEstreno;
        this.clasificacion = clasificacion;
    }

	public void addGenero(Genero genero) {
		itemsGenero.add(genero);
	}
	
	public void quitarGenero(Genero genero) {
		itemsGenero.remove(genero);
	}
	
	public boolean contieneGenero(Genero generoAntiguo) {
	    if (itemsGenero.contains(generoAntiguo)) {
	        return true;
	    }else{
	    	return false;
	    }
	}

    // Getters y setters
    public Integer getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }
}

