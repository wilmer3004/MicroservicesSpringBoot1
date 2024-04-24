package com.microservice.course.services;

import com.microservice.course.entities.CourseEntity;
import com.microservice.course.http.response.StudentByCourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICourseService {

    List<CourseEntity> findAll();

    CourseEntity findById(Long id);

    void save(CourseEntity course);

    StudentByCourseResponse findStudentByIdCourse(Long idCourse);

}
