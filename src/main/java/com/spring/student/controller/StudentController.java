package com.spring.student.controller;

import com.spring.student.Services.StudentServices;
import com.spring.student.model.Student;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Students")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentController {


    private StudentServices studentServices;

    @Autowired
    public StudentController (StudentServices studentServices) {
        this.studentServices = studentServices;
    }

//    http://localhost:8080/api/students
    @GetMapping("/students")
    public List<Student> getAllStudents(@RequestParam int page,@RequestParam int size) {
        return studentServices.getAllStudents(page, size);
    }

//    http://localhost:8080/api/students/2
    @GetMapping("students/{id}")
    public Student getStudentsById(@PathVariable long id) {
        return studentServices.getStudentsById(id);
    }

//    http://localhost:8080/api/student
    //Add new Student
    @PostMapping("student")
    public Student addStudent(@RequestBody Student student) {
        return studentServices.addStudent(student);
    }


//    http://localhost:8080/api/students?id=22
    //Update Student
    @PutMapping("students")
    public Student editStudent(@RequestBody Student student,@RequestParam long id) {
        student.setId(id);
        return studentServices.editStudent(student);
    }


//    http://localhost:8080/api/students/2
    @DeleteMapping("students/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentServices.deleteStudentById(id);
    }

    //http://localhost:8080/api/students/searchName?fullName=abdo
    @GetMapping("students/searchName")
    public List<Student> findByFullName(@RequestParam String fullName,@RequestParam int page,@RequestParam int size){
        return studentServices.findByFullName(fullName,page,size);
    }

    //http://localhost:8080/api/students/size
    @GetMapping("students/size")
    public int getStudentSize(){
        return studentServices.getStudentSize();
    }

    @GetMapping("students/sizeName")
    public int getStudentSizeByName(@RequestParam String fullName) {
        return studentServices.countByFullNameContaining(fullName);
    }

}
