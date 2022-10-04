package com.internals.TechnicalLeadDash.ord.controller;

import com.internals.TechnicalLeadDash.ord.Domain.ProjectMeasure;
import com.internals.TechnicalLeadDash.ord.repo.ProjectMeasureOPs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/projects")
public class ProjectMeasureController {

    @Autowired
    private ProjectMeasureOPs projectMeasureOPsRepo;

    @PostMapping
    public Mono<ProjectMeasure> save(@RequestBody ProjectMeasure projectMeasure){
        return projectMeasureOPsRepo.save(Mono.just(projectMeasure));
    }
    @GetMapping("/id/{id}")
    public Mono<ProjectMeasure> findById(@PathVariable String id){
        return projectMeasureOPsRepo.findById(id);
    }
    @GetMapping
    public Flux<ProjectMeasure> findALl(){
        return projectMeasureOPsRepo.findAll();
    }
    @GetMapping("/name/{developerName}")
    public Flux<ProjectMeasure> findALlByDevName(@PathVariable String developerName){
        return projectMeasureOPsRepo.findAllByDevName(developerName);
    }
    //@PutMapping("/{id}/trainings/update/{compositeKey}")
    //    public Mono<ProjectMeasure> updateTrainings( @PathVariable String id,@PathVariable Training.CompositeKey compositeKey, @RequestBody TrainingUpdateTO training){
    //        return service.updateCompletionStatusOrResource(id,compositeKey,training.getCompletionStatus(),training.getResourceLocation());
    //    }
    //    @PutMapping("/{id}/reporting") @Deprecated
    //    public Mono<ProjectMeasure> updateReport( @PathVariable String id, @RequestBody String tlReport) {
    //        return service.updateTlReport(id, tlReport);
    //    }
    //    @PutMapping("/{id}/offboarding")
    //    public Mono<ProjectMeasure> updateReport(@PathVariable String id, @RequestBody OffBoardingTO offBoardingTO) {
    //        return service.updateOffBoarding(id, offBoardingTO.getTlReport(),offBoardingTO.getOffBoardingDate());
    //    }
    //    @GetMapping
    //    public Flux<ProjectMeasure> findAll(){
    //        return service.findAll();
    //    }
    //    @PutMapping("/{id}/rating")
    //    public Mono<ProjectMeasure> updateComfortRate(@PathVariable String id, @RequestBody ComfortRateTo comfortRateTo){
    //        ComfortRate devRate= comfortRateTo.getDevRate();
    //        ComfortRate tlRate=comfortRateTo.getTlRate();
    //        return service.updateComfortRate(id,devRate,tlRate);
    //    }
}
