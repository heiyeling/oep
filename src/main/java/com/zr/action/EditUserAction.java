package com.zr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.User;

public class EditUserAction extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uid1 = req.getParameter("uid");
		int uid = Integer.parseInt(uid1);
		String uname = req.getParameter("uname");
		String utelephone = req.getParameter("utelephone");
		String uemail = req.getParameter("uemail");
		String uregistertime = req.getParameter("uregistertime");
		User user = new User();
		user.setU_id(uid);
		user.setU_name(uname);
		user.setU_telephone(utelephone);
		user.setU_email(uemail);
		user.setU_registertime(uregistertime);
		
		
	}
}
