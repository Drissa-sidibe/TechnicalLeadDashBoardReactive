package com.internals.TechnicalLeadDash.ord.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.internals.TechnicalLeadDash.ord.Domain.utils.Gender;

import java.time.LocalDate;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Developer {

    @Id
//  private   ObjectId id;
    private String id;
    private String fullName;
    private String profile;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfLeave;
}
