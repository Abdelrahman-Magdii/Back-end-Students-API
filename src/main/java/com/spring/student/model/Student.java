package com.spring.student.model;

import com.spring.student.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
// Lombok is the lip to creat getter ,setter and Constructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "student")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private String age;

    @Column(name="phone_number")
    private String phone;

    @Column(name = "address")
    private String address;

    @CreationTimestamp
    @Column(name = "dateCreated")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "dateUpdated")
    private Date dateUpdated;




}
