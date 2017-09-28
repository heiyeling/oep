package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.User;
import com.zr.service.UserService_leiwei;
import com.zr.service.impl.UserServiceImpl_leiwei;

import net.sf.json.JSONObject;
/**
 * leiwei
 * 编辑用户信息  
 * @author Administrator
 *
 */
public class EditUserAction extends HttpServlet{
	UserService_leiwei es = new UserServiceImpl_leiwei();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}  
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//获取参数
		String uid1 = req.getParameter("uid");
		int uid = Integer.parseInt(uid1);
		String uname = req.getParameter("uname");
		String utelephone = req.getParameter("utelephone");
		String uemail = req.getParameter("uemail");
		String uregistertime = req.getParameter("uregistertime");
		//给user赋值
		User user = new User();
		user.setU_id(uid);
		user.setU_name(uname);
		user.setU_telephone(utelephone);
		user.setU_email(uemail);
		user.setU_registertime(uregistertime);
		int i = es.editUser(user);
		
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		if(i>0){
			json.put("msg", "保存成功");
			json.put("url", req.getContextPath()+"/oep/selectuser.jsp");
		}else{
			json.put("msg", "保存失败");
			json.put("url", req.getContextPath()+"/oep/selectuser.jsp");
		}
		pw.write(json.toString());
	}
}
