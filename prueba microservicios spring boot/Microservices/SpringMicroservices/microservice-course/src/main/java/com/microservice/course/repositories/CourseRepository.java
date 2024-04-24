package com.microservice.course.repositories;

import com.microservice.course.entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity,Long> {

}
