package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.zr.dao.RemoveUserDao;
import com.zr.utils.JDBCUtil;

public class RemoveUserDaoImpl implements RemoveUserDao{

	public List RemoveUserByUid(List ids) {
		List ls = new ArrayList();
		//先删除用户的外键关联信息
		StringBuffer sql = new StringBuffer("");
		sql.append("DELETE from scoreofuser WHERE u_id = ?");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			for (Object i : ids) {
				String i1= (String) i;
				int i2 = Integer.parseInt(i1);
				ps.setInt(1, i2);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//删除用户信息
		
		StringBuffer sql1 = new StringBuffer("");
		sql1.append("DELETE from `user` WHERE u_id = ? ");
		Connection con1 = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = con1.prepareStatement(sql1.toString());
			for (Object i : ids) {
				String i1= (String) i;
				int i2 = Integer.parseInt(i1);
				ps.setInt(1, i2);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
}
