package pe.idat.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "proyecciones")
public class Proyeccion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proyeccionId;

    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false)
    private String tipo;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
    
    @ManyToOne
    @JoinColumn(name = "cine_id",nullable = false)
    private Cine cine;

    @ManyToOne
    @JoinColumn(name = "pelicula_id",nullable = false)
    private Pelicula pelicula;

    public Proyeccion(Integer proyeccionId, String idioma, String tipo, LocalDate fecha) {
        this.proyeccionId = proyeccionId;
        this.idioma = idioma;
        this.tipo = tipo;
        this.fecha = fecha;
    }



    public Proyeccion() {
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

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
