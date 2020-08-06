package com.neusoft.stusystem.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.stusystem.dao.StudentDao;
import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.util.JdbcDAO;

public class StudentDaoImpl extends JdbcDAO implements StudentDao{

	@Override
	public int studentInsert(String studentName, String studentClass) {
		String sql = "insert into student(studentName,studentClass) values(?,?)";
		PreparedStatement pstmt = null;
		int num = 0;
		
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, studentName);
			pstmt.setString(2, studentClass);
			num = pstmt.executeUpdate();
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,null);
		}
		return num;
	}

	@Override
	public Student studentLogin(int studentID) {
		String sql = "select * from student where studentID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student stu = null;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, studentID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				stu = new Student();
				stu.setStudentID(rs.getInt(1));
				stu.setStudentName(rs.getString(2));
				stu.setStudentClass(rs.getString(3));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,null);
		}
		return stu;
		
	}

	@Override
	public int studentCrouse(int studentID, int courseID) {
		String sql = "insert into stucou (studentID,courseID) values(?,?)";
		PreparedStatement pstmt = null;
		int num = 0;
		
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, studentID);
			pstmt.setInt(2, courseID);
			num = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,null);
		}
		return num;
	}

	@Override
	public Course showStudentCrouse(int studentID) {
		String sql = "select c.courseName,c.courseHour from student stu,stucou s,course c "
				+ " where stu.studentID = s.studentID and s.courseID = c.courseID and stu.studentID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course c = null;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, studentID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				c = new Course();
			
				c.setCourseName(rs.getString("courseName"));
				c.setCourseHour(rs.getInt("courseHour"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,null);
		}
		return c;

	}

}
