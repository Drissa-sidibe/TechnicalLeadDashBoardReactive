package com.internals.TechnicalLeadDash.ord.service;

import com.internals.TechnicalLeadDash.ord.Domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.internals.TechnicalLeadDash.ord.repo.DeveloperRepo;

@Service
public class DeveloperImpl implements DeveloperRepo {
    @Autowired
    private ReactiveMongoTemplate template;
    @Override
    public Mono<Developer> save(Mono<Developer> developer) {
        return template.save(developer);
    }

    @Override
    public Mono<Developer> findById(String id) {
        return template.findById(id,Developer.class);
    }

    @Override
    public Flux<Developer> findAll() {
        return template.findAll(Developer.class);
    }

    @Override
    public Flux<Developer> finByName(String name) {
       Query query = new Query(Criteria.where("fullName").is(name));
       return  template.find(query,Developer.class);
    }
}
