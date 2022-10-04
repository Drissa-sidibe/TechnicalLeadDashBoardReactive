package com.internals.TechnicalLeadDash.ord.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@Document @Data
public class ProjectMeasure {//listt pr<M
    @Id
    private String id;
    private String projectDescription;
    private Integer yearsOfExperience;///Whose experience
    private String stack;

    @DocumentReference// Similar to @OneTone/ OneToMany and ManyToOne etc..
    private Developer developer;//manyToOne/manytoman

    @DocumentReference// refers to a location in DB. Can Read/write and listen to the location
    private TechLead techLead;//OneTechLead for many/

    private LocalDate onBoardingDate;
    private LocalDate offBoardingDate;
    private String offBoardingReason;

    @LastModifiedDate
    @DBRef // refers to data in another table and read both tables. It is like joins colum
    private List<Training> trainings;//manytomany


}
