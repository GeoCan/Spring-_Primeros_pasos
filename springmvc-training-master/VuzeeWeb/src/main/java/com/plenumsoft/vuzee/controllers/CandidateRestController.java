package com.plenumsoft.vuzee.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.services.CandidateService;
import com.plenumsoft.vuzee.viewmodels.CandidateViewModel;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateRestController {

	@Autowired
	private CandidateService candidateService;
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> DeleteCandidate(@PathVariable("id") long id) {
		System.out.println("Fetching & Delete user with id " + id);

		Candidate can = candidateService.getCandidateById(id);
		
		if (can == null) {
			System.out.println("Unable to delete. User with id " + id + "not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		candidateService.deleteCandidateById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value = "/allCandidate", method = RequestMethod.GET )
	public ResponseEntity<List<CandidateViewModel>> Index() {
		List<Candidate> candidates = candidateService.getAll();
		List<CandidateViewModel> candidatos = new ArrayList<CandidateViewModel>(); 
		
		CandidateViewModel candidato;
		for (Candidate candidate : candidates) {
			candidato = new CandidateViewModel(candidate.getId(), 
					candidate.getName(), 
					candidate.getPositionApplied(),
					candidate.getCreatedAt(),
					candidate.getCreatedBy()
					);
//			linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel()
			//apiModel.add(linkTo(methodOn(FilesController.class).getCVFile(candidate.getId())).withRel("cv"));
			HttpServletResponse response;
			HttpServletRequest request;
			
			//linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel()
			Link link = linkTo(methodOn(CandidateRestController.class).Index()).withRel("cv");
			
//			candidato.add(link);
			
			candidatos.add(candidato);
		}
		
		return new ResponseEntity<List<CandidateViewModel>>(candidatos ,HttpStatus.OK);
	}

	
//	@JsonView(DataTablesOutput.View.class)
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public DataTablesOutput<Candidate> getCandidates(@Valid DataTablesInput input) {
//		DataTablesOutput val = candidateService.findAll(input);
//		return val;
//	}
	
//	@JsonView(DataTablesOutput.View.class)
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public DataTablesOutput<Candidate> getCandidatos() {
//		return candidateService.findAll(new DataTablesInput());
//	}
	
//	@JsonView(DataTablesOutput.View.class)
//	@RequestMapping(value = "/Candidates/list", method = RequestMethod.POST)
//	public DataTablesOutput<Candidate> getCandidates(@Valid  @RequestBody DataTablesInput input) {
//		return candidateService.findAll(input);
//	}
	
	
//	@JsonView(DataTablesOutput.View.class)
//	@RequestMapping(value = "/Candidates/list", method = RequestMethod.GET)
//    public DataTablesOutput<Candidate> getUsers(@Valid DataTablesInput input) {
////        return userRepository.findAll(input);
//        return candidateService.findAll(input);
//    }
}



