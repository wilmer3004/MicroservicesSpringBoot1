package com.microservice.course.services.impl;

import com.microservice.course.clients.StudentsClient;
import com.microservice.course.controllers.dto.StudentDTO;
import com.microservice.course.entities.CourseEntity;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.repositories.CourseRepository;
import com.microservice.course.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentsClient studentsClient;


    @Override
    public List<CourseEntity> findAll() {
        return (List<CourseEntity>) courseRepository.findAll();
    }

    @Override
    public CourseEntity findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(CourseEntity course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentByIdCourse(Long idCourse) {

        //Search course
        CourseEntity course = courseRepository.findById(idCourse).orElse(new CourseEntity());
        //get students
        List<StudentDTO> studentDTOList = studentsClient.findAllStudentsByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }
}
