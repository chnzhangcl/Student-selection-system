package com.neusoft.stusystem.dao;

import com.neusoft.stusystem.po.Course;
import com.neusoft.stusystem.po.Student;

public interface StudentDao {

	int studentInsert(String studentName, String studentClass);

	Student studentLogin(int studentID);

	int studentCrouse(int studentID, int courseID);

	Course showStudentCrouse(int studentID);

}
