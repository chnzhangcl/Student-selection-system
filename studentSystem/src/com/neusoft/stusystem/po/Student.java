package com.neusoft.stusystem.po;

public class Student {

	private Integer studentID;
	private String studentName;
	private String studentClass;
	public Student() {
		super();
		
	}
	public Student(Integer studentID, String studentName, String studentClass) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.studentClass = studentClass;
	}
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	@Override
	public String toString() {
		return "学生ID:" + studentID + ",\t 学生名字:" + studentName + ",\t 学生班级：" + studentClass
				+ "]";
	}
	
	
	
}
