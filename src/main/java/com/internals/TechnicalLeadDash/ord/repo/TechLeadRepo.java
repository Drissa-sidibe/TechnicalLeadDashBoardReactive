package com.internals.TechnicalLeadDash.ord.repo;

import com.internals.TechnicalLeadDash.ord.Domain.TechLead;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TechLeadRepo {
    Mono<TechLead> save(Mono<TechLead> techLeadMono);
    Mono<TechLead> findById(String id);
    Flux<TechLead> findAll();
    Flux<TechLead> findByName(String name);
    Mono<TechLead> updateTechLead( String id , String fullName,String profile);

}
