/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
 package com.example.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

@RestController
public class StudentController{

    @Autowired
    StudentH2Service studentH2Service;

    @GetMapping("/students")
    public ArrayList<Student> getStudents(){
        return studentH2Service.getStudents();
    }
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentH2Service.getStudentById(studentId);
    }
    @PostMapping("/students/bulk")
        public String addStudent(ArrayList<Student> students){
            return studentH2Service.addStudent(students);
    }
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student){
        return studentH2Service.updateStudent(studentId, student);
    }
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentH2Service.deleteStudent(studentId);
    }
}

