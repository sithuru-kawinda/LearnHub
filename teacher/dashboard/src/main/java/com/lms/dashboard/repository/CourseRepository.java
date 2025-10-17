package com.lms.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.dashboard.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    // Find courses by teacher
    List<Course> findByTeacherId(Long teacherId);
    
    // Search courses by title
    List<Course> findByTitleContainingIgnoreCase(String keyword);
    
    // Get total course count
    @Query("SELECT COUNT(c) FROM Course c")
    Long getTotalCourseCount();
    
    // Get courses count by teacher
    @Query("SELECT COUNT(c) FROM Course c WHERE c.teacherId = :teacherId")
    Long getCourseCountByTeacher(Long teacherId);
    
    // Get all teacher IDs
    @Query("SELECT DISTINCT c.teacherId FROM Course c")
    List<Long> findAllTeacherIds();
}