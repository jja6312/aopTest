package com.ssafy.debug.controller;

import com.ssafy.debug.model.dto.Student;
import com.ssafy.debug.model.service.StudentService;
import com.ssafy.debug.model.service.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("file:C:\\Temp\\applicationContext.xml");
        StudentService studentService = context.getBean("studentServiceImpl",StudentService.class);


        Student student = new Student("c","c","bdbaa@c","3123");
        studentService.registStudent(student);

        List<Student> list = studentService.getStudentList();
        System.out.println(list);

    }
}
