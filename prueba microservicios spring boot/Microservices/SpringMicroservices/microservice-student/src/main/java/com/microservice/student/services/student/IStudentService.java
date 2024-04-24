package com.microservice.student.services.student;

import com.microservice.student.entities.StudentEntity;

import java.util.List;

public interface IStudentService {

    List<StudentEntity> findAll();

    StudentEntity findById(Long id);

    void save(StudentEntity student);

    List<StudentEntity>findByIdCourse(Long idCourse);

}
