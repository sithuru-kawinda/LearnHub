package com.lms.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dashboard.dto.DashboardResponse;
import com.lms.dashboard.model.Course;
import com.lms.dashboard.repository.CourseRepository;

@Service
public class DashboardService {

    @Autowired
    private CourseRepository courseRepository;

    public DashboardResponse getDashboardData() {
        // Get total courses count
        Long totalCourses = courseRepository.getTotalCourseCount();
        
        // Get total teachers count
        List<Long> teacherIds = courseRepository.findAllTeacherIds();
        Long totalTeachers = (long) teacherIds.size();
        
        // Get courses by teacher distribution
        Map<String, Long> coursesByTeacher = new HashMap<>();
        for (Long teacherId : teacherIds) {
            Long count = courseRepository.getCourseCountByTeacher(teacherId);
            coursesByTeacher.put("Teacher " + teacherId, count);
        }
        
        // Get recent courses (last 5)
        List<Course> allCourses = courseRepository.findAll();
        List<DashboardResponse.CourseSummary> recentCourses = allCourses.stream()
                .limit(5)
                .map(course -> new DashboardResponse.CourseSummary(
                    course.getCourseId(),
                    course.getTitle(),
                    course.getTeacherName()
                ))
                .collect(Collectors.toList());
        
        // Get teacher statistics
        List<DashboardResponse.TeacherSummary> teacherStats = teacherIds.stream()
                .map(teacherId -> {
                    Long count = courseRepository.getCourseCountByTeacher(teacherId);
                    return new DashboardResponse.TeacherSummary(
                        teacherId,
                        "Teacher " + teacherId,
                        count
                    );
                })
                .collect(Collectors.toList());
        
        return new DashboardResponse(
            totalCourses,
            totalTeachers,
            coursesByTeacher,
            recentCourses,
            teacherStats
        );
    }
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public List<Course> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }
    
    public List<Course> searchCourses(String keyword) {
        return courseRepository.findByTitleContainingIgnoreCase(keyword);
    }
    
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
    }
    
    // Get dashboard statistics only
    public Map<String, Object> getDashboardStats() {
        Long totalCourses = courseRepository.getTotalCourseCount();
        List<Long> teacherIds = courseRepository.findAllTeacherIds();
        Long totalTeachers = (long) teacherIds.size();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCourses", totalCourses);
        stats.put("totalTeachers", totalTeachers);
        stats.put("averageCoursesPerTeacher", totalTeachers > 0 ? totalCourses / totalTeachers : 0);
        
        return stats;
    }
    
    // Get total courses count
    public Long getTotalCoursesCount() {
        return courseRepository.getTotalCourseCount();
    }
}