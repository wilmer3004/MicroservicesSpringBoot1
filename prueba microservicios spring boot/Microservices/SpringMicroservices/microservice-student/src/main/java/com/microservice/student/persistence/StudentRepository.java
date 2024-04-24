package com.microservice.student.persistence;

import com.microservice.student.entities.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

    @Query("SELECT s FROM StudentEntity s WHERE s.courseId = :idCourse")
    List<StudentEntity> findAllStudent(@Param("idCourse") Long idCourse);

    List<StudentEntity> findAllByCourseId(Long idCourse);
}
