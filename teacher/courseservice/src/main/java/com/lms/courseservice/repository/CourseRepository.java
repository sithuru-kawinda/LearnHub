package com.lms.courseservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.courseservice.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacherId(Long teacherId);
    List<Course> findByTitleContainingIgnoreCase(String title);
}