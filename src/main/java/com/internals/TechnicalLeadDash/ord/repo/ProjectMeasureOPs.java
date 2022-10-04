package com.internals.TechnicalLeadDash.ord.repo;

import com.internals.TechnicalLeadDash.ord.Domain.utils.ComfortRate;
import com.internals.TechnicalLeadDash.ord.Domain.ProjectMeasure;
import com.internals.TechnicalLeadDash.ord.Domain.Training;
import com.internals.TechnicalLeadDash.ord.Domain.utils.CompletetionStatus;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface ProjectMeasureOPs {
    Flux<ProjectMeasure> findAll();
    Flux<ProjectMeasure> findAllByDevName(String developerName);
    Flux<ProjectMeasure> findAllByTlName(String techLeadName);
    Mono<ProjectMeasure> findById(String id);

    Mono<ProjectMeasure> save(Mono<ProjectMeasure> projectMeasureMono);
    //Writen In Controller
    Mono<ProjectMeasure> updateTraining(String id, Training training);
    Mono<ProjectMeasure> updateComfortRate(String id, ComfortRate devRate, ComfortRate tlRate);
    Mono<ProjectMeasure> updateTlReport(String id, String tlReport);
    Mono<ProjectMeasure> updateOffBoarding(String id, String tlReport, LocalDate offBoardingDate);
    Mono<ProjectMeasure> updateCompletionStatusOrResource(String id, Training.CompositeKey compositeKey,
                                                          CompletetionStatus completetionStatus,
                                                          String resource);

}
