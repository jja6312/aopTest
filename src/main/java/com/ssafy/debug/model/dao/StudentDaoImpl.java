package com.ssafy.debug.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.debug.model.dto.Student;
import com.ssafy.debug.util.DBUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements StudentDao {
	public DBUtil util = DBUtil.getInstance();
//	private static StudentDao dao = new StudentDaoImpl();
//
//	private StudentDaoImpl() {
//	}
//
//	public static StudentDao getInstance() {
//		return dao;
//	}

	@Override
	public void InsertStudnet(Student student) {
		String sql = "INSERT INTO Student (name, department, email, phone_number) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = util.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getDepartment());
			pstmt.setString(3, student.getEmail());
			pstmt.setString(4, student.getPhoneNumber());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}

	}

	@Override
	public List<Student> selectAll() {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM Student";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setName(rs.getString("name"));
				student.setDepartment(rs.getString("department"));
				student.setEmail(rs.getString("email"));
				student.setPhoneNumber(rs.getString("phone_number"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return students;
	}

	@Override
	public Student selectOne(int id) {
		Student student = null;
		String sql = "SELECT * FROM Student WHERE student_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setName(rs.getString("name"));
				student.setDepartment(rs.getString("department"));
				student.setEmail(rs.getString("email"));
				student.setPhoneNumber(rs.getString("phone_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return student;
	}

	@Override
	public void updateStudent(Student student) {
		String sql = "UPDATE Student SET name = ?, department = ?, email = ?, phone_number = ? WHERE student_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getDepartment());
			pstmt.setString(3, student.getEmail());
			pstmt.setString(4, student.getPhoneNumber());
			pstmt.setInt(5, student.getStudentId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
	}

	@Override
	public void deleteStudent(int id) {
		String sql = "DELETE FROM Student WHERE student_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
	}
}
