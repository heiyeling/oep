package com.zr.dao;

import java.util.List;

import com.zr.model.User;

public interface UserDao {
	/**
	 * 根据用户关键字分页查询用户信息,获得总行数count
	 * @param start
	 * @param pageSize
	 * @param userkey
	 * @return
	 */
	public int SelectUserInfoByUserkey(int start,int pageSize,String userkey,List<User> userlist);

}
