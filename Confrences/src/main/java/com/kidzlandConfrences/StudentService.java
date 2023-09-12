package com.kidzlandConfrences;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository; 

    public Optional<Student> getStudentByName (String name){
        return studentRepository.findStudentByName(name); 
    }


    public Optional<Student> getStudentById (String id){
        return studentRepository.findStudentById(id); 
    }
    
}
