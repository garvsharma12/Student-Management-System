package com.draig.student_management_system.controller;

import com.draig.student_management_system.dto.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.draig.student_management_system.service.StudentService;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //handler methods to handle list students request
    @GetMapping("/students")
    public String listStudents(Model model){
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    //add new student
    @GetMapping("/students/new")
    public String newStudent(Model model){
        //student model object to store student form data
        //StudentDto studentDto = new StudentDto();
        model.addAttribute("student", new StudentDto());
        return "create_student";
    }
}
