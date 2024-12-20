package com.example.controller;

import com.example.model.Student;
import java.util.*;

import com.example.repository.StudentRepository;
//import lombok.var;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

//    @Autowired  // not required as i am using final keyword and @RequiredArgsConstructor annotation
    private final StudentRepository studentRepository;

    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getSingleStudent(@PathVariable long id) {
        return studentRepository.findById(id);
    }

    @PostMapping("/student")
    public String createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return "Student created successfully";
    }

    @PutMapping("/student/{id}")
    public String updateStudent(@RequestBody Student student, @PathVariable long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));;;
        BeanUtils.copyProperties(student, existingStudent, "id");
        studentRepository.save(existingStudent);  //
        return "Student updated in database";
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
        return "Student removed from database";
    }



}
