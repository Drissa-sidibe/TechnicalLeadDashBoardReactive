package com.internals.TechnicalLeadDash.ord.Domain;

import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import com.internals.TechnicalLeadDash.ord.Domain.utils.CompletetionStatus;

import java.io.Serializable;
import java.time.LocalDate;

@Document
@Data
public class Training implements Persistable {
    @Id//Composite key is a pprimaryKey that is composite  of different components
    private CompositeKey id;
    private String entitlement;
    private String category;
    private LocalDate assignedDate;
    private LocalDate dueDate;
    //triggers due date
    private CompletetionStatus completeStatus;
    private String resourceLocations;

    Training(String entitlement, LocalDate assignedAtDate){
        id = new CompositeKey(entitlement, assignedAtDate);
    }

    public boolean isNew(){
        return false;
    }

    @Value
   public static class CompositeKey implements Serializable{
        private String entitlement;
        private LocalDate assignedAtDate;

//        public CompositeKey(String entitlement, String assignedAtDate) {
//        }
        //private Random random = new Random(3);
    }


}
