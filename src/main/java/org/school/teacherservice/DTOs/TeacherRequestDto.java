package org.school.teacherservice.DTOs;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequestDto {

    private String name ;

    private String email ;

    private String phoneNumber ;

    private String address ;

    private String gender ;

    private Integer age ;

    private String subject ;

    private Long schoolId ;

}
