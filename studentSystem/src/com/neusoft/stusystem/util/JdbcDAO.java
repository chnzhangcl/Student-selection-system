package com.neusoft.stusystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class JdbcDAO {
	private static Connection conn = null;
	public final static String Driver = "com.mysql.jdbc.Driver";
	public final static String Url = "jdbc:mysql:///school";
	public final static String Username = "root";
	public final static String Password = "root";
	
	static{//
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConn(){
		return conn;
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rs){
	
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
