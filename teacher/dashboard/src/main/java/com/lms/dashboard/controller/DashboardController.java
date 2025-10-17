package com.lms.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dashboard.dto.DashboardResponse;
import com.lms.dashboard.model.Course;
import com.lms.dashboard.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    // Get complete dashboard data
    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard() {
        DashboardResponse dashboardData = dashboardService.getDashboardData();
        return ResponseEntity.ok(dashboardData);
    }
    
    // Get dashboard statistics only
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }
    
    // Get all courses
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = dashboardService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    // Get courses by teacher
    @GetMapping("/courses/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable Long teacherId) {
        List<Course> courses = dashboardService.getCoursesByTeacher(teacherId);
        return ResponseEntity.ok(courses);
    }
    
    // Search courses
    @GetMapping("/courses/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String keyword) {
        List<Course> courses = dashboardService.searchCourses(keyword);
        return ResponseEntity.ok(courses);
    }
    
    // Get course by ID
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = dashboardService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    
    // Get courses count - FIXED THIS METHOD
    @GetMapping("/courses/count")
    public ResponseEntity<Map<String, Long>> getCoursesCount() {
        Long count = dashboardService.getTotalCoursesCount();
        return ResponseEntity.ok(Map.of("totalCourses", count));
    }
}