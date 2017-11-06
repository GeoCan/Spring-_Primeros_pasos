package com.plenumsoft.vuzee.api.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.plenumsoft.vuzee.api.models.*;
import com.plenumsoft.vuzee.api.models.CandidateModel;
import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.services.CandidateService;

import io.swagger.models.Model;

@RestController
@RequestMapping(value = "/api/v1/candidates")
public class RestCandidateController {

	CandidateService candidateService;

	@Autowired
	public RestCandidateController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<CandidateModel>> Get() {
		List<CandidateModel> candidatos = new ArrayList<>();
		try {
			List<Candidate> list = candidateService.getAll();

			for (Candidate candidate : list) {
				candidatos
						.add(new CandidateModel(candidate.getId(), candidate.getName(), candidate.getPositionApplied(),
								candidate.getCreatedAt(), candidate.getCreatedBy(), candidate.getCvFile() != null) {
						});
			}
		} catch (Exception ex) {
			// TODO: handle exception
			return new ResponseEntity<List<CandidateModel>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<CandidateModel>>(candidatos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CandidateModel> Get(@PathVariable("id") Long id) {
		CandidateModel candidatoModel = new CandidateModel();
		try {
			Candidate candidate = candidateService.getCandidateById(id);

			candidatoModel = new CandidateModel(candidate.getId(), candidate.getName(), candidate.getPositionApplied(),
					candidate.getCreatedAt(), candidate.getCreatedBy(), candidate.getCvFile() != null) {
			};

		} catch (Exception ex) {
			// TODO: handle exception
			return new ResponseEntity<CandidateModel>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<CandidateModel>(candidatoModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> Post(@RequestBody CandidateCreateModel candidateCreateModel,
			UriComponentsBuilder ucBuilder) {

		Candidate candidato = new Candidate();
		candidato.setName(candidateCreateModel.getName());
		candidato.setPositionApplied(candidateCreateModel.getPositionApplied());
		candidato.setCreatedBy("GeoCan");// TODO: hard-code
		candidato.setCreatedAt(new Date());
		
		candidateService.addCandidate(candidato);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(candidato.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> Update(@PathVariable("id") long id, 
			@ModelAttribute CandidateUpdateModel candidateUpdateModel,
			MultipartFile file )
			throws IOException {

		try {

			Candidate candidate = candidateService.getCandidateById(id);
			candidate.setName(candidateUpdateModel.getName());
			candidate.setPositionApplied(candidateUpdateModel.getPositionApplied());
			
			if (file != null) {
				String mimeType = file.getContentType();
				byte[] fileBytes = file.getBytes();
				String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
				candidate.setCvFile(fileBytes);
				candidate.setCvExtension(fileExtension);
				candidate.setCvMimeType(mimeType);
			}
			
			candidateService.updateCandidate(candidate);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> Delete(@PathVariable("id") long id) {
		System.out.println("Fetching & Delete user with id " + id);

		Candidate can = candidateService.getCandidateById(id);
		
		if (can == null) {
			System.out.println("Unable to delete. User with id " + id + "not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		candidateService.deleteCandidateById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value="/cv/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getCVFile(@PathVariable("id") Long id){
		HttpHeaders headers = new HttpHeaders();
		Candidate candidate = null;;
			try {
				candidate = candidateService.getCandidateById(id);
				if(candidate.getCvFile()!=null) {
					byte[] file = candidate.getCvFile();
					String fileName = candidate.getName().replaceAll(" ", "_");
					
				    headers.set(HttpHeaders.CONTENT_TYPE, candidate.getCvMimeType());
				    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv_"+ fileName.toLowerCase() +"."+ candidate.getCvExtension());
				    headers.set(HttpHeaders.PRAGMA, "no-cache");
				    headers.set(HttpHeaders.CACHE_CONTROL, "no-cache");
				}
				
			}catch(Exception ex) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			byte[] file = candidate.getCvFile();
			
			return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
			
		}
}
