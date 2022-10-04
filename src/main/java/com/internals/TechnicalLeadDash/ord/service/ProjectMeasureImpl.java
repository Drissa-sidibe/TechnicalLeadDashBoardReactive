package com.internals.TechnicalLeadDash.ord.service;

import com.internals.TechnicalLeadDash.ord.Domain.*;
import com.internals.TechnicalLeadDash.ord.Domain.utils.ComfortRate;
import com.internals.TechnicalLeadDash.ord.Domain.utils.CompletetionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.internals.TechnicalLeadDash.ord.repo.DeveloperRepo;
import com.internals.TechnicalLeadDash.ord.repo.ProjectMeasureOPs;
import com.internals.TechnicalLeadDash.ord.repo.TechLeadRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectMeasureImpl implements ProjectMeasureOPs {
//    private static final Query query =  new Query();
//    private static final Update update = new Update();
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private DeveloperRepo developerRepo;

    @Autowired
    private TechLeadRepo techLeadRepo;

    public ProjectMeasureImpl() {
    }


    @Override
    public Flux<ProjectMeasure> findAll() {
        return reactiveMongoTemplate.findAll(ProjectMeasure.class);
    }

    @Override
    public Flux<ProjectMeasure> findAllByDevName(String developerName) {
        Developer dev = developerRepo.finByName(developerName).blockFirst();
        assert dev != null: "Developer name is null";
        return reactiveMongoTemplate.aggregate(Aggregation.newAggregation(Aggregation.match(Criteria.where("developerName")
                .is(dev.getId()))), ProjectMeasure.class, ProjectMeasure.class);
    }

    @Override
    public Flux<ProjectMeasure> findAllByTlName(String techLead) {
        TechLead tL = this.techLeadRepo.findByName(techLead).blockFirst();
        assert tL != null : "The team lead name is null";
        return reactiveMongoTemplate.aggregate(Aggregation.newAggregation(Aggregation.match(Criteria.where("techLead")
                .is(tL.getId()))), ProjectMeasure.class, ProjectMeasure.class);

    }

    @Override
    public Mono<ProjectMeasure> findById(String id) {
        return reactiveMongoTemplate.findById(id, ProjectMeasure.class);
    }

    @Override
    public Mono<ProjectMeasure> save(Mono<ProjectMeasure> projectMeasureMono) {
        return reactiveMongoTemplate.save(projectMeasureMono);
    }

    @Override
    public Mono<ProjectMeasure> updateTraining(String id, Training training) {
        Mono<ProjectMeasure> project = reactiveMongoTemplate.findById(id, ProjectMeasure.class);
        List<Training> trainingList = project.block().getTrainings();
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("id", training);
        return  reactiveMongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), ProjectMeasure.class);
    }

    @Override
    public Mono<ProjectMeasure> updateComfortRate(String id, ComfortRate devRate, ComfortRate tlRate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("devRate", devRate).set("tlRate", tlRate);
        return reactiveMongoTemplate.findAndModify(query, update, new FindAndModifyOptions()
                .returnNew(true), ProjectMeasure.class);
    }

    @Override
    public Mono<ProjectMeasure> updateTlReport(String id, String tlReport) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("tlReport", tlReport);

        return reactiveMongoTemplate.findAndModify(query,update,
                new FindAndModifyOptions().returnNew(true), ProjectMeasure.class);
    }

    @Override
    public Mono<ProjectMeasure> updateOffBoarding(String id, String tlReport, LocalDate offBoardingDate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("tlReport", tlReport).set("offBoardingDate", offBoardingDate);
        return reactiveMongoTemplate.findAndModify(query,update,
                new FindAndModifyOptions().returnNew(true), ProjectMeasure.class);
    }

    @Override
    public Mono<ProjectMeasure> updateCompletionStatusOrResource(String id, Training.CompositeKey compositeKey, CompletetionStatus completetionStatus, String resource) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id)).addCriteria(Criteria.where("trainings")
                .is(compositeKey));
        Update update = new Update();

        if(completetionStatus!=null){
            update.set("completionsStats",completetionStatus);
        }
        if(resource!=null){
            update.set("resourceLocation", resource);
        }
        return  reactiveMongoTemplate.findAndModify(query,update, new FindAndModifyOptions()
                .returnNew(true),ProjectMeasure.class);
    }

}
