package com.itheima.privilege.web.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.privilege.domain.Resource;
import com.itheima.privilege.domain.Role;
import com.itheima.privilege.domain.User;
import com.itheima.privilege.service.PrivilegeService;
import com.itheima.privilege.service.impl.PrivilegeServiceImpl;
import com.itheima.util.FillBeanUtil;

public class PrivilegeServlet extends HttpServlet {
	private PrivilegeService s = new PrivilegeServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("listReosurces".equals(op)){
			listReosurces(request,response);
		}else if("addResource".equals(op)){
			addResource(request,response);
		}else if("listRoles".equals(op)){
			listRoles(request,response);
		}else if("addRole".equals(op)){
			addRole(request,response);
		}else if("grantResources2RoleUI".equals(op)){
			grantResources2RoleUI(request,response);
		}else if("grantResource2Role".equals(op)){
			grantResource2Role(request,response);
		}else if("listUsers".equals(op)){
			listUsers(request,response);
		}else if("addUser".equals(op)){
			addUser(request,response);
		}else if("grantRoles2UserUI".equals(op)){
			grantRoles2UserUI(request,response);
		}else if("grantRoles2User".equals(op)){
			grantRoles2User(request,response);
		}
	}
	private void grantRoles2User(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
		//得到角色
		String [] roleIds = request.getParameterValues("roleIds");
		
		if(roleIds==null||roleIds.length==0){
			request.setAttribute("message", "请选择要分配的角色");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		s.grantRoles2User(userId,roleIds);
		request.setAttribute("message", "分配角色成功");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//
	private void grantRoles2UserUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String userId= request.getParameter("userId");
		User user = s.findUserById(userId);
		//查询用户已经分配的角色
		request.setAttribute("user", user);
		//查询所有的可用角色
		Set<Role> roles = s.findAllRoles();
		request.setAttribute("roles", roles);
		//转发到页面显示
		request.getRequestDispatcher("/grantRoles2User.jsp").forward(request, response);
	}
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		User user = FillBeanUtil.fillBean(request, User.class);
		s.addUser(user);
		request.setAttribute("message", "添加用户成功");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	private void listUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		Set<User> users = s.findAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/listUsers.jsp").forward(request, response);
	}
	//给角色分配资源
	private void grantResource2Role(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		//得到角色
		String roleId = request.getParameter("roleId");
		//得到资源
		String [] resourceIds = request.getParameterValues("resourceIds");
		
		if(resourceIds==null||resourceIds.length==0){
			request.setAttribute("message", "请选择要分配的资源");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		s.grantResources2Role(roleId,resourceIds);
		request.setAttribute("message", "分配资源成功");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//给角色分配资源的页面
	private void grantResources2RoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String roleId = request.getParameter("roleId");
		Role role = s.findRoleById(roleId);
		//查询角色已经拥有的资源列表
		request.setAttribute("role", role);
		//查询所有的可用资源
		Set<Resource> resources = s.findAllResources();
		request.setAttribute("resources", resources);
		//转发到页面显示
		request.getRequestDispatcher("/grantResource2Role.jsp").forward(request, response);
		
	}
	//添加角色
	private void addRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		Role role = FillBeanUtil.fillBean(request, Role.class);
		s.addRole(role);
		request.setAttribute("message", "添加角色成功");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//列出角色
	private void listRoles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		Set<Role> roles = s.findAllRoles();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/listRoles.jsp").forward(request, response);
	}
	//添加资源
	private void addResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Resource resource = FillBeanUtil.fillBean(request, Resource.class);
		s.addResource(resource);
		request.setAttribute("message", "添加资源成功");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//查询所有的资源
	private void listReosurces(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		Set<Resource> resources = s.findAllResources();
		request.setAttribute("resources", resources);
		request.getRequestDispatcher("/listResources.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
