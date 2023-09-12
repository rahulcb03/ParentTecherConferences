package com.kidzlandConfrences;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, ObjectId>{

    Optional<Teacher> findTeacherById(String ObjectId);
}
