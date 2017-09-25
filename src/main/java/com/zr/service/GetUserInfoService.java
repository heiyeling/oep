package com.zr.service;

import java.util.List;

import com.zr.model.User;

import net.sf.json.JSONObject;

public interface GetUserInfoService {
	/**
	 * 获得用户信息
	 * @param start
	 * @param pageSize
	 * @param userkey
	 * @return  用户信息的JSON对象
	 */
	public JSONObject getUserInfo(int page, int pageSize, String userkey);
	
}
