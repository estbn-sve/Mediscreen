package com.estbn.mediscreenpatients.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Patient model
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name="last_name")
    private String lastName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="sex")
    private Boolean sex;
    @Column(name="address")
    private String address;
    @Column(name="phone_number")
    private String phoneNumber;
}