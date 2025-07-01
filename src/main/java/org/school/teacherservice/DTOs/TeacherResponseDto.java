package org.school.teacherservice.DTOs;

import lombok.*;
import org.school.teacherservice.Models.Teacher;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDto {

    private Long id ;

    private String name ;

    private String email ;

    private String phoneNumber ;

    private String address ;

    private String gender ;

    private Integer age ;

    private String subject ;

    private Date createdAt;

    private Date updatedAt;

    private SchoolResponseDto school ;

    public static TeacherResponseDto fromTeacher(Teacher teacher,School school){
        return TeacherResponseDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .address(teacher.getAddress())
                .gender(teacher.getGender())
                .age(teacher.getAge())
                .subject(teacher.getSubject())
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())
                .school(SchoolResponseDto.fromResponseSchool(school))
                .build();
    }

}
