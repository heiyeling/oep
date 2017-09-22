package com.zr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zr.dao.ExamDao;
import com.zr.model.Exam;
import com.zr.utils.JDBCUtil;

public class ExamDaoImpl implements ExamDao {
	Connection con;

	public ExamDaoImpl() {
		this.con = JDBCUtil.getConnection();
	}

	@Override
	public int getExamByKey(String key, int start, int pageSize, List<Exam> examList) {
		int count = 0;// 该页考试总数
		StringBuffer sql = new StringBuffer();
		// 信息sql部分
		String selectInfo = "SELECT e_id,e_name,e_starttime,e_endtime,e_state,e_total ";
		// 总数sql部分
		String selectCount = "SELECT COUNT(e_id) AS count ";
		// 条件和分页sql部分
		String whereSql = "FROM exam WHERE e_name LIKE ? OR e_state LIKE ? ";
		String limit = "LIMIT " + start + "," + pageSize;
		// 关键字处理
		key = '%' + key + '%';
		try {
			sql.append(selectCount).append(whereSql);
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 1;
			ps.setString(i++, key);
			ps.setString(i++, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");// 总数
			}
			sql = new StringBuffer();
			sql.append(selectInfo).append(whereSql).append(limit);
			ps = con.prepareStatement(sql.toString());
			i = 1;
			ps.setString(i++, key);
			ps.setString(i++, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				examList.add(row2entity(rs));// exam实体

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 记录转化为实体对象
	 * 
	 * @param rs
	 *            一条记录
	 * @return 实体对象
	 */
	private static Exam row2entity(ResultSet rs) {
		Exam exam = new Exam();
		try {
			exam.setE_id(rs.getInt("e_id"));
			exam.setE_name(rs.getString("e_name"));
			exam.setE_starttime(rs.getString("e_starttime"));
			exam.setE_endtime(rs.getString("e_endtime"));
			exam.setE_state(rs.getString("e_state"));
			exam.setE_total(rs.getInt("e_total"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exam;
	}

	@Override
	public boolean deleteByIds(int[] examIds) {
		// 记录结果
		boolean result = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM exam WHERE e_id IN (");
		//组装sql语句
		for (int i = 0; i < examIds.length; i++) {
			sql.append(examIds[i]);
			if (i == examIds.length - 1) {
				sql.append(")");
			} else {
				sql.append(",");
			}
		}
		// 信息sql部分
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			if(examIds.length != ps.executeUpdate()){
				throw new SQLException();//删除过程异常
			}
			else{
				result = true;//删除成功
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
