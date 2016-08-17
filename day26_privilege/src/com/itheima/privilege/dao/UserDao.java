package com.itheima.privilege.dao;

import java.util.Set;

import com.itheima.privilege.domain.User;

public interface UserDao {

	Set<User> findAll();

	void add(User user);

	User findById(String userId);

	void delRolesByUser(String userId);

	void insertUserRole(String userId, String[] roleIds);

	User find(String username, String password);

}
