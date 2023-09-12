package com.kidzlandConfrences;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService; 

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Map<String, String> payload){

        String date =payload.get("date");
        String teacher =payload.get("teacher");
        String student = payload.get("student");

        Appointment appointment = appointmentService.createAppointment(date, teacher, student);
        if(appointment==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Appointment slot is not available.");
        }
        return new ResponseEntity<Appointment>(appointment, HttpStatus.CREATED );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable String id){
        if(!appointmentService.deleteAppointment(id)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error removing Appointment"); 
        }
        return new ResponseEntity<String>("removed", HttpStatus.OK );
    }




    
}
