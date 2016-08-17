package com.itheima.privilege.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.privilege.dao.ResourceDao;
import com.itheima.privilege.domain.Resource;
import com.itheima.util.C3P0Util;

public class ResourceDaoImpl implements ResourceDao {
	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	public Set<Resource> findAll() {
		try {
			List<Resource> resources = qr.query(" select * from resources ", new BeanListHandler<Resource>(Resource.class));
			return new HashSet<Resource>(resources);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void add(Resource resource) {
		try {
			qr.update("insert into resources (name,uri,description) values (?,?,?)", resource.getName(),resource.getUri(),resource.getDescription());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
