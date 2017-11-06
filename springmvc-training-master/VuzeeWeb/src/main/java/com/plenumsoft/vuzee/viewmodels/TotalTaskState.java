package com.plenumsoft.vuzee.viewmodels;

import com.plenumsoft.vuzee.entities.TaskState;

public class TotalTaskState {

	private Integer Total;
	private TaskState taskState;
	
	
	public Integer getTotal() {
		return Total;
	}
	public void setTotal(Integer total) {
		Total = total;
	}
	public TaskState getTaskState() {
		return taskState;
	}
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}
	
	
}
