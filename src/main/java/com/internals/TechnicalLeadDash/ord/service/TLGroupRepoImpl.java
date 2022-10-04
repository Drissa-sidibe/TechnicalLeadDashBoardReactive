package com.internals.TechnicalLeadDash.ord.service;

import com.internals.TechnicalLeadDash.ord.Domain.TLGroup;
import com.internals.TechnicalLeadDash.ord.Domain.TechLead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.internals.TechnicalLeadDash.ord.repo.TLGroupRepo;
import com.internals.TechnicalLeadDash.ord.Domain.utils.GroupExpertise;

import java.util.List;

@Service
public class TLGroupRepoImpl implements TLGroupRepo {

    @Autowired
    private ReactiveMongoTemplate dbTemplate;

    @Override
    public Mono<TLGroup> save(Mono<TLGroup> tlGroup) {
        return dbTemplate.save(tlGroup);
    }

    @Override
    public Mono<TLGroup> findById(String id) {
        return dbTemplate.findById(id, TLGroup.class);
    }

    @Override
    public Mono<TLGroup> addTeachLeadToTlGroup(String id, TechLead techLead) {
        //Mono<TLGroup> tlGroupMono = dbTemplate.findById(id, TLGroup.class);
       // List<TechLead> techLeads = tlGroupMono.block().getTechLeads();
              //  getTechLead();
        //.add(techLead);
        Query query = new Query();
        query.addCriteria(Criteria.where("id"));
        Update update = new Update();
        update.addToSet("techLeads",techLead);
                //set("techLead", techLead);

        return dbTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), TLGroup.class);
    }

    @Override
    public Mono<TLGroup> updateExpertise(String id, List<GroupExpertise> expertises) {
            Mono<TLGroup> tlGroupMono = dbTemplate.findById(id, TLGroup.class);// Like the pathVariable
//            List<GroupExpertise> oldExpertises = tlGroupMono.block().getExpertises();
//                   // getGroupExpertise();
//            oldExpertises.stream().map(oldExpertises::add);
        Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update().addToSet("expertises", expertises);

        return dbTemplate.findAndModify(query,update,
                new FindAndModifyOptions().returnNew(true), TLGroup.class);
    }

    @Override
    public Flux<TLGroup> findAll() {
        return dbTemplate.findAll(TLGroup.class);
    }

    @Override
    public Flux<TLGroup> findByName(String name) {

        Query query = new Query();
        query.addCriteria(Criteria.where("groupName").is(name));
        return dbTemplate.find(query, TLGroup.class);
    }
}

