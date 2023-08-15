/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.school.repository.StudentRepository;
import com.example.school.model.*;

@Service 
public class StudentH2Service implements StudentRepository{

    @Autowired
    JdbcTemplate db;
    
    @Override 
    public ArrayList<Student> getStudents(){
        List<Student> studentList = db.query("select * from STUDENT ", new StudentRowMapper());
        ArrayList<Student> student = new ArrayList<>(studentList);
        return student;
    }
    @Override 
    public Student getStudentById(int studentId){
       try {
         Student student = db.queryForObject("select * from STUDENT where studentid = ?", new StudentRowMapper(), studentId);
        return student;
       }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
    }
    @Override 
    public String addStudent(ArrayList<Student> students){
        int count = 0;
        for (Student student : students){
            db.update("insert into STUDENT(studentname,gender,standard) values (?,?,?)", student.getStudentName(), student.getGender(), student.getStandard());
            count += 1;
        } 


    return "Successfully added " + count +  " students";
    }
    @Override
    public Student updateStudent(int studentId, Student student){
        if(student.getStudentName() != null){
            db.update("update STUDENT set studentname = ? where studentid = ?", student.getStudentName(), studentId);
        }
        if(student.getGender() != null){
            db.update("update STUDENT set gender = ? where studentid = ?", student.getGender(), studentId);
        }
        if(student.getStandard() != 0){
            db.update("update STUDENT set standard = ? where studentid = ?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }
    @Override 
    public void deleteStudent(int studentId){
        db.update("delete from STUDENT where studentid = ?", studentId);
    }
}