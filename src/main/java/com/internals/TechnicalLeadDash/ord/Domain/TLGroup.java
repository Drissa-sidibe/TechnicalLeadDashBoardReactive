package com.internals.TechnicalLeadDash.ord.Domain;

import com.internals.TechnicalLeadDash.ord.Domain.utils.GroupExpertise;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
@Data
public class TLGroup {
    @Id
    private String id;
    private String groupName;
//Why and how?
    private List<GroupExpertise> expertises;
    @DBRef
    private List<TechLead> techLeads =new ArrayList<>();



}
