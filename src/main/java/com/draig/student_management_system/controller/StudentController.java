package com.draig.student_management_system.controller;

import com.draig.student_management_system.dto.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.draig.student_management_system.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //handler methods to handle list students request
    @GetMapping("/students")
    public String listStudents(Model model) {
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

    // handler method to handle save student form submission request
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student, BindingResult bindingResult,
        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            return "create_student";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    // handler method to handle edit student form submission request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentDto student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "edit_student";  
    }
}
