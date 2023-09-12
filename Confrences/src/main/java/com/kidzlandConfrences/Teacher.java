package com.kidzlandConfrences;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    private String id; 

    private String name;
    private String teacherId; 
    private List<String> available; 

    @DocumentReference
    private List<Appointment> appointments; 
    
    
}
