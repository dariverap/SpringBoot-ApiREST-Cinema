package pe.idat.vo;

import pe.idat.entity.RoleEntity;
import pe.idat.entity.UserEntity;

public class UserRole {

	private UserEntity userEntity;
	private RoleEntity roleEntity;
	
	public UserRole() {
	}

	public UserRole(UserEntity userEntity, RoleEntity roleEntity) {
		this.userEntity = userEntity;
		this.roleEntity = roleEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}
	
	
	
}

