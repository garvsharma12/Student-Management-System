package com.draig.student_management_system.service.impl;

import com.draig.student_management_system.dto.StudentDto;
import com.draig.student_management_system.repository.StudentRepository;
import com.draig.student_management_system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<StudentDto> getAllStudents() {
        return List.of();
    }
}
