package com.zr.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zr.service.RemoveUserService;
import com.zr.service.impl.RemoveUserServiceImpl;

public class RemoveUserAction extends HttpServlet{
	RemoveUserService rs = new RemoveUserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String rowsuid[] = req.getParameterValues("selectUid[]");
		List ids = new ArrayList();
		for (String s : rowsuid) {
			Integer.parseInt(s);
			ids.add(s);
		}
		
		List l =rs.RemoveUserByUid(ids);
		int i = ids.size();
		System.out.println(i);
	}
}
