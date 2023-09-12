package com.kidzlandConfrences;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository; 

    @Autowired
    private StudentRepository studentRepository; 

    @Autowired
    private TeacherRepository teacherRepository; 

    @Autowired
    MongoTemplate mongoTemplate;

    public Appointment createAppointment(String dateTime, String teacherId, String studentId ){
        Teacher teacher = teacherRepository.findTeacherById(teacherId).get();

        if (teacher.getAppointments().stream().anyMatch(appt -> appt.getDateTime().equals(dateTime))) return null; 
        if (!teacher.getAvailable().stream().anyMatch(time -> time.equals(dateTime))) return null; 

        Appointment appointment = appointmentRepository.insert(new Appointment(dateTime, studentRepository.findStudentById(studentId).get().getName()));

        mongoTemplate.update(Teacher.class)  // Missing collection name
        .matching(Criteria.where("id").is(teacherId))
        .apply(new Update().push("appointments").value(appointment))
        .first();
    
        mongoTemplate.update(Student.class)  // Missing collection name
            .matching(Criteria.where("id").is(studentId))
            .apply(new Update().set("dateTime", dateTime))
            .first();
        return appointment; 
    }

    public boolean deleteAppointment(String id) {

        Student student = studentRepository.findStudentById(id).get(); 
        String dateTime = student.getDateTime(); 

        Teacher teacher = student.getTeacher(); 
        List<Appointment> appointments = teacher.getAppointments();

        Appointment app = null; 
      

        for(int i=0; i<appointments.size(); i++){
            if(appointments.get(i).getDateTime().equals( dateTime )){
                app = appointments.get(i);
                 
            }
        }

        if(app==null) return false; 

        appointmentRepository.deleteById(new ObjectId(app.getId()));

        mongoTemplate.update(Student.class)  // Missing collection name
        .matching(Criteria.where("id").is(id))
        .apply(new Update().set("dateTime", ""))
        .first();

        mongoTemplate.update(Teacher.class)  // Missing collection name
        .matching(Criteria.where("id").is(teacher.getId()))
        .apply(new Update().pull("appointments", app))
        .first();
    
    
        return true;
    }
}
