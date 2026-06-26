package com.draig.student_management_system.service;

import com.draig.student_management_system.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    void createStudent(StudentDto student);

    StudentDto getStudentById(Long studentId);

    void updateStudent(Long studentId, StudentDto student);

    void deleteStudent(Long studentId);
}
