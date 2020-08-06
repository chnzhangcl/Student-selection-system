package com.neusoft.stusystem.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.stusystem.dao.CourseDao;
import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.po.Teacher;
import com.neusoft.stusystem.util.JdbcDAO;

public class CourseDaoImpl extends JdbcDAO implements CourseDao{

	@Override
	public int courseInsert(String courseName, int courseHour,int teacherID) {
		String sql = "insert into course (courseName,courseHour,teacherID) values(?,?,?)";
		PreparedStatement pstmt = null;
		int num = 0;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, courseName);
			pstmt.setInt(2, courseHour);
			pstmt.setInt(3, teacherID);
			num= pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,null);
		}
		return num;
	}

	@Override
	public int courseDelete(int courseID) {
		String sql1 = "delete from stucou where courseID = ?";//先删子表后删主表
		String sql2 = "delete from course where courseID = ?";
		PreparedStatement pstmt = null;
		int num = 0;
		try {
			getConn().setAutoCommit(false);
			pstmt = getConn().prepareStatement(sql1);
			pstmt.setInt(1, courseID);
			num = pstmt.executeUpdate();
			
			pstmt = getConn().prepareStatement(sql2);
			pstmt.setInt(1, courseID);
			num = pstmt.executeUpdate();
			System.out.println(num);
			getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				getConn().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			close(null, pstmt, null);
		}
		return num;
	}

	@Override
	public Course CourseLogin(int courseID) {
		String sql = "select * FROM course WHERE courseID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, courseID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				course = new Course();
				course.setCourseName(rs.getString("courseName"));
				course.setCourseHour(rs.getInt("courseHour"));
				course.setTeacherID(rs.getInt("teacherID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return course;
	}

	@Override
	public int courseUpdate(Course course) {
		String sql = "update course set courseName= ? , courseHour = ? where courseID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,course.getCourseName());
			pstmt.setInt(2, course.getCourseHour());
			pstmt.setInt(3, course.getCourseID());
			num = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,rs);
		}
		return num;
	}


	@Override
	public List<Course> courseShow() {
		String sql = "Select * from course";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> clist = new ArrayList<Course>();
		Course course = null;
		try{
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				course = new Course();
				course.setCourseID(rs.getInt("courseID"));
				course.setCourseName(rs.getString("courseName"));
				course.setCourseHour(rs.getInt("courseHour"));
				course.setTeacherID(rs.getInt("teacherID"));
				clist.add(course);	
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,rs);
		}
		return clist;
	}

	//查看某一课程下已经选的学生信息功能
	@Override
	public List<Student> courseStudent(int courseID) {
		String sql = "select stu.studentID ,stu.studentName,stu.studentClass from student stu,stucou s,course c where stu.studentID = s.studentID and s.courseID = c.courseID"
				+ " and c.courseID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> slist = new ArrayList<Student>();
		Student student = null;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, courseID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				student = new Student();
				student.setStudentID(rs.getInt("studentID"));
				student.setStudentName(rs.getString("studentName"));
				student.setStudentClass(rs.getString("studentClass"));
				slist.add(student);	
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,rs);
		}
		return slist;
		
	}
	
	
	
	
	

}
