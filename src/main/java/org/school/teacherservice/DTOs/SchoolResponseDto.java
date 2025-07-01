package org.school.teacherservice.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolResponseDto {

    private Long id ;

    private String name ;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String contactPhone;

    private String principalName;
    private String boardAffiliation; // e.g., CBSE, ICSE, State Board

    private LocalDate establishedDate;
    private Boolean isActive;

    // Audit
    private Date createdAt;
    private Date updatedAt;


    public static SchoolResponseDto fromResponseSchool(School school){
        return SchoolResponseDto.builder()
                .id(school.getId())
                .name(school.getName())
                .address(school.getAddress())
                .city(school.getCity())
                .state(school.getState())
                .zipCode(school.getZipCode())
                .contactPhone(school.getContactPhone())
                .principalName(school.getPrincipalName())
                .boardAffiliation(school.getBoardAffiliation())
                .establishedDate(school.getEstablishedDate())
                .isActive(school.getIsActive())
                .createdAt(school.getCreatedAt())
                .updatedAt(school.getUpdatedAt())
                .build();
    }

}
