package com.neusoft.stusystem.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.stusystem.dao.CourseDao;
import com.neusoft.stusystem.dao.impl.CourseDaoImpl;
import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.po.Teacher;
import com.neusoft.stusystem.view.CourseView;

public class CourseViewImpl implements CourseView {

	private CourseDao dao = new CourseDaoImpl();
	private Scanner sc = new Scanner(System.in);
	@Override
	public void courseInsert() {
		System.out.println("请输入课程名称：");
		String courseName = sc.nextLine();
		System.out.println("请输入课程小时：");
		int courseHour = sc.nextInt();
		System.out.println("请输入关联老师外键：");
		int teacherID = sc.nextInt();
		int num = dao.courseInsert(courseName, courseHour,teacherID);
		if(num>0){
			System.out.println("课程信息加入成功");
		}else{
			System.out.println("老课程信息加入失败");
		}
		
	}
	@Override
	public void courseDelete() {
		courseShow();
		System.out.println("请输入 需要删除的课程编号：");
		int courseID = sc.nextInt();
				int num = dao.courseDelete(courseID);
				if(num > 0){
					System.out.println("课程信息被删除！");
				}else{
					System.out.println("删除失败");	
				}
	}
	@Override
	public void courseUpdate(int courseID) {
		courseShow();
		
		Course course = dao.CourseLogin(courseID);
		System.out.println("您好："+course.getCourseName());
		String str = null;
		course.setCourseID(courseID);
		System.out.println("是否修改课程名称?(y/n)");
		str = sc.next();
		if(str.equalsIgnoreCase("y")){
			System.out.println("请输入新的名称");
			String  courseName = sc.next();
			course.setCourseName(courseName);
		}
		System.out.println("是否修改課程小时?(y/n)");
		str = sc.next();
		if(str.equalsIgnoreCase("y")){
			System.out.println("请输入新的课程小时");
			int courseHour  = sc.nextInt();
			course.setCourseHour(courseHour);
			
		}
		int num = dao.courseUpdate(course);
		if(num>0){
			System.out.println("课程信息修改成功");
		}else{
			System.out.println("课程信息修改失败");
		}
		
	}
	@Override
	public void courseShow() {
		List<Course> clist = dao.courseShow();
		System.out.println(clist.size());
		for(Course course:clist){
			System.out.println(course);
		}
	}
	@Override
	public void courseStudent() {
		//查看某一课程下已经选的学生信息功能
		System.out.println("请输入需要查询的课程ID：");
		int courseID = sc.nextInt();
		Course course = dao.CourseLogin(courseID);
		if(course!=null){
			List<Student> courseStudent = dao.courseStudent(courseID);
			for (int i = 0; i < courseStudent.size(); i++) {
				Student student = courseStudent.get(i);
				System.out.println("学生ID"+student.getStudentID()+"学生姓名"+student.getStudentName()+
						"学生班级"+student.getStudentClass());
			}
		}else{
			System.out.println("输入数据有误");
		}
		
	}
	
}
