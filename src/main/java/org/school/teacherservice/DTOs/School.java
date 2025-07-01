package org.school.teacherservice.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class School {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String contactEmail;
    private String contactPhone;

    private String principalName;
    private String boardAffiliation; // e.g., CBSE, ICSE, State Board

    private LocalDate establishedDate;
    private Boolean isActive;

    // Audit
    private Date createdAt;
    private Date updatedAt;

    // Optional: One-to-many with Departments, etc. if you want more structure

}