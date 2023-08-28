package pe.idat.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class RoleEntity implements Serializable
{
	private static final long serialVersionUID=1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer roleId;
	
	@Column(unique=true,nullable=false)
	private String type;
	
	@ManyToMany(mappedBy="itemsRole")
	@JsonIgnore
	private Set<UserEntity> itemsUser=new HashSet<>();
	
	public RoleEntity() {		
	}

	public RoleEntity(Integer roleId, String type) {
		this.roleId = roleId;
		this.type = type;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<UserEntity> getItemsUser() {
		return itemsUser;
	}

	public void setItemsUser(Set<UserEntity> itemsUser) {
		this.itemsUser = itemsUser;
	}
}
