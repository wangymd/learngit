package com.itheima.privilege.service.impl;

import java.util.Set;

import com.itheima.privilege.dao.ResourceDao;
import com.itheima.privilege.dao.RoleDao;
import com.itheima.privilege.dao.UserDao;
import com.itheima.privilege.dao.impl.ResourceDaoImpl;
import com.itheima.privilege.dao.impl.RoleDaoImpl;
import com.itheima.privilege.dao.impl.UserDaoImpl;
import com.itheima.privilege.domain.Resource;
import com.itheima.privilege.domain.Role;
import com.itheima.privilege.domain.User;
import com.itheima.privilege.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {
	private ResourceDao resourceDao = new ResourceDaoImpl();
	private RoleDao roleDao = new RoleDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	public Set<Resource> findAllResources() {
		return resourceDao.findAll();
	}
	public void addResource(Resource resource) {
		resourceDao.add(resource);
	}
	public Set<Role> findAllRoles() {
		return roleDao.findAll();
	}
	public void addRole(Role role) {
		roleDao.add(role);
	}
	public Role findRoleById(String roleId) {
		return roleDao.findById(roleId);
	}
	public void grantResources2Role(String roleId, String[] resourceIds) {
		//先删除角色对应资源的关系
		roleDao.delResourcesByRole(roleId);
		roleDao.insertRoleResource(roleId,resourceIds);
	}
	public Set<User> findAllUsers() {
		return userDao.findAll();
	}
	public void addUser(User user) {
		userDao.add(user);
	}
	public User findUserById(String userId) {
		return userDao.findById(userId);
	}
	public void grantRoles2User(String userId, String[] roleIds) {
		userDao.delRolesByUser(userId);
		userDao.insertUserRole(userId,roleIds);
	}
	public User login(String username, String password) {
		return userDao.find(username,password);
	}

}
