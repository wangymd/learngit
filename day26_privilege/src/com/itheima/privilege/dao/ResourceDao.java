package com.itheima.privilege.dao;

import java.util.Set;

import com.itheima.privilege.domain.Resource;

public interface ResourceDao {

	Set<Resource> findAll();

	void add(Resource resource);

}
