package pe.idat.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class UserEntity implements Serializable
{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;	
	
	@Column(unique=true,nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidos;
	
	@Column
	private Integer edad;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	@Column
	private String direccion;
	
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	private LocalDate dateCreated;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="users_roles",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<RoleEntity> itemsRole=new HashSet<>();
	
	public UserEntity() {		
	}


	public UserEntity(Integer userId, String username, String password, String nombre, String apellidos, Integer edad,
			String email, String direccion, LocalDate dateCreated, Set<RoleEntity> itemsRole) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.email = email;
		this.direccion = direccion;
		this.dateCreated = dateCreated;
		this.itemsRole = itemsRole;
	}



	public void agregarRol(RoleEntity roleEntity) {
		itemsRole.add(roleEntity);
	}
	
	public void quitarRol(RoleEntity roleEntity) {
		itemsRole.remove(roleEntity);
	}
	
	public boolean contieneRol(RoleEntity rolAntiguo) {
	    if (itemsRole.contains(rolAntiguo)) {
	        return true;
	    }else{
	    	return false;
	    }
	}
	
	@PrePersist
	public void prePersist() {
		dateCreated=LocalDate.now();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Set<RoleEntity> getItemsRole() {
		return itemsRole;
	}

	public void setItemsRole(Set<RoleEntity> itemsRole) {
		this.itemsRole = itemsRole;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
