package com.teacher.registaration.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teacher.registaration.dto.TeacherRequest;
import com.teacher.registaration.dto.TeacherResponse;
import com.teacher.registaration.model.Teacher;
import com.teacher.registaration.repository.TeacherRepository;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    public TeacherResponse registerTeacher(TeacherRequest teacherRequest) {
        // Check if email already exists
        if (teacherRepository.existsByEmail(teacherRequest.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        // Create new teacher
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setPassword(teacherRequest.getPassword());
        teacher.setQualification(teacherRequest.getQualification());
        
        Teacher savedTeacher = teacherRepository.save(teacher);
        
        return convertToResponse(savedTeacher);
    }
    
    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public TeacherResponse getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return convertToResponse(teacher);
    }
    
    public TeacherResponse getTeacherByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Teacher not found with email: " + email));
        return convertToResponse(teacher);
    }
    
    private TeacherResponse convertToResponse(Teacher teacher) {
        return new TeacherResponse(
            teacher.getId(),
            teacher.getName(),
            teacher.getEmail(),
            teacher.getQualification()
        );
    }
}