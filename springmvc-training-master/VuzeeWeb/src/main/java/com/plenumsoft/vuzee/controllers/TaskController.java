package com.plenumsoft.vuzee.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskState;
import com.plenumsoft.vuzee.services.CandidateService;
import com.plenumsoft.vuzee.services.TaskService;
import com.plenumsoft.vuzee.viewmodels.TaskCreateViewModel;

@Controller
@RequestMapping(value = "/tasks")
public class TaskController {

	String prefix = "tasks/";
	private TaskService taskService;
	private CandidateService candidateService;

	public TaskController() {
	}

	@Autowired
	public TaskController(TaskService taskService, CandidateService candidateService) {
		super();
		this.taskService = taskService;
		this.candidateService = candidateService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String Create(Model model) {
		List<Candidate> candidatos = candidateService.getAll();
		model.addAttribute("taskCreateViewModel", new TaskCreateViewModel());
		model.addAttribute("allCandidate", candidatos);
		return prefix + "create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String Create(@Valid TaskCreateViewModel taskCreateViewModel
			, BindingResult bindingResult, final RedirectAttributes ra, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("taskCreateViewModel", taskCreateViewModel);
			model.addAttribute("allCandidate", candidateService.getAll());
			List<ObjectError> errors = bindingResult.getAllErrors();
			return prefix + "create";
		}

		Task task = new Task();

		Candidate can = new Candidate();
		can.setId(taskCreateViewModel.getCandidato());

		try {
		
			task.setCandidate(can);
			task.setCreatedBy("GeoCan");
			task.setCreatedAt(new Date());
			task.setTaskDate(taskCreateViewModel.getTaskDate());
			task.setHasRating(true);
//			task.setTaskState(TaskState.values()[taskCreateViewModel.getTaskState()]);
			task.setTaskState(TaskState.from(taskCreateViewModel.getTaskState()));
			
			taskService.addTask(task);
			
		} catch (Exception ex) {
			// TODO: handle exception
//			ra.addFlashAttribute("message_error", ex.getLocalizedMessage());
			model.addAttribute("taskCreateViewModel", taskCreateViewModel);
			model.addAttribute("allCandidate", candidateService.getAll());
			model.addAttribute("message_error", ex.getLocalizedMessage());
			return prefix + "create";
		}
		
		ra.addFlashAttribute("message_success", "Registro creado con éxito");

		// List<Candidate> candidatos = candidateService.getAll();
		// model.addAttribute("taskCreateViewModel", new TaskCreateViewModel());
		// model.addAttribute("allCandidate", candidatos);
		return prefix + "create";
	}

}
