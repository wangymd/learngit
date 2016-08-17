package com.itheima.privilege.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.privilege.dao.UserDao;
import com.itheima.privilege.domain.Role;
import com.itheima.privilege.domain.User;
import com.itheima.util.C3P0Util;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	public Set<User> findAll() {
		try {
			List<User> users = qr.query(" select * from users ", new BeanListHandler<User>(User.class));
			return new HashSet<User>(users);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void add(User user) {
		try {
			qr.update("insert into users (username,password) values (?,?)",user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public User findById(String userId) {
		try {
			User user = qr.query("select * from users where id=?", new BeanHandler<User>(User.class), userId);
			if(user!=null){
				List<Role> roles = qr.query("select * from roles where id in (select role_id from user_role where user_id=?)", new BeanListHandler<Role>(Role.class), userId);
				user.setRoles(new HashSet<Role>(roles));
			}
		return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delRolesByUser(String userId) {
		try {
			qr.update("delete from user_role where user_id=?", userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public void insertUserRole(String userId, String[] roleIds) {
		try {
			Object params[][] = new Object[roleIds.length][];
			for(int i=0;i<roleIds.length;i++){
				params[i] = new Object[]{userId,roleIds[i]};
			}
			qr.batch("insert into user_role (user_id,role_id) values(?,?)", params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public User find(String username, String password) {
		try {
			return qr.query(" select * from users where username=? and password=? ", new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
