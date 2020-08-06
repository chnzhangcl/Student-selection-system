package com.neusoft.stusystem;

import java.util.Scanner;


import com.neusoft.stusystem.po.Teacher;
import com.neusoft.stusystem.view.CourseView;
import com.neusoft.stusystem.view.StudentView;
import com.neusoft.stusystem.view.TeacherView;
import com.neusoft.stusystem.view.impl.CourseViewImpl;
import com.neusoft.stusystem.view.impl.StudentViewImpl;
import com.neusoft.stusystem.view.impl.TeacherViewImpl;

public class AdminIn {
	private  Scanner sc = new Scanner(System.in);
	private TeacherView tv = new TeacherViewImpl();
	private StudentView sv = new StudentViewImpl();
	private CourseView cv = new CourseViewImpl();
	
public void work(){
		System.out.println("欢迎登陆学校信息系统");
		int num = 0;
		while(num != 3){
			System.out.println("---------1.课程管理  2.教师管理  3.退出系统---------");
			num = sc.nextInt();
			switch (num) {
				case 1:
					course();
					break;
				case 2:
					teacher();		
					break;
				case 3:
					System.out.println("欢迎再来啊");
					break;
				default:
					System.out.println("输入有误");
					break;
			}
		}
	}
	
	
	
//课程管理模块：对课程的添加功能，删除功能，修改功能，查询（全查）功能，查看某一课程下已经选的学生信息功能


	private void course() {
	
		int num = 0;
		while(num != 6){
			System.out.println("1.添加课程，2.删除课程，3.修改课程，4.查询课程,5.查询课程所选的学生,6.退出功能");
			num = sc.nextInt();
			switch(num){
				case 1:
					cv.courseInsert();
					break;
				case 2:
					cv.courseDelete();
					break;
				case 3:
					System.out.println("请输入你需要修改课程信息的老师编号：");
					int courseID = sc.nextInt();
					cv.courseUpdate(courseID);
					break;
				case 4:
					cv.courseShow();
					break;
				case 5:
					cv.courseStudent();
					break;
				case 6:
					System.out.println("欢迎下次再来");
					break;
				default:
					System.out.println("输入有误");
					break;
			}
		}
		
  }




	private void teacher() {
		int num = 0;
		while(num != 6){
		System.out.println("1.添加老师信息，2.删除老师信息，3.修改老师信息，4.查询老师信息，5查询老师所教的学生,6.退出功能");
			num = sc.nextInt();
			switch(num){
				case 1:
					tv.teacherInsert();
					break;
				case 2:
					tv.teacherDelete();
					break;
				case 3:
					System.out.println("请输入你需要修改老师信息的老师编号：");
					int teacherID = sc.nextInt();
					tv.teacherUpdate(teacherID);
					break;
				case 4:
					tv.teacherShow();
					break;
				case 5:
					tv.teacherStudent();
					break;
				case 6:
					System.out.println("欢迎下次再来");
					break;
				default:
					System.out.println("输入有误");
					break;
			}
		}
	}
		

	
	
	public static void main(String[] args) {
	
		new AdminIn().work();
		
	}
}
