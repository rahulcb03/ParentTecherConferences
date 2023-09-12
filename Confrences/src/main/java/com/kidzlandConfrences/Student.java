package com.kidzlandConfrences;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String id; 

    private String name;
    private String parent;
    private String email;
    private String dateTime;
  

    @DocumentReference (collection = "Teachers")
    private Teacher teacher; 
    
}