package com.kidzlandConfrences;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private String id; 

    private String dateTime; 
    private String studentName; 

    public Appointment(String dateTime, String studentName){
        this.dateTime = dateTime; 
        this.studentName = studentName; 
    }
}
