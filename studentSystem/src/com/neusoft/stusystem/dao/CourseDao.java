package com.neusoft.stusystem.dao;

import java.util.List;

import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;

public interface CourseDao {

	int courseInsert(String courseName, int courseHour,int teacherID);

	int courseDelete(int courseID);

	Course CourseLogin(int courseID);

	int courseUpdate(Course course);

	List<Course> courseShow();

	List<Student> courseStudent(int courseID);

	
}
