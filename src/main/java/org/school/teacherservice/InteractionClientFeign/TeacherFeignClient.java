package org.school.teacherservice.InteractionClientFeign;

import org.school.teacherservice.DTOs.School;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "SCHOOL-SERVICE")
public interface TeacherFeignClient {

    @GetMapping("/api/v1/school/{id}")
    School getSchoolInfo(@PathVariable Long id);

}
