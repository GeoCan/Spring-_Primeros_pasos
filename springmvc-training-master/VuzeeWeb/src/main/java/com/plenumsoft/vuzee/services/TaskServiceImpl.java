package com.plenumsoft.vuzee.services;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskTotales;
import com.plenumsoft.vuzee.repositories.TaskRespository;

@Service
public class TaskServiceImpl implements TaskService {
	
	TaskRespository taskRepository;
	
	@Autowired
	public TaskServiceImpl(TaskRespository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> getAll() {
		// TODO Auto-generated method stub
		
		List<TaskTotales> query = taskRepository.GetTaskCount();
		
		
		
		return (List<Task>)taskRepository.findAll();
	}

	@Override
	public Task getTask(Long id) {
		// TODO Auto-generated method stub
		Task task = this.getTask(id);
		
		if(task == null)
			throw new TaskServiceException("La tareas con el id " + id + " no existe");
		
		return task;
	}

	@Override
	public Long addTask(Task task) {
		// TODO Auto-generated method stub
		if(task == null || task.getCandidate() == null || task.getTaskDate() == null || task.getTaskState() == null)
			throw new TaskServiceException("No se pudo agregar la entidad " + task);
		
		Task insertTask = taskRepository.save(task);
		if(insertTask != null)
			return insertTask.getId();
		
		return null;
	}

	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		this.updateTask(task);
	}

	@Override
	public void deleteTask(Long id) {
		// TODO Auto-generated method stub
		this.taskRepository.delete(id);
	}

	@Override
	public List<TaskTotales> getTasksTotales() {
		return this.taskRepository.GetTaskCount();
	}

}
