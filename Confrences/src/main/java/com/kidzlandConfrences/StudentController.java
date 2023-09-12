package com.kidzlandConfrences;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService; 

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Student>> getStudentByName(@PathVariable String name){
        Optional<Student> student = studentService.getStudentByName(name); 

        return new ResponseEntity<Optional<Student> >(student, HttpStatus.OK) ; 
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable String id){
        Optional<Student> student = studentService.getStudentById(id); 

        return new ResponseEntity<Optional<Student> >(student, HttpStatus.OK) ; 
    }
    
}
