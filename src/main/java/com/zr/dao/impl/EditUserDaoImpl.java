package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zr.model.User;
import com.zr.utils.JDBCUtil;

public class EditUserDaoImpl {
	public void editUserByUid(User user){
		System.out.println(1111);
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE `user`");
		sql.append("SET u_name = ? ,u_telephone=? , u_email = ? ,  u_registertime = ? ");
		sql.append("WHERE u_id =? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setString(1, user.getU_name());
				ps.setString(2, user.getU_telephone());
				ps.setString(3, user.getU_email());
				ps.setString(4, user.getU_registertime());
				ps.setInt(5, user.getU_id());
				ps.executeUpdate();
			System.out.println(222);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
