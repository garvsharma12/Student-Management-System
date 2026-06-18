package com.draig.student_management_system.service.impl;

import com.draig.student_management_system.dto.StudentDto;
import com.draig.student_management_system.entity.Student;
import com.draig.student_management_system.mapper.StudentMapper;
import com.draig.student_management_system.repository.StudentRepository;
import com.draig.student_management_system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
        return studentDtos;
    }
}
