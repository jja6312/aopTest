package com.ssafy.debug.model.dto;

public class Student {

	private int studentId; // 학생 ID (고유 번호)
	private String name; // 학생 이름
	private String department; // 학과
	private String email; // 이메일 주소
	private String phoneNumber;// 전화번호

	// 기본 생성자
	public Student() {
	}

	public Student(String name, String department, String email, String phoneNumber) {
		this.name = name;
		this.department = department;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	// 모든 필드를 포함하는 생성자
	public Student(int studentId, String name, String department, String email, String phoneNumber) {
		this.studentId = studentId;
		this.name = name;
		this.department = department;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	// Getters and Setters
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", department=" + department + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}
}