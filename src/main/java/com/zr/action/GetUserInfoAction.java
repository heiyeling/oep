package com.zr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import com.zr.model.User;
import com.zr.service.GetUserInfoService;
import com.zr.service.impl.GetUserInfoServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetUserInfoAction extends HttpServlet{
	GetUserInfoService gs = new GetUserInfoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("utf-8");
		
		//获取参数
		String userkey = req.getParameter("userkey");//搜索关键词
		int page = Integer.valueOf(req.getParameter("page"));//当前页码
		int pageSize = Integer.valueOf(req.getParameter("rows"));//每页行数
		JSONObject json = gs.getUserInfo(page, pageSize, userkey);
		resp.getWriter().write(json.toString());
	}

}
