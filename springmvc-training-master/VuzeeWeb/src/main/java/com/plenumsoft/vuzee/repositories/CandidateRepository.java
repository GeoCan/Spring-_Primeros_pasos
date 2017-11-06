package com.plenumsoft.vuzee.repositories;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.plenumsoft.vuzee.entities.Candidate;


@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long>, PagingAndSortingRepository <Candidate, Long> {//DataTablesRepository<Candidate, Long>  {
}
