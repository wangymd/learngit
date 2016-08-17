package com.itheima.privilege.service;

import java.util.Set;

import com.itheima.privilege.domain.Resource;
import com.itheima.privilege.domain.Role;
import com.itheima.privilege.domain.User;

public interface PrivilegeService {
	/**
	 * 查询所有的资源
	 * @return
	 */
	Set<Resource> findAllResources();

	void addResource(Resource resource);

	Set<Role> findAllRoles();

	void addRole(Role role);
	/**
	 * 根据主键查询角色，同时把角色关联的资源查询出来
	 * @param roleId
	 * @return
	 */
	Role findRoleById(String roleId);
	/**
	 * 给角色分配资源
	 * @param roleId
	 * @param resourceIds
	 */
	void grantResources2Role(String roleId, String[] resourceIds);

	Set<User> findAllUsers();

	void addUser(User user);

	User findUserById(String userId);

	void grantRoles2User(String userId, String[] roleIds);
	
	User login(String username,String password);

}
