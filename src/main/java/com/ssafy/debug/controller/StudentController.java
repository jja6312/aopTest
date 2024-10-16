package com.ssafy.debug.controller;

import java.io.IOException;

import com.ssafy.debug.model.dto.Student;
import com.ssafy.debug.model.service.StudentService;
import com.ssafy.debug.model.service.StudentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@WebServlet("/student")
@RequiredArgsConstructor
public class StudentController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private final String prefix = "/WEB-INF/student/";
	private final StudentService service;
//	private StudentService service = StudentServiceImpl.getInstance();

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "registform":
			doRegistForm(request, response);
			break;
		case "regist":
			doRegist(request, response);
			break;
		case "list":
			doList(request, response);
			break;
		case "detail":
			doDetail(request, response);
			break;
		case "delete":
			doRemove(request, response);
			break;
		case "updateform":
			doUpdateForm(request, response);
			break;
		case "update":
			doUpdate(request, response);
			break;
		default:
			response.sendRedirect(request.getContextPath());
			break;
		}
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = service.getStudent(id);
		
		student.setName(request.getParameter("name"));
		student.setDepartment(request.getParameter("department"));
		student.setEmail(request.getParameter("email"));
		student.setPhoneNumber(request.getParameter("phoneNumber"));
		
		
		service.modifyStudent(student);
		
		response.sendRedirect("student?action=detail&id="+id);
		
	}

	private void doUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("student", service.getStudent(id));
		request.getRequestDispatcher(prefix+"updateform.jsp").forward(request, response);
	}

	private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		service.removeStudent(id);
		response.sendRedirect("student?action=list");
	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		request.setAttribute("student", service.getStudent(id));
		request.getRequestDispatcher(prefix+"detail.jsp").forward(request, response);
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", service.getStudentList());
		request.getRequestDispatcher(prefix+"list.jsp").forward(request, response);;
		
	}

	private void doRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
        String department = request.getParameter("department");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        Student student = new Student(name, department, email, phoneNumber);
        
        service.registStudent(student);

        response.sendRedirect("student?action=list");
		
	}

	private void doRegistForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(prefix+"registform.jsp").forward(request, response);;
	}
}
