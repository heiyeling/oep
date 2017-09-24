package com.zr.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author VerSion
 * @time 2017年9月21日下午3:00:40
 */
@SuppressWarnings("serial")
public class RegisterAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String conPassword = req.getParameter("conPassword");
		String email = req.getParameter("email");
		String telephone = req.getParameter("telephone");
		List<String> errors = new ArrayList<String>(); // 装载错误信息
}
	}

//	public boolean isValidPassword(String password, String conPassword) {
//		return password == null || conPassword == null || !password.equals(conPassword);
//	}
//
//	public boolean isValidEmail(String email) {
//		boolean flag = false;
//		if (email == null || "".equals(email)) {
//			flag = false;
//		} else {
//			flag = email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
//		}
//		return flag;
//	}


