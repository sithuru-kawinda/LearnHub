package com.lms.dashboard.dto;


import java.util.List;
import java.util.Map;

public class DashboardResponse {
    private Long totalCourses;
    private Long totalTeachers;
    private Map<String, Long> coursesByTeacher;
    private List<CourseSummary> recentCourses;
    private List<TeacherSummary> teacherStats;
    
    // Constructors
    public DashboardResponse() {}
    
    public DashboardResponse(Long totalCourses, Long totalTeachers, 
                           Map<String, Long> coursesByTeacher, 
                           List<CourseSummary> recentCourses,
                           List<TeacherSummary> teacherStats) {
        this.totalCourses = totalCourses;
        this.totalTeachers = totalTeachers;
        this.coursesByTeacher = coursesByTeacher;
        this.recentCourses = recentCourses;
        this.teacherStats = teacherStats;
    }
    
    // Getters and Setters
    public Long getTotalCourses() { return totalCourses; }
    public void setTotalCourses(Long totalCourses) { this.totalCourses = totalCourses; }
    
    public Long getTotalTeachers() { return totalTeachers; }
    public void setTotalTeachers(Long totalTeachers) { this.totalTeachers = totalTeachers; }
    
    public Map<String, Long> getCoursesByTeacher() { return coursesByTeacher; }
    public void setCoursesByTeacher(Map<String, Long> coursesByTeacher) { this.coursesByTeacher = coursesByTeacher; }
    
    public List<CourseSummary> getRecentCourses() { return recentCourses; }
    public void setRecentCourses(List<CourseSummary> recentCourses) { this.recentCourses = recentCourses; }
    
    public List<TeacherSummary> getTeacherStats() { return teacherStats; }
    public void setTeacherStats(List<TeacherSummary> teacherStats) { this.teacherStats = teacherStats; }
    
    // Inner class for course summary
    public static class CourseSummary {
        private Long courseId;
        private String title;
        private String teacherName;
        
        public CourseSummary() {}
        
        public CourseSummary(Long courseId, String title, String teacherName) {
            this.courseId = courseId;
            this.title = title;
            this.teacherName = teacherName;
        }
        
        // Getters and Setters
        public Long getCourseId() { return courseId; }
        public void setCourseId(Long courseId) { this.courseId = courseId; }
        
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public String getTeacherName() { return teacherName; }
        public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
    }
    
    // Inner class for teacher summary
    public static class TeacherSummary {
        private Long teacherId;
        private String teacherName;
        private Long courseCount;
        
        public TeacherSummary() {}
        
        public TeacherSummary(Long teacherId, String teacherName, Long courseCount) {
            this.teacherId = teacherId;
            this.teacherName = teacherName;
            this.courseCount = courseCount;
        }
        
        // Getters and Setters
        public Long getTeacherId() { return teacherId; }
        public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
        
        public String getTeacherName() { return teacherName; }
        public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
        
        public Long getCourseCount() { return courseCount; }
        public void setCourseCount(Long courseCount) { this.courseCount = courseCount; }
    }
}