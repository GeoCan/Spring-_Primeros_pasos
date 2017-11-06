package com.plenumsoft.vuzee.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plenumsoft.vuzee.api.models.PagerModel;
import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.services.CandidateService;
import com.plenumsoft.vuzee.viewmodels.CandidateCreateViewModel;
import com.plenumsoft.vuzee.viewmodels.CandidateUpdateViewModel;
import com.plenumsoft.vuzee.viewmodels.CandidateViewModel;

@Controller
@RequestMapping(value = { "/candidates" })
public class CandidatesController {
	String prefix = "candidates/";

	private CandidateService candidateService;
	
	public CandidatesController() {
	}

	@Autowired
	public CandidatesController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
//	@RequestMapping(value = { "/", "" })
//	public ModelAndView Index() {
//		ModelAndView mv = new ModelAndView(prefix + "index");
//		List<Candidate> candidates = candidateService.getAll();
//		mv.addObject("candidates", candidates);
//		return mv;
//	}
	@RequestMapping(value = { "/", "" })
	public String Index(Model model, Pageable pageable) {
		  Page<Candidate> candidatePage = candidateService.listAllByPage(pageable);
	         PageWrapper<Candidate> page = new PageWrapper<Candidate>(candidatePage, "/candidates");
	         model.addAttribute("candidates", page.getContent());
	         model.addAttribute("page", page);
		return prefix + "index";
	}
	
	
//	@RequestMapping(value={"/", ""})
//	public ModelAndView list(
//			@RequestParam("pageSize") Optional<Integer> pageSize,
//			@RequestParam("page") Optional<Integer> page
//            ){
//		ModelAndView mv = new ModelAndView(prefix + "index");
//		//
//		// Evaluate page size. If requested parameter is null, return initial
//		// page size
//		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
//		// Evaluate page. If requested parameter is null or less than 0 (to
//				// prevent exception), return initial size. Otherwise, return value of
//				// param. decreased by 1.
//				int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
//		
//				Page<Candidate> candidatosList = candidateService.listAllByPage(new PageRequest(evalPage, evalPageSize));
//		try {
//			PagerModel pager = new PagerModel(candidatosList.getTotalPages(),candidatosList.getNumber(),BUTTONS_TO_SHOW);
//			
//			
//			// add clientmodel
//			mv.addObject("candidates",candidatosList);
//			// evaluate page size
//			mv.addObject("selectedPageSize", evalPageSize);
//			// add page sizes
//			mv.addObject("pageSizes", PAGE_SIZES);
//			// add pager
//			mv.addObject("pager", pager);
//			
//		} catch (Exception ex) {
//			mv.addObject("message_error", ex.getLocalizedMessage());
//		}
//		
//		
//		return mv;
//	}

	@RequestMapping(value = { "/create" })
	public String PrepareCreate(CandidateCreateViewModel candidateCreateViewModel) {
		return prefix + "create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String PostCreateCandidate(@Valid CandidateCreateViewModel candidateCreateViewModel,
			BindingResult bindingResult, final RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			return prefix + "create";
		}
		Candidate candidate = new Candidate();
		candidate.setName(candidateCreateViewModel.getName());
		candidate.setPositionApplied(candidateCreateViewModel.getPositionApplied());
		candidate.setCreatedBy("msoberanis");// TODO: hard-code
		Date now = new Date();
		candidate.setCreatedAt(new Date());
		candidateService.addCandidate(candidate);
		
		ra.addFlashAttribute("message_success", "Registro ingresado con éxito");
		
		return "redirect:/" + prefix + "/";
	}

	@RequestMapping(value = { "/edit/{id}" })
	public String PrepareEdit(@PathVariable("id") Long id
			,final RedirectAttributes ra, Model model
			) {
		Candidate candidato = null;
		try {
			candidato = candidateService.getCandidateById(id);
			CandidateUpdateViewModel candidateEditViewModel = new CandidateUpdateViewModel();
			candidateEditViewModel.setCreatedAt(candidato.getCreatedAt());
			candidateEditViewModel.setCreatedBy(candidato.getCreatedBy());
			candidateEditViewModel.setId(candidato.getId());
			candidateEditViewModel.setName(candidato.getName());
			candidateEditViewModel.setPositionApplied(candidato.getPositionApplied());
			
			model.addAttribute("candidateUpdateViewModel", candidateEditViewModel);
		} catch (Exception ex) {
			ra.addFlashAttribute("message_error", ex.getLocalizedMessage());
			return "redirect:/" + prefix;
		}
		
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public String PutUpdateCandidate(@PathVariable("id") Long id,
			@Valid CandidateUpdateViewModel candidateUpdateViewModel, BindingResult bindingResult
			,final RedirectAttributes ra, Model model, @RequestParam("file") MultipartFile file
			) {

		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("candidateUpdateViewModel", candidateUpdateViewModel);
				List<ObjectError> errors = bindingResult.getAllErrors();
				return prefix + "edit";
			}

			Candidate can = candidateService.getCandidateById(id);
			
			if(file!=null) {
				String mimeType = file.getContentType();
				byte[] fileBytes = file.getBytes();
				String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
				can.setCvFile(fileBytes);
				can.setCvExtension(fileExtension);
				can.setCvMimeType(mimeType);
			}

			can.setName(candidateUpdateViewModel.getName());
			can.setPositionApplied(candidateUpdateViewModel.getPositionApplied());

			candidateService.updateCandidate(can);

		} catch (Exception ex) {
			// TODO: handle exception
			ra.addFlashAttribute("message_error", ex.getLocalizedMessage());
		}

		// Solo se mantiene una petición
		ra.addFlashAttribute("message_success", "Registro actualizado con éxito");

		return "redirect:/" + prefix;
	}
	
	@RequestMapping(value="/cv/{id}", method=RequestMethod.GET)
	@ResponseBody
	public void getCVFile(@PathVariable("id") Long id, HttpServletResponse response,
           HttpServletRequest request){
		try {
			Candidate candidate = candidateService.getCandidateById(id);
			if(candidate.getCvFile()!=null) {
				byte[] file = candidate.getCvFile();
				String fileName = candidate.getName().replaceAll(" ", "_");
				response.setContentType(candidate.getCvMimeType());
			    response.setHeader("Content-Disposition", "attachment; filename=cv_"+ fileName.toLowerCase() +"."+ candidate.getCvExtension());
			    response.setHeader("Pragma", "no-cache");
			    response.setHeader("Cache-Control", "no-cache");
			    response.getOutputStream().write(file);
			}
			
		}catch(Exception ex) {
		}
		
	}

}
