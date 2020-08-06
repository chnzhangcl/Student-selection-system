package com.neusoft.stusystem.view.impl;

import java.util.List;
import java.util.Scanner;


import com.neusoft.stusystem.dao.TeacherDao;
import com.neusoft.stusystem.dao.impl.TeacherDaoImpl;
import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.po.Teacher;
import com.neusoft.stusystem.view.TeacherView;

public class TeacherViewImpl implements TeacherView{
	private TeacherDao dao = new TeacherDaoImpl();
	private Scanner sc = new Scanner(System.in);
	
	//老师增加
	@Override
	public void teacherInsert() {
		System.out.println("请输入教师名称：");
		String teacherName = sc.nextLine();
		System.out.println("请输入老师的技术方向：");
		String technology = sc.nextLine();
		int num = dao.teacherInsert(teacherName, technology);
		if(num>0){
			System.out.println("老师信息加入成功");
		}else{
			System.out.println("老师信息加入失败");
		}
		
	}
	
	//老师删除
	@Override
	public void teacherDelete() {
		 teacherShow();
		System.out.println("请输入 需要删除的教师编号：");
		int teacherID = sc.nextInt();
		Teacher teacher = dao.teacherLogin(teacherID);
		System.out.println("是否确认要删除此课程?(y/n)");
		String s = sc.nextLine();
		if(s.equalsIgnoreCase("y")){
			if(teacher == null){
				int num = dao.teacherDelete(teacherID);
				if(num > 0){
					System.out.println("教师信息被删除！");
				}else{
					System.out.println("删除失败");	
				}

			}else{
				System.out.println("没有您要删除的教师");	
			}
		}
	}

	@Override
	public void teacherUpdate(int teacherID) {
		Teacher teacher = dao.teacherLogin(teacherID);
		System.out.println("您好："+teacher.getTeacherName());
		String str = null;
		System.out.println("是否修改老师名称?(y/n)");
		str = sc.next();
		if(str.equalsIgnoreCase("y")){
			System.out.println("请输入新的名称");
			String  teacherName = sc.next();
			teacher.setTeacherName(teacherName);
		}
		System.out.println("是否修改教师课程?(y/n)");
		str = sc.next();
		if(str.equalsIgnoreCase("y")){
			System.out.println("请输入新的教师课程");
			String  technology = sc.next();
			teacher.setTechnology(technology);
		}

		int num = dao.teacherUpdate(teacher);
		if(num>0){
			System.out.println("老师信息修改成功");
		}else{
			System.out.println("老师信息修改失败");
		}
		
		
	}

	@Override
	public void teacherShow() {
		
		List<Teacher> tlist = dao.teacherShow();

		
		
		for(Teacher teacher: tlist ){
			System.out.println(teacher);
		}
		
	}

	@Override
	public void teacherStudent() {
		System.out.println("请输入需要查询的课程ID：");
		int teacherID = sc.nextInt();
		Teacher teacher = dao.teacherLogin(teacherID);
		if(teacher!=null){
			List<Student> teacherStudent = dao.teacherStudent(teacherID);
			for (int i = 0; i < teacherStudent.size(); i++) {
				Student student = teacherStudent.get(i);
				System.out.println("学生ID"+student.getStudentID()+"学生姓名"+student.getStudentName()+
						"学生班级"+student.getStudentClass());
			}
		}else{
			System.out.println("输入数据有误");
		}
	}
	
	

	
}
