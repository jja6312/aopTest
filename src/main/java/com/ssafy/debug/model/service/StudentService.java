package com.ssafy.debug.model.service;

import java.util.List;

import com.ssafy.debug.model.dto.Student;

public interface StudentService {
	// 학생등록
	public void registStudent(Student Student);
	// 학생 전체 목록 조회
	public List<Student> getStudentList();
	// 학생 개별 정보 조회
	public Student getStudent(int id);
	// 학생 수정
	public void modifyStudent(Student student);
	// 학생 삭제
	public void removeStudent(int id);
}
