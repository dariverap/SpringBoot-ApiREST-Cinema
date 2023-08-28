package pe.idat.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer administradorId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String email;
    
    @OneToOne(mappedBy = "administrador")
    private Cine cine;


    // Constructor con parámetros

    public Administrador(Integer administradorId, String nombre, String dni, String telefono, String email) {
        this.administradorId = administradorId;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor por defecto
    public Administrador() {
    }
    // Getters y setters
    public Integer getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Integer administradorId) {
        this.administradorId = administradorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}

