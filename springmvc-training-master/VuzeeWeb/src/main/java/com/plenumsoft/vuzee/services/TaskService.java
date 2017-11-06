package com.plenumsoft.vuzee.services;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskTotales;

@Service
public interface TaskService {
	List<Task> getAll();
	Task getTask(Long id);
	Long addTask(Task task);
	void updateTask(Task task);
	void deleteTask(Long id);
	List<TaskTotales> getTasksTotales();
}
