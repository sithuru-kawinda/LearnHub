package com.lms.courseservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.courseservice.dto.CourseRequest;
import com.lms.courseservice.dto.CourseResponse;
import com.lms.courseservice.model.Course;
import com.lms.courseservice.repository.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public CourseResponse createCourse(CourseRequest courseRequest) {
        // In a real scenario, you might call teacher service to validate teacher exists
        // For now, we'll assume teacher exists
        
        // Create course
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setTeacherId(courseRequest.getTeacherId());
        course.setTeacherName("Teacher " + courseRequest.getTeacherId()); // You can fetch actual name from teacher service
        
        Course savedCourse = courseRepository.save(course);
        
        return convertToResponse(savedCourse);
    }
    
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<CourseResponse> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return convertToResponse(course);
    }
    
    public List<CourseResponse> searchCourses(String keyword) {
        return courseRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    private CourseResponse convertToResponse(Course course) {
        return new CourseResponse(
            course.getCourseId(),
            course.getTitle(),
            course.getDescription(),
            course.getTeacherId(),
            course.getTeacherName()
        );
    }
}