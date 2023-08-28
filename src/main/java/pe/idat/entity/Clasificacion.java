package pe.idat.entity;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "clasificaciones")
public class Clasificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clasificacionId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;
    
    @OneToMany(mappedBy = "clasificacion")
    private Collection<Pelicula> itemsPelicula = new ArrayList<>();
    
    
    // Constructor por defecto
    public Clasificacion() {
    }

    // Constructor con parámetros
    public Clasificacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public Integer getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(Integer clasificacionId) {
        this.clasificacionId = clasificacionId;
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

