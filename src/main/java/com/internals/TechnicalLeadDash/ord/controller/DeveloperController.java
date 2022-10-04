package com.internals.TechnicalLeadDash.ord.controller;

import com.internals.TechnicalLeadDash.ord.Domain.Developer;
import com.internals.TechnicalLeadDash.ord.repo.DeveloperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dev")
public class DeveloperController {
    @Autowired
    private DeveloperRepo repo;

    @PostMapping("/create")
    public Mono<Developer> save( @RequestBody Developer developer){
        return repo.save(Mono.just(developer));
    }
    @GetMapping("/id/{id}")
    public Mono<Developer> findOne(@PathVariable String id){
        return repo.findById(id);
    }
    @GetMapping("/name/{name}")
    public Flux<Developer> findByName(@PathVariable String name){
        return repo.finByName(name);
    }
    @GetMapping
    public Flux<Developer> findAll(){
        return repo.findAll();
    }
}
