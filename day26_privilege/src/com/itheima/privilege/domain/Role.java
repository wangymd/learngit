package com.itheima.privilege.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {
	private Integer id;
	private String name;
	private String description;
	private Set<Resource> resources = new HashSet<Resource>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
}
