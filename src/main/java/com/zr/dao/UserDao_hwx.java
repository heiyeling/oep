package com.zr.dao;


/**
*@author VerSion
*@time 2017年9月22日上午9:51:52
*/
public interface UserDao_hwx {

	public boolean UserRegister(String u_name,String u_password,String u_email,String u_telephone);//注册到数据库
	
	public boolean UserExist(String u_uname);//查看库里是否存在该用户
}
