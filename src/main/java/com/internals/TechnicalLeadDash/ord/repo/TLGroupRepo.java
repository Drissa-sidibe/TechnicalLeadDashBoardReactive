package com.internals.TechnicalLeadDash.ord.repo;

import com.internals.TechnicalLeadDash.ord.Domain.TLGroup;
import com.internals.TechnicalLeadDash.ord.Domain.TechLead;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.internals.TechnicalLeadDash.ord.Domain.utils.GroupExpertise;

import java.util.List;

@Repository
public interface TLGroupRepo {
     Mono<TLGroup> save(Mono<TLGroup> tlGroup);
     Mono<TLGroup> findById(String id);
     Mono<TLGroup> addTeachLeadToTlGroup(String id, TechLead techLead);
     Mono<TLGroup> updateExpertise(String id, List<GroupExpertise> expertises);
     Flux<TLGroup> findAll();
     Flux<TLGroup> findByName(String name);
}
