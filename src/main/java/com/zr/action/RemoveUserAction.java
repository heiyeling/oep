package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zr.service.UserService_leiwei;
import com.zr.service.impl.UserServiceImpl_leiwei;

import net.sf.json.JSONObject;
/**
 * leiwei
 * 删除用户记录  
 * @author Administrator
 *
 */
public class RemoveUserAction extends HttpServlet{
	UserService_leiwei rs = new UserServiceImpl_leiwei();
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
		int l =rs.RemoveUserByUid(ids);
		
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		if(l>0){
			json.put("msg", "删除成功");
			json.put("url", req.getContextPath()+"/oep/selectuser.jsp");
		}else{
			json.put("msg", "删除失败");
			json.put("url", req.getContextPath()+"/oep/selectuser.jsp");
		}
		pw.write(json.toString());
		
	}
}
