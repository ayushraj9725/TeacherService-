package org.school.teacherservice.Controllers;

import org.school.teacherservice.DTOs.TeacherRequestDto;
import org.school.teacherservice.DTOs.TeacherResponseDto;
import org.school.teacherservice.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @PostMapping("/assign")
    public ResponseEntity<?> assignTeacher(@RequestBody TeacherRequestDto teacherRequestDto){
        try{
            TeacherResponseDto response = teacherService.addTeacher(teacherRequestDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTeacher(){
        try{
            return new ResponseEntity<>(teacherService.getAllTeacher(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable long id){
        try{
            return new ResponseEntity<>(teacherService.getTeacherById(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
