package com.internals.TechnicalLeadDash.ord.controller;

import com.internals.TechnicalLeadDash.ord.Domain.TLGroup;
import com.internals.TechnicalLeadDash.ord.Domain.TechLead;
import com.internals.TechnicalLeadDash.ord.Domain.utils.GroupExpertise;
import com.internals.TechnicalLeadDash.ord.repo.TLGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("tlgroup")
public class TLGroupController {

    @Autowired
    private TLGroupRepo techLeadRepo;

    @PostMapping("/create")
    public Mono<TLGroup> save(@RequestBody Mono<TLGroup> techleagroup){
        return techLeadRepo.save(techleagroup);
    }
    @GetMapping("/all")
    public Flux<TLGroup> findAll(){
       return techLeadRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public Mono<TLGroup> findById(@PathVariable String id){
        return techLeadRepo.findById(id);
    }


    @GetMapping("/addtlToGroup/{id}")
    public Mono<TLGroup> addTechLeadToTLGroup(@PathVariable String id, @RequestBody TechLead techLead){
        return techLeadRepo.addTeachLeadToTlGroup(id,techLead);
    }
    @PutMapping("/addLeadExpertise/{id}")
    public ResponseEntity<Mono<TLGroup>> addingExpertisesToGroup(@PathVariable String id, List<GroupExpertise> expertiseList){
        URI uri= URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/addLeadExpertise/{id}").toUriString());
      return ResponseEntity.created(uri).body(techLeadRepo.updateExpertise(id, expertiseList));
      //update expertise means adding more expertise to the list of expertises.
    }
    @GetMapping("/names/{name}")
    public Flux<TLGroup> findByName(@PathVariable String name){
        return techLeadRepo.findByName("name");
    }

}
