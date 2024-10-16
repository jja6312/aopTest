package com.ssafy.debug.model.dao;

import java.util.List;

import com.ssafy.debug.model.dto.Student;

public interface StudentDao {
	// 학생등록
	public void InsertStudnet(Student student);

	// 학생 전체 목록 조회
	public List<Student> selectAll();

	// 학생 개별 정보 조회
	public Student selectOne(int id);

	// 학생 수정
	public void updateStudent(Student student);

	// 학생 삭제
	public void deleteStudent(int id);
}
