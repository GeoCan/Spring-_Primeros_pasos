package com.plenumsoft.vuzee.entities;


public class TaskTotales {
	
	private Long CandidateId;
	private String Name;
	private TaskState taskState;
	private Long Total;
	
	
	
	public Long getCandidateId() {
		return CandidateId;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setCandidateId(Long candidateId) {
		CandidateId = candidateId;
	}
	public TaskState getTaskState() {
		return taskState;
	}
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}
	public Long getTotal() {
		return Total;
	}
	public void setTotal(Long total) {
		Total = total;
	}

	
	
	
}
