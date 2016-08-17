package com.itheima.privilege.dao;

import java.util.Set;

import com.itheima.privilege.domain.Role;

public interface RoleDao {

	Set<Role> findAll();

	void add(Role role);

	Role findById(String roleId);

	void delResourcesByRole(String roleId);

	void insertRoleResource(String roleId, String[] resourceIds);

}
