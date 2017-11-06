package com.plenumsoft.vuzee.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskState;
import com.plenumsoft.vuzee.entities.TaskTotales;
import com.plenumsoft.vuzee.services.CandidateService;
import com.plenumsoft.vuzee.services.TaskService;
import com.plenumsoft.vuzee.viewmodels.DashBoardViewModel;
import com.plenumsoft.vuzee.viewmodels.TotalTaskState;

@RestController
@RequestMapping(value="/dashboard")
public class DashBoardRestController {

	String prefix ="/";
	
	private CandidateService candidateService;
	private TaskService taskService;

	@Autowired
	public DashBoardRestController(CandidateService candidateService, TaskService taskService) {
		super();
		this.candidateService = candidateService;
		this.taskService = taskService;
	}
	
	@RequestMapping(value="/getResumen", method=RequestMethod.GET)
	@ResponseBody
	public List<DashBoardViewModel> GetResumCandidate(){
		List<DashBoardViewModel> resumen = new ArrayList<>();
		
		try {
			List<Task> tasks = taskService.getAll();
			List<TaskTotales> tareas = taskService.getTasksTotales();
			
			
			for (TaskTotales task : tareas) {
				DashBoardViewModel candidate;
				
				DashBoardViewModel das = resumen.stream()
						.filter(x -> x.getIdCandidato().equals(task.getCandidateId()))
						.findFirst()
						.orElse(null);
				
				if(das != null){
					continue;
				}
				else{
					candidate = new DashBoardViewModel();
					candidate.setCandidato(task.getName());
					candidate.setIdCandidato(task.getCandidateId());
					
					List<TotalTaskState> totales = new ArrayList<>();
					
					
					TotalTaskState totalTask;
					for (TaskState item : TaskState.values()){
						totalTask = new TotalTaskState();
						totalTask.setTaskState(item);
						//int total = (int)tasks.stream().filter(x -> x.getTaskState().equals(item) && x.getCandidate().getId().equals(task.getCandidate().getId())).count();
						//totalTask.setTotal(total);
						TaskTotales valor = tareas.stream()
								.filter(x -> x.getCandidateId().equals(task.getCandidateId()) && x.getTaskState() != null && x.getTaskState().equals(item))
								.findFirst()
								.orElse(null);
						int total = 0;
						if(valor != null && valor.getTaskState() != null)
							total = valor.getTotal().intValue();
						totalTask.setTotal(total);
						totales.add(totalTask);
					}
					
					candidate.setTotales(totales);
					resumen.add(candidate);
				}
				
				
				
				
			}
			
//			for (Task task : tasks) {
//				DashBoardViewModel candidate;
//				
//				DashBoardViewModel das = resumen.stream()
//						.filter(x -> x.getIdCandidato().equals(task.getCandidate().getId()))
//						.findFirst()
//						.orElse(null);
//				
//				if(das != null){
//					continue;
//				}
//				else{
//					candidate = new DashBoardViewModel();
//					candidate.setCandidato(task.getCandidate().getName());
//					candidate.setIdCandidato(task.getCandidate().getId());
////					candidate.setCandidato(task.getCandidate());
//					
//					List<TotalTaskState> totales = new ArrayList<>();
//					
//					
//					TotalTaskState totalTask;
//					for (TaskState item : TaskState.values()){
//						totalTask = new TotalTaskState();
//						totalTask.setTaskState(item);
//						int total = (int)tasks.stream().filter(x -> x.getTaskState().equals(item) && x.getCandidate().getId().equals(task.getCandidate().getId())).count();
//						totalTask.setTotal(total);
//						
//						totales.add(totalTask);
//					}
//					
//					candidate.setTotales(totales);
//					resumen.add(candidate);
//				}
//				
//				
//				
//				
//			}
			
		} catch (Exception ex) {
			// TODO: handle exception
		}
		return resumen;
	}
	
}
