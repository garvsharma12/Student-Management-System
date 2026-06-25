package com.draig.student_management_system.controller;

import com.draig.student_management_system.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    void saveStudentShowsValidationErrorsForInvalidForm() throws Exception {
        mockMvc.perform(post("/students")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "not-an-email"))
                .andExpect(status().isOk())
                .andExpect(view().name("create_student"))
                .andExpect(model().attributeHasFieldErrors("student", "firstName", "lastName", "email"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("First name should not be empty")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Last name should not be empty")));
    }
}
