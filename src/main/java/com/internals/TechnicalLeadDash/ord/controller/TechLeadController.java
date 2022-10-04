package com.internals.TechnicalLeadDash.ord.controller;

import com.internals.TechnicalLeadDash.ord.Domain.TechLead;
import com.internals.TechnicalLeadDash.ord.repo.TechLeadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/lead")
public class TechLeadController {

    @Autowired
    private TechLeadRepo techLeadRepo;

    @PostMapping("/create")
    public Mono<TechLead> createTechLead(@RequestBody TechLead lead){
        return techLeadRepo.save(Mono.just(lead));
    }
    @GetMapping("/{id}")
    public Mono<TechLead> findById(@PathVariable String id ){
        return techLeadRepo.findById(id);
    }
    @GetMapping("/all")
    public Flux<TechLead> findAll(){
       return techLeadRepo.findAll();
    }
    @GetMapping("/names/{name}")
    public Flux<TechLead> findByName(@PathVariable String name){
        return techLeadRepo.findByName(name);
    }
   @PutMapping("/edit/{id}")
    public Mono<TechLead> updateTechLead(@PathVariable String id,
                                         @RequestParam String fullName,
                                         @RequestParam String profile){
       Mono<TechLead> techLead = techLeadRepo.updateTechLead(id,fullName,profile);
       System.out.println("Updated id:"+id);
       return techLead;
   }
}
