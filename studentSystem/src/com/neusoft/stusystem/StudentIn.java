package com.neusoft.stusystem;

import java.util.Scanner;

import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.view.CourseView;
import com.neusoft.stusystem.view.StudentView;
import com.neusoft.stusystem.view.TeacherView;
import com.neusoft.stusystem.view.impl.CourseViewImpl;
import com.neusoft.stusystem.view.impl.StudentViewImpl;
import com.neusoft.stusystem.view.impl.TeacherViewImpl;

public class StudentIn {
	private  Scanner sc = new Scanner(System.in);
	private StudentView sv = new StudentViewImpl();
	
	public void work(){
		int num = 0;
		while(num!=5){
		System.out.println("1.学生注册，2.学生登录，3.学生选课，4.查询自己已经选的课程,5.退出功能");
			num = sc.nextInt();
			switch(num){
			case 1:
				sv.studentInsert();
				break;
			case 2:
				sv.studentLogin();	
				break;
			case 3:
				sv.studentCrouse();
				break;
			case 4:
				sv.showStudentCrouse();
				break;
			case 5:
				System.out.println("欢迎下次再来");
				break;
			default:
				System.out.println("输入有误");
				break;
			}
		}
		
		
	}
	
	public static void main(String[] args) {
	 new StudentIn().work();
	}
}
