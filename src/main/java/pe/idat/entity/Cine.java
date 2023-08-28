package pe.idat.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "cines")
public class Cine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cineId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Integer numeroSalas;

    @Column(nullable = false)
    private Integer aforo;
    
    @OneToOne
    @JoinColumn(name="administrador_id",unique = true,nullable = false)
    private Administrador administrador;

//    @ManyToMany(mappedBy = "cine")
//    private Collection<Pelicula> pelicula;
//    Constructor, getters y setters
    
    @OneToMany(mappedBy = "cine")
    private Collection<Proyeccion> itemsProyeccion;
    // Constructor por defecto
    public Cine() {
    }

    // Constructor con parámetros
    public Cine(String nombre, String direccion, Integer numeroSalas, Integer aforo, Administrador administrador) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroSalas = numeroSalas;
        this.aforo = aforo;
        this.administrador = administrador;
    }

    // Getters y setters
    public Integer getCineId() {
        return cineId;
    }

    public void setCineId(Integer cineId) {
        this.cineId = cineId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumeroSalas() {
        return numeroSalas;
    }

    public void setNumeroSalas(Integer numeroSalas) {
        this.numeroSalas = numeroSalas;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}

