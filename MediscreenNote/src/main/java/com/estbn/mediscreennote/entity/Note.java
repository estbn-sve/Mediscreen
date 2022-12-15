package com.estbn.mediscreennote.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


//import javax.persistence.*;
/**
 * Note model
 */
//@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name= "notes")
@Document(collection = "notes")
public class Note {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "_id")
    @Id
    private String id;
//    @Column(name="0")
    @Field(name="0")
    private String idPatient;
//    @Column(name="1")
    @Field(name="1")
    private String note;
}