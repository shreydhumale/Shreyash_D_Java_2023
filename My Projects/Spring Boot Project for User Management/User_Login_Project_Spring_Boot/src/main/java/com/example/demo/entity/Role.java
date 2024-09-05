package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Role {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int roleId;
	    private String roleName;
	   

	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id")
	    private Users users;
	    
		public Role() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Role(int roleId, String roleName, Users users) {
			super();
			this.roleId = roleId;
			this.roleName = roleName;
			this.users = users;
		}

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public Users getUsers() {
			return users;
		}

		public void setUsers(Users users) {
			this.users = users;
		}

		@Override
		public String toString() {
			return "Role [roleId=" + roleId + ", roleName=" + roleName + ", users=" + users + "]";
		}
	    
}
