package com.Version.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	 //1.数据库地址  (根据不同的数据标准是不一样)
	  private  final  static String DBURL = "jdbc:mysql://localhost:3306/chinasofti";
	  //2.设置用户和密码
	  private  final  static String  USERNAME = "root";
	  private  final  static String  PASSWORD = "1234";
	  //3.设置驱动名称 (根据不同的数据标准是不一样)
	  private  final  static String  DBDRIVER = "com.mysql.jdbc.Driver";
	  /**
	   * 获取数据库连接
	   * @return  返回数据库连接
	   */
	  public  static  Connection  getConnection(){
		  Connection  con = null;
		  try {
			Class.forName(DBDRIVER);
			con  =  DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return  con;
			
	  }
	  /**
	   * 关闭连接
	   * @param st
	   * @param con
	   * @throws SQLException
	   */
	  public static void  closeJDBC(Statement st,Connection  con) throws SQLException{
		    if(st!=null){
		    	st.close();
		    }
		    if(con!=null){
		    	con.close();
		    }
		   
	  }
}
