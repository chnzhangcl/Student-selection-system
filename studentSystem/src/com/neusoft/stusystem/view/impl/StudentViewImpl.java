package com.neusoft.stusystem.view.impl;

import java.util.Scanner;

import com.neusoft.stusystem.dao.StudentDao;
import com.neusoft.stusystem.dao.impl.StudentDaoImpl;
import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.view.StudentView;

public class StudentViewImpl implements StudentView{
	private StudentDao dao = new StudentDaoImpl();
	private Scanner sc = new Scanner(System.in);
	@Override
	public void studentInsert() {
		System.out.println("请输入学生姓名");
		String studentName = sc.nextLine();
		System.out.println("请输入学生班级");
		String studentClass = sc.nextLine();
		int num = dao.studentInsert(studentName,studentClass);
		if(num>0){
			System.out.println("学生信息注册成功");
		}else{
			System.out.println("学生信息注册失败");
		}
		
	}
	@Override
	public void studentLogin() {
		System.out.println("请输入学生ID");
		int studentID = sc.nextInt();
		Student student = dao.studentLogin(studentID);
		if(student != null){
			System.out.println(student);
		}
	}
	@Override
	public void studentCrouse() {
		System.out.println("请输入需要选课学生ID");
		int studentID = sc.nextInt();
		System.out.println("请输入需要被选课程ID");
		int courseID = sc.nextInt();
		int num = dao.studentCrouse(studentID,courseID);
		if(num > 0){
			System.out.println("课程添加成功");
		}else{
			System.out.println("课程添加失败");
		}
		
	}
	@Override
	public void showStudentCrouse() {
		System.out.println("请输入学生编号：");
		int studentID = sc.nextInt();
		Course course = dao.showStudentCrouse(studentID);
		if(course!=null){
			System.out.println("学生编号："+studentID+"学生课程："+course.getCourseName()+"课程学时："+course.getCourseHour());
		}else{
			System.out.println("没有这个学生");
		}
		
		
	}
	
	
	

}
