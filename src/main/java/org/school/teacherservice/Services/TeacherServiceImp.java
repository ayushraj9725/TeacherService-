package org.school.teacherservice.Services;

import lombok.RequiredArgsConstructor;
import org.school.teacherservice.DTOs.School;
import org.school.teacherservice.DTOs.SchoolResponseDto;
import org.school.teacherservice.DTOs.TeacherRequestDto;
import org.school.teacherservice.DTOs.TeacherResponseDto;
import org.school.teacherservice.InteractionClientFeign.TeacherFeignClient;
import org.school.teacherservice.Models.Teacher;
import org.school.teacherservice.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    private final TeacherFeignClient teacherFeignClient;

    @Override
    public TeacherResponseDto addTeacher(TeacherRequestDto teacherRequestDto) {

        try{
            Teacher teacher = Teacher.builder()
                    .name(teacherRequestDto.getName())
                    .email(teacherRequestDto.getEmail())
                    .phoneNumber(teacherRequestDto.getPhoneNumber())
                    .address(teacherRequestDto.getAddress())
                    .gender(teacherRequestDto.getGender())
                    .age((teacherRequestDto.getAge()))
                    .subject(teacherRequestDto.getSubject())
                    .schoolId(teacherRequestDto.getSchoolId())
                    .build();

            Teacher teacher1 = teacherRepository.save(teacher);

            // we want to call the school microservice
            School school = teacherFeignClient.getSchoolInfo(teacher1.getSchoolId());

            return TeacherResponseDto.fromTeacher(teacher1,school);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponseDto> teachersResponse = new ArrayList<>();

        try{
            if(!teachers.isEmpty()){
                for(Teacher teacher : teachers){
                    School school = teacherFeignClient.getSchoolInfo(teacher.getSchoolId());
                    TeacherResponseDto teacherResponseDto = TeacherResponseDto.builder()
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

                    teachersResponse.add(teacherResponseDto);
                }
            }
            return new ResponseEntity<>(teachersResponse,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getTeacherById(long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        try{
            if (teacher.isPresent()) {
                School school = teacherFeignClient.getSchoolInfo(teacher.get().getSchoolId());
                TeacherResponseDto teacherResponseDto = TeacherResponseDto.builder()
                        .id(teacher.get().getId())
                        .name(teacher.get().getName())
                        .email(teacher.get().getEmail())
                        .phoneNumber(teacher.get().getPhoneNumber())
                        .address(teacher.get().getAddress())
                        .gender(teacher.get().getGender())
                        .age(teacher.get().getAge())
                        .subject(teacher.get().getSubject())
                        .createdAt(teacher.get().getCreatedAt())
                        .updatedAt(teacher.get().getUpdatedAt())
                        .school(SchoolResponseDto.fromResponseSchool(school))
                        .build();

                return new ResponseEntity<>(teacherResponseDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Student Found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
