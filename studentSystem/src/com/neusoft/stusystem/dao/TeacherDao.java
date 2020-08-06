package com.neusoft.stusystem.dao;

import java.util.List;

import com.neusoft.stusystem.po.Student;
import com.neusoft.stusystem.po.Teacher;

public interface TeacherDao {
	public int teacherInsert(String teacherName,String technology);
	public int teacherDelete(int teacherID);
	public int teacherUpdate(Teacher teacher);
	public Teacher teacherLogin(int teacherID);
	public List<Teacher> teacherShow();
	public List<Student> teacherStudent(int teacherID);

}
