package com.lms.courseservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.courseservice.dto.CourseRequest;
import com.lms.courseservice.dto.CourseResponse;
import com.lms.courseservice.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        CourseResponse response = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        CourseResponse course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseResponse>> getCoursesByTeacher(@PathVariable Long teacherId) {
        List<CourseResponse> courses = courseService.getCoursesByTeacher(teacherId);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CourseResponse>> searchCourses(@RequestParam String keyword) {
        List<CourseResponse> courses = courseService.searchCourses(keyword);
        return ResponseEntity.ok(courses);
    }
}