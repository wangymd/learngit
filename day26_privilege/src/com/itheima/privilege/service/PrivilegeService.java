package com.itheima.privilege.service;

import java.util.Set;

import com.itheima.privilege.domain.Resource;
import com.itheima.privilege.domain.Role;
import com.itheima.privilege.domain.User;

public interface PrivilegeService {
	/**
	 * ��ѯ���е���Դ
	 * @return
	 */
	Set<Resource> findAllResources();

	void addResource(Resource resource);

	Set<Role> findAllRoles();

	void addRole(Role role);
	/**
	 * ����������ѯ��ɫ��ͬʱ�ѽ�ɫ��������Դ��ѯ����
	 * @param roleId
	 * @return
	 */
	Role findRoleById(String roleId);
	/**
	 * ����ɫ������Դ
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
