package org.school.teacherservice.Services;

import org.school.teacherservice.DTOs.TeacherRequestDto;
import org.school.teacherservice.DTOs.TeacherResponseDto;
import org.springframework.http.ResponseEntity;

public interface TeacherService {

    TeacherResponseDto addTeacher(TeacherRequestDto teacherRequestDto);

    ResponseEntity<?> getAllTeacher();

    ResponseEntity<?> getTeacherById(long id);

    // TeacherRequestDto updateTeacher(long id, TeacherResponseDto teacherResponseDto);

    // Boolean deleteTeacher(long id);

}
