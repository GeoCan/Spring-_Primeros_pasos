package com.plenumsoft.vuzee.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Candidate;

@Service
public interface CandidateService {
	List<Candidate> getAll();
	Candidate getCandidateById(Long id);
	Long addCandidate(Candidate candidate);
	void updateCandidate(Candidate candidate);
	void deleteCandidateById(Long id);
	Page<Candidate> listAllByPage(Pageable pageable);
//	DataTablesOutput<Candidate> findAll(DataTablesInput input);
}
