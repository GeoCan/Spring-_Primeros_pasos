package com.plenumsoft.vuzee.viewmodels;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskCreateViewModel {

	@NotNull
	private Long candidato;
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date taskDate;
	@NotNull
	private int taskState;
	public Long getCandidato() {
		return candidato;
	}
	public void setCandidato(Long candidato) {
		this.candidato = candidato;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	public int getTaskState() {
		return taskState;
	}
	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}
	
	
}
