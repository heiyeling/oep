package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zr.model.User;
import com.zr.utils.JDBCUtil;

public class EditUserDaoImpl {
	public void editUserByUid(User user){
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE `user`");
		sql.append("SET u_name = ? , u_email = ? , u_telephone=? , u_registertime = ? ");
		sql.append("WHERE u_id =? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setString(1, user.getU_name());
				ps.setString(2, user.getU_telephone());
				//ps.setString(3, x);
				ps.setInt(5, user.getU_id());
				
				ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
