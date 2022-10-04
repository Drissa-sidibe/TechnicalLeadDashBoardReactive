package com.internals.TechnicalLeadDash.ord.Domain;


import com.internals.TechnicalLeadDash.ord.Domain.utils.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Document
@Data
public class TechLead {
    @Id
    private String id;
    private String fullName;
    private String profile;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    //@DocumentReference
    @DBRef
    private TLGroup tlGroup;
    //ListProject

}
