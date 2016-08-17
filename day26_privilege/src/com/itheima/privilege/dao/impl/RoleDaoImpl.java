package com.itheima.privilege.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.privilege.dao.RoleDao;
import com.itheima.privilege.domain.Resource;
import com.itheima.privilege.domain.Role;
import com.itheima.util.C3P0Util;

public class RoleDaoImpl implements RoleDao {
	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	public Set<Role> findAll() {
		try {
			List<Role> roles = qr.query(" select * from roles ", new BeanListHandler<Role>(Role.class));
			return new HashSet<Role>(roles);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void add(Role role) {
		try {
			qr.update("insert into roles (name,description) values (?,?)",role.getName(),role.getDescription());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Role findById(String roleId) {
		try {
			Role role = qr.query("select * from roles where id=?", new BeanHandler<Role>(Role.class), roleId);
			if(role!=null){
				List<Resource> resources = qr.query("select * from resources where id in (select resource_id from role_resource where role_id=?)", new BeanListHandler<Resource>(Resource.class), roleId);
				role.setResources(new HashSet<Resource>(resources));
			}
		return role;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void delResourcesByRole(String roleId) {
		try {
			qr.update("delete from role_resource where role_id=?", roleId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void insertRoleResource(String roleId, String[] resourceIds) {
		try {
			Object params[][] = new Object[resourceIds.length][];
			for(int i=0;i<resourceIds.length;i++){
				params[i] = new Object[]{roleId,resourceIds[i]};
			}
			qr.batch("insert into role_resource (role_id,resource_id) values(?,?)", params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
