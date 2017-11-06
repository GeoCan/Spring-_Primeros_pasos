package com.plenumsoft.vuzee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plenumsoft.vuzee.mysql.entities.User;
import com.plenumsoft.vuzee.mysql.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	String prefix = "users/";
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(value = { "/", "" })
	public String index(Model model){
		List<User> usuarios = service.getAll();
		model.addAttribute("usuarios", usuarios);
		return prefix + "index";
	}
	
	
}
