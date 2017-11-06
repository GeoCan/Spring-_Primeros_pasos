package com.plenumsoft.vuzee.viewmodels;

import java.util.List;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.entities.TaskState;

public class DashBoardViewModel {
	
//	List<Candidate> candidatos;
//
//	public List<Candidate> getCandidatos() {
//		return candidatos;
//	}
//
//	public void setCandidatos(List<Candidate> candidatos) {
//		this.candidatos = candidatos;
//	}
	
	private List<TotalTaskState> totales;
	private String candidato;
	private Long idCandidato;
	
	
	public String getCandidato() {
		return candidato;
	}
	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}
	public Long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public List<TotalTaskState> getTotales() {
		return totales;
	}
	public void setTotales(List<TotalTaskState> totales) {
		this.totales = totales;
	}
	
	
	

}