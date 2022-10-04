package com.internals.TechnicalLeadDash.ord.repo;

import com.internals.TechnicalLeadDash.ord.Domain.Developer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DeveloperRepo {
     Mono<Developer> save(Mono<Developer> developer);
     Mono<Developer> findById(String id);
     Flux<Developer> findAll();
     Flux<Developer> finByName(String name);

}
