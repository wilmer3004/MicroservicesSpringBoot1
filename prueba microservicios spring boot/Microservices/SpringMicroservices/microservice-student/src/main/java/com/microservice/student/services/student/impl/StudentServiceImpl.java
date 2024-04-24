package com.microservice.student.services.student.impl;

import com.microservice.student.entities.StudentEntity;
import com.microservice.student.persistence.StudentRepository;
import com.microservice.student.services.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<StudentEntity> findAll() {
        return (List<StudentEntity>) studentRepository.findAll();
    }

    @Override
    public StudentEntity findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(StudentEntity student) {
        studentRepository.save(student);
    }

    @Override
    public List<StudentEntity> findByIdCourse(Long idCourse) {
        return studentRepository.findAllStudent(idCourse);
    }
}
