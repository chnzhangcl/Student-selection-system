package com.neusoft.stusystem.dao.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.stusystem.dao.TeacherDao;
import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.po.Teacher;
import com.neusoft.stusystem.util.JdbcDAO;

public class TeacherDaoImpl extends JdbcDAO implements TeacherDao{

	@Override
	public int teacherInsert(String teacherName, String technology) {
		String sql = "insert into teacher (teacherName,technology) values(?,?)";
		PreparedStatement pstmt = null;
		int num = 0;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1, teacherName);
			pstmt.setString(2, technology);
			num= pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(null,pstmt,null);
		}
		return num;
	}

	@Override
	public int teacherDelete(int teacherID) {
		String sql1 = "delete from stucou where courseID in (select courseID from course where teacherID = ?)";
		String sql2 = "delete from course where teacherID = ?";
		String sql3 = "delete from teacher where teacherID = ? ";
		PreparedStatement pstmt = null;
		int num = 0;
		try {
			getConn().setAutoCommit(false);
			pstmt = getConn().prepareStatement(sql1);
			pstmt.setInt(1, teacherID);
			num = pstmt.executeUpdate();
			System.out.println(num);
			pstmt = getConn().prepareStatement(sql2);
			pstmt.setInt(1, teacherID);
			num = pstmt.executeUpdate();
			System.out.println(num);

			pstmt = getConn().prepareStatement(sql3);
			pstmt.setInt(1, teacherID);
			num = pstmt.executeUpdate();
			System.out.println(num);

			getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				getConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			close(null, pstmt, null);
		}
		return num;
	}

	@Override
	public int teacherUpdate(Teacher teacher) {
		String sql = "update teacher set teacherName= ? , technology = ? where teacherID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setString(1,teacher.getTeacherName());
			pstmt.setString(2, teacher.getTechnology());
			pstmt.setInt(3, teacher.getTeacherID());
			num = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("Sqlyichang");
		}finally{
			close(null,pstmt,rs);
		}
		return num;
	}

	@Override
	public Teacher teacherLogin(int teacherID) {
		String sql = "select TeacherID,teacherName,technology FROM teacher WHERE teacherID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		try {
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, teacherID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				teacher = new Teacher();
				teacher.setTeacherName(rs.getString("teacherName"));
				teacher.setTechnology(rs.getString("technology"));
				teacher.setTeacherID(rs.getInt("teacherID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(null, pstmt, rs);
		}
		return teacher;
	}

	@Override
	public List<Teacher> teacherShow(){
		
		String sql = "select * from teacher";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		List<Teacher> tlist = new ArrayList<Teacher>();
		try{
			pstmt = getConn().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				teacher = new Teacher();
				teacher.setTeacherID(rs.getInt(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setTechnology(rs.getString(3));
				tlist.add(teacher);
			}
			
		}catch(SQLException e){
			System.out.println("sqlyic");
		}finally{
			close(null,pstmt,rs);
		}
		return tlist;
		
	}

	@Override
	public List<Student> teacherStudent(int teacherID) {
		String sql = "select stu.Studentid ,stu.Studentname,stu.StudentClass from student stu,stucou sc,course c,teacher t "
		+ "where t.teacherId = c.teacherId and c.courseId = sc.courseId and sc.studentid = stu.studentid and t.teacherid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> slist = new ArrayList<Student>();
		Student student = null;
		try{
			pstmt = getConn().prepareStatement(sql);
			pstmt.setInt(1, teacherID);
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
