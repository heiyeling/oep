package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zr.dao.UserDao;
import com.zr.model.User;
import com.zr.utils.JDBCUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public int SelectUserInfoByUserkey(int start, int pageSize, String userkey, List<User> userlist) {
		int count = 0;//查询的总行数
		userkey = '%'+userkey+'%';
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(u_id)  ");
		sql.append("FROM `user` ");
		sql.append("WHERE   `user`.u_name LIKE ? OR `user`.u_telephone LIKE ? OR `user`.u_email LIKE ? OR `user`.u_registertime LIKE ? ");
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, userkey);
			pst.setString(2, userkey);
			pst.setString(3, userkey);
			pst.setString(4, userkey);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				 count = rs.getInt("COUNT(u_id)");
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer sql1 = new StringBuffer("");
		sql1.append("select `user`.u_id,`user`.u_name,`user`.u_telephone,`user`.u_email,`user`.u_registertime  ");
		sql1.append("FROM `user` ");
		sql1.append("WHERE   `user`.u_name LIKE ? OR `user`.u_telephone LIKE ? OR `user`.u_email LIKE ? OR `user`.u_registertime LIKE ? ");
		sql1.append("LIMIT ?, ?");
		try {
			PreparedStatement pst1 = con.prepareStatement(sql1.toString());
			pst1.setString(1, userkey);
			pst1.setString(2, userkey);
			pst1.setString(3, userkey);
			pst1.setString(4, userkey);
			pst1.setInt(5, start);
			pst1.setInt(6, pageSize);
			ResultSet rs1 = pst1.executeQuery();
			while(rs1.next()){
				User user = new User();
				user.setU_id(rs1.getInt("u_id"));
				user.setU_name(rs1.getString("u_name"));
				user.setU_telephone(rs1.getString("u_telephone"));
				user.setU_email(rs1.getString("u_email"));
				user.setU_registertime(rs1.getString("u_registertime"));
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
			
}
