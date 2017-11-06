package com.plenumsoft.vuzee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.repositories.CandidateRepository;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {
	final CandidateRepository candidateRepository;
	
	@Autowired	
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		super();
		this.candidateRepository = candidateRepository;
		
	}
	
	@Override
	public List<Candidate> getAll() {
		return (List<Candidate>) this.candidateRepository.findAll();
	}
	
	@Override
	public Long addCandidate(Candidate candidate) {
		if(candidate==null)
			throw new CandidateServiceException("No se pudo agregar la entidad" + candidate);
		
		if(candidate.getName().length()==0)
			throw new CandidateServiceException("No se pudo agregar la entidad" + candidate);
		
		if(candidate.getPositionApplied().length()==0)
			throw new CandidateServiceException("No se pudo agregar la entidad" + candidate);
		
		Candidate insertedCandidate = this.candidateRepository.save(candidate);
		if(insertedCandidate!=null)
			return insertedCandidate.getId();
		
		return null;
	}

	@Override
	public Candidate getCandidateById(Long id) {
		
		Candidate can = this.candidateRepository.findOne(id);
		
		if(can == null)
			throw new CandidateServiceException("El candidato con el el id " + id + " no existe");
		
		return can;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		this.candidateRepository.save(candidate);
		
	}

	@Override
	public void deleteCandidateById(Long id) {
		// TODO Auto-generated method stub
		this.candidateRepository.delete(id);
		
	}

	@Override
	public Page<Candidate> listAllByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.candidateRepository.findAll(pageable);
	}

//	@Override
//	public DataTablesOutput<Candidate> findAll(DataTablesInput input) {
//		// TODO Auto-generated method stub
//		return this.candidateRepository.findAll(input);
//	}
	
}

