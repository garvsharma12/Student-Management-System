package com.draig.student_management_system.service.impl;

import com.draig.student_management_system.dto.StudentDto;
import com.draig.student_management_system.entity.Student;
import com.draig.student_management_system.mapper.StudentMapper;
import com.draig.student_management_system.repository.StudentRepository;
import com.draig.student_management_system.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

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

    @Override
    public void createStudent(StudentDto student) {
        Student studentEntity = StudentMapper.mapToStudent(student);
        studentRepository.save(studentEntity);
    }
    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = findStudent(studentId);
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public void updateStudent(Long studentId, StudentDto studentDto) {
        Student student = findStudent(studentId);
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.delete(findStudent(studentId));
    }

    private Student findStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }
}
