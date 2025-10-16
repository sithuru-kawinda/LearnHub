package com.teacher.registaration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teacher.registaration.dto.TeacherRequest;
import com.teacher.registaration.dto.TeacherResponse;
import com.teacher.registaration.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    
    @Autowired
    private TeacherService teacherService;
    
    @PostMapping("/register")
    public ResponseEntity<TeacherResponse> registerTeacher(
            @Valid @RequestBody TeacherRequest teacherRequest) {
        TeacherResponse response = teacherService.registerTeacher(teacherRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        List<TeacherResponse> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id) {
        TeacherResponse teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<TeacherResponse> getTeacherByEmail(@PathVariable String email) {
        TeacherResponse teacher = teacherService.getTeacherByEmail(email);
        return ResponseEntity.ok(teacher);
    }
}