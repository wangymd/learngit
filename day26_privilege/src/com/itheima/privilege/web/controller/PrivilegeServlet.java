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
		//�õ���ɫ
		String [] roleIds = request.getParameterValues("roleIds");
		
		if(roleIds==null||roleIds.length==0){
			request.setAttribute("message", "��ѡ��Ҫ����Ľ�ɫ");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		s.grantRoles2User(userId,roleIds);
		request.setAttribute("message", "�����ɫ�ɹ�");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//
	private void grantRoles2UserUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String userId= request.getParameter("userId");
		User user = s.findUserById(userId);
		//��ѯ�û��Ѿ�����Ľ�ɫ
		request.setAttribute("user", user);
		//��ѯ���еĿ��ý�ɫ
		Set<Role> roles = s.findAllRoles();
		request.setAttribute("roles", roles);
		//ת����ҳ����ʾ
		request.getRequestDispatcher("/grantRoles2User.jsp").forward(request, response);
	}
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		User user = FillBeanUtil.fillBean(request, User.class);
		s.addUser(user);
		request.setAttribute("message", "����û��ɹ�");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	private void listUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		Set<User> users = s.findAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/listUsers.jsp").forward(request, response);
	}
	//����ɫ������Դ
	private void grantResource2Role(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		//�õ���ɫ
		String roleId = request.getParameter("roleId");
		//�õ���Դ
		String [] resourceIds = request.getParameterValues("resourceIds");
		
		if(resourceIds==null||resourceIds.length==0){
			request.setAttribute("message", "��ѡ��Ҫ�������Դ");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		s.grantResources2Role(roleId,resourceIds);
		request.setAttribute("message", "������Դ�ɹ�");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//����ɫ������Դ��ҳ��
	private void grantResources2RoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String roleId = request.getParameter("roleId");
		Role role = s.findRoleById(roleId);
		//��ѯ��ɫ�Ѿ�ӵ�е���Դ�б�
		request.setAttribute("role", role);
		//��ѯ���еĿ�����Դ
		Set<Resource> resources = s.findAllResources();
		request.setAttribute("resources", resources);
		//ת����ҳ����ʾ
		request.getRequestDispatcher("/grantResource2Role.jsp").forward(request, response);
		
	}
	//��ӽ�ɫ
	private void addRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		Role role = FillBeanUtil.fillBean(request, Role.class);
		s.addRole(role);
		request.setAttribute("message", "��ӽ�ɫ�ɹ�");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//�г���ɫ
	private void listRoles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		Set<Role> roles = s.findAllRoles();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/listRoles.jsp").forward(request, response);
	}
	//�����Դ
	private void addResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Resource resource = FillBeanUtil.fillBean(request, Resource.class);
		s.addResource(resource);
		request.setAttribute("message", "�����Դ�ɹ�");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	//��ѯ���е���Դ
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
