package com.internals.TechnicalLeadDash.ord.service;

import com.internals.TechnicalLeadDash.ord.Domain.TechLead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.internals.TechnicalLeadDash.ord.repo.TechLeadRepo;

import javax.swing.*;

@Service
public class TechLeadRepoImpl implements TechLeadRepo {


    @Autowired
    ReactiveMongoTemplate mongoTemplate;


    @Override
    public Mono<TechLead> save(Mono<TechLead> techLeadMono) {
        return mongoTemplate.save(techLeadMono);
    }

    @Override
    public Mono<TechLead> findById(String id) {
        return mongoTemplate.findById(id, TechLead.class);
    }

    @Override
    public Flux<TechLead> findAll() {
        return mongoTemplate.findAll(TechLead.class);
    }

    @Override
    public Flux<TechLead> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fullName").is(name));
        if (name.isBlank()){
            throw new RuntimeException("This name is blank");
        }
        return mongoTemplate.find(query, TechLead.class);
    }

    @Override
    public Mono<TechLead> updateTechLead(String id, String fullName, String profile) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("fullName", fullName);
        update.set("profile", profile);
       // System.out.println("Updated id:"+id);
        return mongoTemplate.findAndModify(query, update,
        new FindAndModifyOptions().returnNew(true), TechLead.class);

    }

}
