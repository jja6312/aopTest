package com.ssafy.debug.model.service;

import java.util.List;

import com.ssafy.debug.model.dao.StudentDao;
import com.ssafy.debug.model.dao.StudentDaoImpl;
import com.ssafy.debug.model.dto.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentDao dao;

	@Override
	public void registStudent(Student student) {
		// 암호화
		dao.InsertStudnet(student);
	}

	@Override
	public List<Student> getStudentList() {
		return dao.selectAll();
	}

	@Override
	public Student getStudent(int id) {
		return dao.selectOne(id);
	}

	@Override
	public void modifyStudent(Student student) {
		dao.updateStudent(student);
	}

	@Override
	public void removeStudent(int id) {
		dao.deleteStudent(id);
	}
}
