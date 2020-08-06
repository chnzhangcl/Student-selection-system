package com.neusoft.stusystem.po;

public class Stucou {
	
	private Integer ID;
	private Integer studentID;
	private Integer courseID;
	public Stucou() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stucou(Integer iD, Integer studentID, Integer courseID) {
		super();
		ID = iD;
		this.studentID = studentID;
		this.courseID = courseID;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getStudentID() {
		return studentID;
	}
	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	@Override
	public String toString() {
		return "Stucou [ID=" + ID + ",\t studentID=" + studentID + ", \t courseID=" + courseID + "]";
	}
	
	
	

}
