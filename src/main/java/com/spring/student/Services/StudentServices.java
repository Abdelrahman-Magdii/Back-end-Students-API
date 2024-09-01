package com.spring.student.Services;

import com.spring.student.dao.StudentRepo;
import com.spring.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class StudentServices {

    private StudentRepo studentRepo;

    @Autowired
    public StudentServices(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return studentRepo.findAll(pageable).getContent();
    }

    public Student getStudentsById(long id) {
        return studentRepo.findById(id).get();
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student editStudent(Student student) {
        return studentRepo.save(student);
    }


    public void deleteStudentById(long id) {
        studentRepo.deleteById(id);
    }

    public List<Student> findByFullName(String fullName,int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return studentRepo.findByFullNameContaining(fullName,pageable);
    }

    public int getStudentSize(){
        return studentRepo.getStudentSize();
    }
    public int countByFullNameContaining(String fullName){
        return studentRepo.countByFullNameContaining(fullName);
    }

}
