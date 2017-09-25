package com.zr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zr.dao.UserDao;
import com.zr.dao.impl.UserDaoImpl;
import com.zr.model.User;
import com.zr.service.GetUserInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetUserInfoServiceImpl implements GetUserInfoService{
	UserDao udao = new UserDaoImpl();
	;
	@Override
	public JSONObject getUserInfo(int page, int pageSize, String userkey) {
		JSONObject json = new JSONObject();
		List<User> userlist = new ArrayList<User>() ;
		
		//获取total
		int count = udao.SelectUserInfoByUserkey((page-1)*pageSize, pageSize, userkey, userlist);
		json.put("total", count);
		//获取rows数组对象
		JSONArray jsonArray = new JSONArray();
		for (User user : userlist) {
			JSONObject u = new JSONObject();
			u.put("u_id", user.getU_id());
			u.put("u_name", user.getU_name());
			u.put("u_telephone",user.getU_telephone() );
			u.put("u_email", user.getU_email());
			u.put("u_registertime", user.getU_registertime());
			jsonArray.add(u);
		}
		json.put("rows", jsonArray);
		return json;
	}
}
