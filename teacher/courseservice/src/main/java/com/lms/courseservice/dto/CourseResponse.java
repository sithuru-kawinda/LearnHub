package com.lms.courseservice.dto;

public class CourseResponse {
    private Long courseId;
    private String title;
    private String description;
    private Long teacherId;
    private String teacherName;
    
    // Constructors
    public CourseResponse() {}
    
    public CourseResponse(Long courseId, String title, String description, Long teacherId, String teacherName) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
    
    // Getters and Setters
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
    
    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
}