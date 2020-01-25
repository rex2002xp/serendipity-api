package org.serendipity.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import org.serendipity.restapi.model.Individual;
import org.serendipity.restapi.service.IndividualService;

import org.serendipity.restapi.hateoas.IndividualRepresentationModelAssembler;

@RestController
@RequestMapping("/api")
public class IndividualController {
  
  @Autowired
  private IndividualService entityService;
  
  @Autowired
  private IndividualRepresentationModelAssembler assembler;
  
  @GetMapping("/individuals")
  public ResponseEntity<CollectionModel<EntityModel<Individual>>> findAll() {
    
    return ResponseEntity.ok(assembler.toCollectionModel(entityService.findAll()));
  }
  
  @GetMapping("/individuals/{id}")
  public ResponseEntity<Individual> findById(
      @PathVariable("id") final Long id) throws ResponseStatusException {
    
    Individual individual = entityService.findById(id).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND));
    
    return ResponseEntity.ok().body(individual);
  }
  
}

// https://github.com/spring-projects/spring-hateoas-examples/blob/master/hypermedia/src/main/java/org/springframework/hateoas/examples/EmployeeController.java

/*

  @GetMapping("/individuals")
  public List<Individual> findAll() {

    return entityService.findAll();
  }


    return ResponseEntity.status(HttpStatus.OK).body(individual);
    
      throws ResourceNotFoundException {
      Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
      return ResponseEntity.ok().body(employee);

*/
