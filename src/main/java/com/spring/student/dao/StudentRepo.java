package com.spring.student.dao;

import com.spring.student.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    public List<Student> findByFullNameContaining(String fullName, Pageable pageable);

    public int countByFullNameContaining(String fullName);

    @Query("select COUNT(id) from student ")
    public int getStudentSize();

}
